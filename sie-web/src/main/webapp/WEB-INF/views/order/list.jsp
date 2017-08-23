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
                    <form class="form-inline" id="search-form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="orderCode"  name="orderCode" placeholder="订单号">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="studentName" name="studentName" placeholder="用户名">
                        </div>
                        <div class="form-group">
                            <select   class="form-control" id="orderStatus" name="orderStatus"  >
                                <option value="1">已提交</option>
                                <option value="2">已完成</option>
                                <option value="3">已退款</option>
                                <option value="4">申请退款</option>
                                <option value="5">取消</option>
                             </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control" id="payStatus" name="payStatus"  >
                                <option value="1">已提交</option>
                                <option value="2">已支付</option>
                                <option value="3">支付失败</option>
                                <option value="5">取消</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <input type="email" class="form-control" id="crCode" name="crCode" placeholder="Cr Code">
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" id="couponCode" name="crCode" placeholder="优惠码">
                        </div>

                        <button type="button" id="searchBtn" class="btn btn-primary" onclick="return false;" >查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <br/>
                        <button type="button" class="btn" id="addBtn" onclick="return false;" >添加</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary disabled" id="editBtn" onclick="return false;"  >修改</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-danger disabled" id="deleteBtn"  onclick="return false;" >删除</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-success disabled"  id="infoBtn" onclick="return false;" >查看</button> &nbsp;&nbsp;
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




</ot:layout>
<script src="/statics/pages/order/list.js"></script>
