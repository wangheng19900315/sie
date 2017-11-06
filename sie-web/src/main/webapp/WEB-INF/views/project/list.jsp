<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="项目管理">




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
                            <input type="text" class="form-control" name="code" placeholder="项目编码">
                        </div>
                        <button type="button"  id="searchBtn" class="btn btn-primary" >查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button type="button"  class="btn" id="addBtn" >添加</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary disabled" id="editBtn">修改</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-danger disabled" id="deleteBtn">删除</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary" id="exportBtn"  >导出</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-success"  id="registrationProjectBtn" data-toggle="modal" data-target="#registrationProject">报名项目</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary disabled" id="exportStudentCourseBtn">导出学生课程</button> &nbsp;&nbsp;
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


    <%--报名项目弹出框--%>
    <div id="registrationProject" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title">项目报名设置</h4>
                </div>
                <div class="modal-body" >
                    <form   class="form-horizontal" novalidate="novalidate" id="registration-form">
                        <div class="form-group">
                            <div class="col-sm-12 twoProject">
                                <input type="hidden" name="projectOneId" value="1">
                                <input type="hidden" name="projectTwoId" value="1">
                                <input name="dormitoryid" type="checkbox" value="1">ECNU 4周 校内住宿
                            </div>
                        </div>
                    </form>

                </div>
                <div class="modal-footer">
                    <button type="button" id="registrationCancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="registrationProjectSubmitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>


</ot:layout>
<script src="${rootPath}/statics/pages/project/list.js"></script>
