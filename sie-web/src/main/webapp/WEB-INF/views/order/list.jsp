<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="订单管理">




    <div class="row">

        <%--<div class="col-sm-6">--%>
            <%--<div class="panel">--%>
                <%--<div class="panel-body buttons-with-margins">--%>
                  <%----%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

        <div class="col-sm-12">
            <div class="panel">
                <div class="panel-body buttons-with-margins">
                    <form class="form-inline" id="search-form" action="" method="post">
                        <div class="form-group">
                            <input type="text" class="form-control" id="orderCode"  name="orderCode" placeholder="订单号">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="studentChineseName" name="studentChineseName" placeholder="学生姓名">
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="projectCode" name="projectCode" placeholder="项目编码">
                        </div>
                        <div class="form-group">
                            <select   class="form-control" id="orderStatus" name="orderStatus" placeholder="订单状态" >
                                <option value="">...</option>
                                <option value="1">已提交</option>
                                <option value="2">已完成</option>
                                <option value="3">已退款</option>
                                <option value="4">申请退款</option>
                                <option value="5">取消</option>
                             </select>
                        </div>
                        <%--<div class="form-group">--%>
                            <%--<select class="form-control" id="orderType" name="orderType"  placeholder="订单类型">--%>
                                <%--<option value="">...</option>--%>
                                <%--<option value="1">用户</option>--%>
                                <%--<option value="2">管理员</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                        <div class="form-group">
                            <select class="form-control" id="system" name="system"  placeholder="提交系统">
                                <option value="">...</option>
                                <option value="1">SIE</option>
                                <option value="2">TRU</option>
                            </select>
                        </div>

                        <button type="button" id="searchBtn" class="btn btn-primary" onclick="return false;" >查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <br/>
                        <%--<button type="button" class="btn" id="addBtn" onclick="return false;" >添加</button> &nbsp;&nbsp;--%>
                        <button type="button" class="btn btn-info disabled"  id="infoBtn"  >查看</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary disabled" id="editBtn" >修改</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-success disabled"  id="refundBtn" data-toggle="modal" data-target="#refund">退课</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-danger disabled" id="addOrderBtn"  data-toggle="modal" data-target="#refund">加课</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-danger disabled" id="deleteBtn"  >删除</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary" id="importBtn"  >导入</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary" id="exportBtn"  >导出</button> &nbsp;&nbsp;
                        <button type="button"  id="showImportFile" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#importExcels"></button>
                    </form>
                </div>
            </div>
        </div>


    </div>


    <div class="row">



        <div class="col-sm-12">
            <div class="panel">


                <div class="panel-body">
                    <div class="table-primary">

                        <table class="table table-striped table-bordered" id="grid-table">
                        </table>
                        <div id="grid-pager"></div>
                    </div>
                </div>
            </div>
    <!-- /11. $JQUERY_DATA_TABLES -->

         </div>
    </div>


    <%--退课弹出框--%>
    <div id="refund" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel"></h4>
                </div>
                <div class="modal-body" >
                    <form   class="form-horizontal" novalidate="novalidate" id="details-form">
                        <input type="hidden" id="studentId" name="studentId" value="">
                        <input type="hidden" id="status" name="status" value="">
                        <input type="hidden" id="orderType" name="orderType" value="2">
                        <input type="hidden" id="systemType" name="systemType" value="">
                        <div id="details"></div>
                        <div class="form-group ">
                            <label  class="col-sm-2 control-label">金额</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="money"  name="money" placeholder="金额">
                            </div>
                        </div>
                        <div class="form-group ">
                            <label  class="col-sm-2 control-label">付款方式</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="payType" name="payType">
                                    <option value="4">人工支付</option>
                                    <option value="1">微信</option>
                                    <option value="2">支付宝</option>
                                    <option value="3">银联</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group ">
                            <label  class="col-sm-2 control-label">备注</label>
                            <div class="col-sm-9">
                                <textarea type="text" class="form-control" rows="3" id="remark"  name="remark" placeholder="" style="resize:none;"></textarea>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" id="refundCanclBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="refundSubmitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>




    <div id="importExcels" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title"  >导入Excel</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline" id="fileFrom" enctype="multipart/form-data">
                        <div class="form-group ">
                            <label   class="col-sm-3 control-label">文件:</label>
                            <div class="col-sm-8" id="courseNameDivs">
                                <input type="file" class="form-control" id="excelFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"  name="excelFile" >
                            </div>
                        </div>

                        <div   id="resultMessage" style="color:red;">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="importSubmitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>

</ot:layout>
<script src="/statics/pages/order/list.js"></script>
