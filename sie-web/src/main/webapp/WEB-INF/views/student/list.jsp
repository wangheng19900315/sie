<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="学生管理">




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
                    <form class="form-inline" id="search-form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="中文名称">
                            <input type="text" class="form-control" id="userID" name="userID" placeholder="用户ID">
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-primary" style="margin-right: 10px;">查找</button>

                        <%--<button type="btn" class="btn" id="addBtn" onclick="return false;" >添加</button> &nbsp;&nbsp;--%>
                        <button type="button" class="btn btn-primary disabled" id="editBtn" >修改</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-primary" id="importBtn"  >导入</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary" id="exportBtn"  >导出</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-warning disabled" id="modifyPasswordBtn"  >修改密码</button> &nbsp;&nbsp;

                        <button type="button"  id="showImportFile" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#importExcels"></button>
                        <%--<button type="btn" class="btn btn-danger disabled" id="deleteBtn"  onclick="return false;" >删除</button> &nbsp;&nbsp;--%>
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

    <div id="importExcels" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title"  >导入Excel</h4>
                </div>
                <div class="modal-body">
                    <form class="form-inline" id="fileFrom" enctype="multipart/form-data">
                        <div class="form-group ">
                            <label   class="col-sm-3 control-label">文件:</label>
                            <div class="col-sm-8" id="courseNameDivs">
                                <input type="file" class="form-control" id="excelFile" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/vnd.ms-excel"  name="excelFile" >
                            </div>
                        </div>

                        <div   id="resultMessage" style="color:red;">
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="importSubmitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>

</ot:layout>
<script src="${rootPath}/statics/pages/student/list.js"></script>
