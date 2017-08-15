<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="角色添加">




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
                    <span class="panel-title">角色信息</span>
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
                                <label for="password" class="col-sm-2 control-label">menu</label>
                                <div class="col-sm-9">
                                    <%--<input type="text" class="form-control" id="password" name="password" placeholder="password" required data-msg-required="请输入密码" minlength="6" data-msg-minlength="至少输入6个字符">--%>
                                        <select id="role-list" multiple="multiple">
                                            <option value="cheese">Cheese</option>
                                            <option value="tomatoes">Tomatoes</option>
                                            <option value="mozarella">Mozzarella</option>
                                            <option value="mushrooms">Mushrooms</option>
                                            <option value="pepperoni">Pepperoni</option>
                                            <option value="onions">Onions</option>
                                        </select>
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
<script src="/statics/pages/role/add.js"></script>
