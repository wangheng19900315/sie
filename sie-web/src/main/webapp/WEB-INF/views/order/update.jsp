<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="订单修改" home="订单管理">

        <div class="row">
            <div class="col-sm-12">
                <div class="panel">
                    <div class="panel-heading">
                        <label>用户信息</label>
                    </div>
                    <div class="panel-body buttons-with-margins">
                        <form   class="form-horizontal" novalidate="novalidate" id="userInfo" >
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">用户ID:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="studentID" name="studentID" readonly >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">学生姓名:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="studentName" readonly  >
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">中文姓名:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="studentChineseName" readonly  >
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">联系方式:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="studentTel" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">微信:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="weiXin" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">Email:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="studentEmail" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                            </div>
                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">身份信息:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="identity" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">学校:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="schoolName" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">专业:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control"  name="profession" readonly  >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-sm-12">
                <div class="panel">
                    <div class="panel-heading">
                        <label>订单信息</label>
                    </div>
                    <div class="panel-body buttons-with-margins">
                        <form   class="form-horizontal" novalidate="novalidate" id="data-form" >
                            <input type="hidden" name="id" id="id">
                            <input type="hidden" name="systemType" id="systemType">

                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">订单号:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="code" name="code" readonly >
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">订单来源:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="systemTypeName" name="systemTypeName"  readonly>
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">订单状态:</label>
                                        <div class="col-sm-8">
                                            <select class="form-control" id="status" name="status">
                                                <option value="1">已提交</option>
                                                <option value="2">已完成</option>
                                                <option value="3">已退款</option>
                                                <option value="4">申请退款</option>
                                                <option value="5">已取消</option>
                                            </select>

                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->
                            </div><!-- row -->

                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">总金额:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="money" name="money"   readonly>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">支付金额:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="payMoney" name="payMoney" readonly >
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">支付方式:</label>
                                        <div class="col-sm-8">
                                            <select class="form-control" id="payType" name="payType">
                                                <option value="1">微信</option>
                                                <option value="2">支付宝</option>
                                                <option value="3">银联</option>
                                                <option value="4">人工支付</option>
                                            </select>
                                        </div>
                                    </div>
                                </div><!-- col-sm-4 -->

                            </div><!-- row -->

                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">CR优惠:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="crDiscount" name="crDiscount"  readonly>
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">优惠券:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="couponDiscount" name="couponDiscount"  readonly >
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">管理员优惠:</label>
                                        <div class="col-sm-8">
                                            <input type="number" class="form-control" id="discount" name="discount" >
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">退款方式:</label>
                                        <div class="col-sm-8">
                                            <select class="form-control" id="refundType" name="refundType">
                                                <option value=""></option>
                                                <option value="1" aria-id="refundWayBank">银行转账</option>
                                                <option value="2" aria-id="refundWayAlipay">支付宝</option>
                                                <option value="3" aria-id="refundWayWechat">微信</option>
                                                <option value="4" aria-id="refundWayEMT">加币EMT</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">退款金额:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="refundMoney" name="refundMoney">
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                            </div>

                            <div class="row"  id="refundWayBank" hidden>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">开户行:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="depositBank" name="depositBank" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">收款人:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="bank-payee" name="payee" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">账号:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="account" >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row"  id="refundWayAlipay" hidden>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">账号:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="alipay" name="alipay" >
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">收款人:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="alipay-payee" name="payee" >
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div id="refundWayWechat"class="row" hidden></div>
                            <div class="row"  id="refundWayEMT" hidden>
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">邮箱:</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="refundEmail" name="refundEmail" >
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">备注:</label>
                                        <div class="col-sm-8">
                                            <textarea type="text" class="form-control" id="remark" name="remark" style="resize: none"></textarea>
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                                <div class="col-sm-4">
                                    <div class="form-group ">
                                        <label   class="col-sm-3 control-label">退款原因:</label>
                                        <div class="col-sm-8">
                                            <textarea type="text" class="form-control" id="refundReason" name="refundReason" style="resize: none"></textarea>
                                        </div>
                                    </div>
                                </div><!-- col-sm-6 -->
                            </div>

                            <div class="panel-footer text-center">
                                <button class="btn btn-primary" id="submitBtn" type="button" >提交</button>
                                <button class="btn btn-danger" type="button" onclick="window.history.go(-1)">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" id="orderDetailInfo">
            <div class="col-sm-12">
                <div class="panel">
                    <div class="panel-heading">
                        <label>订单明细</label>
                    </div>
                    <div class="panel-body">
                        <div class="table-primary">
                            <button id="selectBtn" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#selectCourses"></button>
                            <table class="table table-striped table-bordered" id="grid-table">
                            </table>
                            <%--<div id="grid-pager"></div>--%>
                        </div>
                    </div>
                </div>
                <!-- /11. $JQUERY_DATA_TABLES -->

            </div>
        </div>

        <div id="selectCourses" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">选择课程</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group ">
                        <%--<label   class="col-sm-3 control-label">课程:</label>--%>
                        <div class="col-sm-8" id="courseNameDivs">

                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" id="detailCanclBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="detailSubmitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>

</ot:layout>
<script src="${rootPath}/statics/pages/order/update.js"></script>
<script type="text/javascript">
    var id = "${id}";
</script>

