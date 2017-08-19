<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="用户添加">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">优惠码信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="name" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="name" required data-msg-required="请输入名称">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="code" class="col-sm-2 control-label">优惠码</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="code" required data-msg-required="请输入优惠码">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="rmbDiscount" class="col-sm-2 control-label">RMB优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="rmbDiscount" name="rmbDiscount" placeholder="rmbDiscount" required data-msg-required="请输入RMB优惠" data-rule-number="true" data-msg-number="请输入正确的数字" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="dollarDiscount" class="col-sm-2 control-label">美元优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="dollarDiscount" name="dollarDiscount" placeholder="dollarDiscount" required data-msg-required="请输入美元优惠" data-rule-number="true" data-msg-number="请输入正确的数字">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="canadianDiscount" class="col-sm-2 control-label">加币优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="canadianDiscount" name="canadianDiscount" placeholder="canadianDiscount" required data-msg-required="请输入加币优惠" data-rule-number="true" data-msg-number="请输入正确的数字" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="status" class="col-sm-2 control-label">优惠状态</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="status" name="status" placeholder="dollarDiscount" required data-msg-required="请输入活动状态" data-rule-number="true" data-msg-number="请输入正确的数字">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                </div>
                <div class="panel-footer text-center">
                    <button class="btn btn-primary" id="submitBtn" type="submit" >提交</button>
                    <button class="btn btn-danger" type="button" onclick="window.history.go(-1)">取消</button>
                </div>
            </form>
        </div>


    </div>

</ot:layout>
<script src="/statics/pages/student/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
</script>