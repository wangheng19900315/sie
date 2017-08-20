<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="订单详情">


    <div class="row" style="margin-bottom: 10px">
        <ul class="nav nav-tabs nav-tabs-sm">
            <li class="active">
                <a href="#" id="orderBtn" data-toggle="tab">订单信息  </a>
            </li>
            <li>
                <a href="#" id="orderDetailBtn" data-toggle="tab">订单明细 </a>
            </li>
        </ul>

    </div>
    <div class="tab-content tab-content-bordered">

        <div class="row" id="orderInfo">

            <%--<div class="col-sm-6">--%>
                <%--<div class="panel">--%>
                    <%--<div class="panel-body buttons-with-margins">--%>
                      <%----%>
                    <%--</div>--%>
                <%--</div>--%>
            <%--</div>--%>

            <div class="col-sm-12">
                <div class="panel">
                    <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">订单号:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="code" name="code" readonly >
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">创建时间:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="createTime" name="createTime" readonly >
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">学生名称:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="studentName" name="studentName" readonly  >
                                    </div>
                                </div>
                            </div>
                        </div><!-- row -->

                        <div class="row" style="margin-top: 10px">
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
                                    <label   class="col-sm-3 control-label">订单状态:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="statusName" name="statusName" readonly >
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->

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
                                    <label   class="col-sm-3 control-label">优惠卷:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="couponDiscount" name="couponDiscount"  readonly >
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">管理员优惠:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="discount" name="discount" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">订单来源:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="systemTypeName" name="systemTypeName"  readonly>
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">订单类型:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="orderTypeName" name="orderTypeName"  readonly >
                                    </div>
                                </div>
                            </div><!-- col-sm-6 -->
                            <div class="col-sm-4">
                                <div class="form-group ">
                                    <label   class="col-sm-3 control-label">支付时间:</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="payTime" name="payTime" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>


        </div>


        <div class="row" id="orderDetailInfo">
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
  </div>




</ot:layout>
<script src="/statics/pages/order/detail.js"></script>
<script type="text/javascript">
    var id = "${id}";
</script>

