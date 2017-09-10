<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="寄送管理">




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
                            <input type="text" class="form-control" id="name" placeholder="输入名称">
                        </div>
                        <button type="button"  id="searchBtn" class="btn btn-primary" >查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button type="button"  class="btn" id="addBtn" >添加</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary disabled" id="editBtn">修改</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-danger disabled" id="deleteBtn">删除</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-success" id="exportBtn"  >导出</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary" id="importBtn">导入</button> &nbsp;&nbsp;
                        <button type="button"  id="showImportFile" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#importExcels"></button>
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
                    <h4 class="modal-title" id="myModalLabel">导入Excel</h4>
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
                    <button type="button" id="submitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</ot:layout>
<script src="${rootPath}/statics/pages/send/list.js"></script>
