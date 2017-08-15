<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="学校管理">




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
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" placeholder="输入名称">
                        </div>
                        <button type="btn" id="searchBtn" class="btn btn-primary">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button class="btn" id="addBtn" onclick="return false;" >添加</button> &nbsp;&nbsp;
                        <button class="btn btn-primary" id="editBtn" >修改</button> &nbsp;&nbsp;
                        <button class="btn btn-info" id="deleteBtn" >删除</button> &nbsp;&nbsp;
                        <button class="btn btn-success" id="infoBtn">查看</button> &nbsp;&nbsp;
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

         </div>
    </div>



</ot:layout>
<script src="/statics/pages/school/list.js"></script>
