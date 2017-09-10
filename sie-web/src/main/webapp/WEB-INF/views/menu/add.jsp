<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="菜单添加">




    <div class="row">

            <%--<div class="col-sm-6">--%>
            <%--<div class="panel">--%>
            <%--<div class="panel-body buttons-with-margins">--%>
            <%----%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <div class="panel-heading">
                    <span class="panel-title">菜单信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="password" class="col-sm-2 control-label">name</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="name" required data-msg-required="请输入名称">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="password" class="col-sm-2 control-label">password</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="password" name="password" placeholder="password" required data-msg-required="请输入密码" minlength="6" data-msg-minlength="至少输入6个字符">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email" required data-msg-required="请输入Email">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="phone" class="col-sm-2 control-label">Phone</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone" required  data-rule-mobile="true" data-msg-required="请输入手机号"  data-msg-mobile="请输入正确格式">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                </div>
                <div class="panel-footer text-center">
                    <button class="btn btn-primary" id="submitBtn" type="submit" >提交</button>
                    <button class="btn btn-danger" onclick="window.history.go(-1)">取消</button>
                </div>
            </form>
        </div>


    </div>



</ot:layout>
<script src="${rootPath}/statics/pages/menu/add.js"></script>
