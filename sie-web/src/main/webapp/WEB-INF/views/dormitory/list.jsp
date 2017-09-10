<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="宿舍管理">




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
                        <button type="button"  id="searchBtn" class="btn btn-primary" >查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button type="button"  class="btn" id="addBtn" >添加</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary disabled" id="editBtn">修改</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-danger disabled" id="deleteBtn">删除</button> &nbsp;&nbsp;
                        <button type="button"  class="btn btn-primary" id="exportBtn"  >导出</button> &nbsp;&nbsp;
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

</ot:layout>
<script src="${rootPath}/statics/pages/dormitory/list.js"></script>
