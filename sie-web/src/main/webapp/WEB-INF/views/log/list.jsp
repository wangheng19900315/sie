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
                    <form class="form-inline" id="search-form">
                        <div class="form-group">
                            <input type="text" class="form-control" id="comment" name="comment" placeholder="备注">
                        </div>
                        <button type="button" id="searchBtn" class="btn btn-primary">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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

</ot:layout>
<script src="${rootPath}/statics/pages/log/list.js"></script>
