<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="用户添加">




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
                    <span class="panel-title">用户信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="password" class="col-sm-2 control-label">name</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="name" name="name" placeholder="name">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="password" class="col-sm-2 control-label">password</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="password" name="password" placeholder="password">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-9">
                                    <input type="email" class="form-control" id="email" name="email" placeholder="Email">
                                    </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="phone" class="col-sm-2 control-label">Phone</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Phone">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                </div>
                <div class="panel-footer text-center">
                    <button class="btn btn-primary" id="submitBtn" onclick="return false;">提交</button>
                    <button class="btn btn-danger" onclick="window.history.go(-1)">取消</button>
                </div>
            </form>
        </div>


    </div>



</ot:layout>
<script src="/statics/pages/user/add.js"></script>
