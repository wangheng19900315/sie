<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="用户管理">




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
                        <button type="button" id="searchBtn" class="btn btn-primary">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button type="button" class="btn" id="addBtn" >添加</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary disabled" id="editBtn" >修改</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-danger disabled" id="deleteBtn" >删除</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-info disabled" id="selectRoleBtn" >选择角色</button> &nbsp;&nbsp;
                        <button type="button"  id="showSelectRoles" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#selectRoles"></button>

                    <%--<button type="btn" class="btn btn-success" id="infoBtn" onclick="return false;" >查看</button> &nbsp;&nbsp;--%>
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



    <div id="selectRoles" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">修改角色</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group ">
                        <input type="hidden" name="id" id="id">
                        <label   class="col-sm-2 control-label">角色</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="roleId" name="roleId" >
                            </select>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="submitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</ot:layout>
<script src="${rootPath}/statics/pages/user/list.js"></script>
