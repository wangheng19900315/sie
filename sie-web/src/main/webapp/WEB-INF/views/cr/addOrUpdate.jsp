<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="CR添加">




    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">CR信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="personName" class="col-sm-2 control-label">name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="personName" name="personName" placeholder="name" required data-msg-required="请输入姓名">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="code" class="col-sm-2 control-label">CR编码</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="code" required data-msg-required="请输入CR编码">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="rmbPrice" class="col-sm-2 control-label">RMB优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="rmbPrice" name="rmbPrice" placeholder="rmbPrice" required data-msg-required="请输入RMB优惠金额" data-rule-number="true" data-msg-number="请输入正确的数字">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="dollarPrice" class="col-sm-2 control-label">美金优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="dollarPrice" name="dollarPrice" placeholder="dollarPrice" required data-msg-required="请输入美金优惠金额" data-rule-number="true" data-msg-number="请输入正确的数字">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="canadianPrice" class="col-sm-2 control-label">加币优惠</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="canadianPrice" name="canadianPrice" placeholder="canadianPrice" required data-msg-required="请输入加币优惠金额" data-rule-number="true" data-msg-number="请输入正确的数字">
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
<script src="/statics/pages/cr/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
</script>
