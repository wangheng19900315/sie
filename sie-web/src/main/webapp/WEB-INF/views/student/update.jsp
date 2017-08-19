<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<ot:layout title="学生信息编辑">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">学生信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="code" class="col-sm-2 control-label">用户名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="code" required data-msg-required="请输入用户名">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="userID" class="col-sm-2 control-label">用户ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="userID" name="userID" placeholder="name" required data-msg-required="请输入用户ID">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="email" required data-msg-required="请输入邮箱" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="weiXin" class="col-sm-2 control-label">微信号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="weiXin" name="weiXin" placeholder="weiXin" required data-msg-required="请输入微信号" >
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="lastName" class="col-sm-2 control-label">姓-LastName</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="LastName" required data-msg-required="请输入姓" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="firstName" class="col-sm-2 control-label">名-FirstName</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="FirstName" required data-msg-required="请输入姓" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="chineseName" class="col-sm-2 control-label">中文名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="chineseName" required data-msg-required="请输入姓名" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label class="col-sm-2 control-label">出生日期</label>
                                <div class="col-sm-9">
                                    <div class="input-group date form_date"   data-date-format="dd-mm-yyyy" data-link-field="birthday" data-link-format="yyyy-mm-dd">
                                        <input class="form-control" size="16" type="text"     value="<fmt:formatDate value='${entity.birthday}' pattern='yyyy-MM-dd'/>"  >
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    </div>

                                    <input type="hidden" id="birthday" name="birthday">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="nationality" class="col-sm-2 control-label">国籍</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="nationality" name="nationality" placeholder="nationality" required data-msg-required="请输入国籍" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="idNumber" class="col-sm-2 control-label">身份证号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="idNumber" name="idNumber" placeholder="ID">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="passportNumber" class="col-sm-2 control-label">护照号</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="passportNumber" name="passportNumber" placeholder="Passport" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="telephone" class="col-sm-2 control-label">国内联系电话</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="telephone" name="telephone" placeholder="Phone" required data-msg-required="请输入手机号"  data-rule-mobile="true" data-msg-mobile="请输入正确格式">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="university" class="col-sm-2 control-label">在读大学</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="university" name="university" placeholder="university" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="profession" class="col-sm-2 control-label">专业</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="profession" name="profession" placeholder="profession" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="gpa" class="col-sm-2 control-label">GAP</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="gpa" name="gpa" placeholder="GAP" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="graduationYear" class="col-sm-2 control-label">毕业年份</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="graduationYear" name="graduationYear" placeholder="graduationYear" >
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
<script src="/statics/pages/student/update.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
    $('.form_date').datetimepicker({
        language: 'zh-CN',//显示中文
        format: 'yyyy-mm-dd',//显示格式
        autoclose: true,//选中自动关闭
        startView: 2,
        minView: 2,
        forceParse: 0
    });
</script>