<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="用户管理">
    <!-- Javascript -->
    <script>
        init.push(function () {
            $('#jq-datatables-example').dataTable();
            $('#jq-datatables-example_wrapper .table-caption').text('Some header text');
            $('#jq-datatables-example_wrapper .dataTables_filter input').attr('placeholder', 'Search...');
        });
    </script>
    <!-- / Javascript -->

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
<script src="/statics/pages/user/list.js"></script>
