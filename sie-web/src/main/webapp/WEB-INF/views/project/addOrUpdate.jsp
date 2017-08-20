<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="项目添加">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">所属系统</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="form-group ">
                                <label for="system" class="col-sm-3 control-label">系统</label>
                                <div class="col-sm-9">
                                    <select class="form-control form-group-margin" id="system" name="system">
                                        <option value="1">SIE</option>
                                        <option value="2">TRU</option>
                                        <option value="3">SIE&TRU</option>
                                    </select>

                                </div>
                            </div>
                        </div><!-- col-sm-4 -->
                        <div class="col-sm-4">
                            <div class="form-group ">
                                <label for="startTime" class="col-sm-3 control-label">开始时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date form_datetime"   data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="startTime" data-link-format="yyyy-mm-dd hh:ii:ss">
                                        <input class="form-control" type="text"  id="startTime" name="startTime"  value=""  required data-msg-required="请输入开始时间" >
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-4 -->
                        <div class="col-sm-4">
                            <div class="form-group ">
                                <label for="endTime" class="col-sm-3 control-label">结束时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date form_datetime" data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="endTime" data-link-format="yyyy-mm-dd hh:ii:ss" >
                                        <input class="form-control" size="16" type="text"  id="endTime" name="endTime"  value=""  required data-msg-required="请输入结束时间" >
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-4 -->

                    </div><!-- row -->
                    <div id="sie">

                    </div>
                    <div id="tru">

                    </div>
                </div>
                <div class="panel-footer text-center">
                    <button class="btn btn-primary" id="submitBtn" type="submit" >提交</button>
                    <button class="btn btn-danger" type="button" onclick="window.history.go(-1)">取消</button>
                </div>
            </form>
        </div>


    </div>

</ot:layout>
<script src="/statics/pages/project/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
</script>