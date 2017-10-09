<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="寄送信息添加" home="寄送信息管理">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <input type="hidden" id="defaultSend" name="defaultSend" value="0">
                <div class="panel-heading">
                    <span class="panel-title">寄送信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="studentId" class="col-sm-2 control-label">学生</label>
                                <div class="col-sm-9">
                                    <select class="form-control form-group-margin" id="studentId" name="studentId">
                                    </select>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="expressCompany" class="col-sm-2 control-label">快递公司</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="expressCompany" name="expressCompany" placeholder="expressCompany" required data-msg-required="请选择学生">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="trackingNumber" class="col-sm-2 control-label">寄送单号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="trackingNumber" name="trackingNumber" placeholder="trackingNumber" required data-msg-required="请输入单号">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendCountry" class="col-sm-2 control-label">寄送街道</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendStreet" name="sendStreet" placeholder="sendStreet" required data-msg-required="请输入街道">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendCountry" class="col-sm-2 control-label">寄送县/市</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendCountry" name="sendCountry" placeholder="sendCountry" required data-msg-required="请输入县/市">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendProvince" class="col-sm-2 control-label">寄送州/省</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendProvince" name="sendProvince" placeholder="sendProvince" required data-msg-required="请输入州/市" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendPostCode" class="col-sm-2 control-label">寄送邮编</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendPostCode" name="sendPostCode" placeholder="sendPostCode" required data-msg-required="请输入邮编">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendPerson" class="col-sm-2 control-label">寄送联系人/部门</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendPerson" name="sendPerson" placeholder="sendPerson" required data-msg-required="请输入联系人/部门" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="sendTel" class="col-sm-2 control-label">寄送电话</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sendTel" name="sendTel" placeholder="sendTel" required data-msg-required="请输入电话">
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
<script src="${rootPath}/statics/pages/send/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
    var students = '${students}';
</script>