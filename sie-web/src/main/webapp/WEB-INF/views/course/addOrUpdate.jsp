<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="课程添加">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">所属系统</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="projectId" class="col-sm-2 control-label">所在项目</label>
                                <div class="col-sm-9">
                                    <select class="form-control form-group-margin" id="projectId" name="projectId">
                                    </select>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="maxStudent" class="col-sm-2 control-label">最大学生数</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="maxStudent" name="maxStudent" placeholder="maxStudent" required data-msg-required="请输入最大学生数" data-rule-age="true" data-msg-age="请输入正确的整数">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div>
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="startTimeFormat" class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date">
                                        <input type="text" class="form-control  form_time" id="startTimeFormat" name="startTimeFormat"  value=""  required data-msg-required="请输入开始时间">
                                        <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="endTimeFormat" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date">
                                        <input type="text" class="form-control  form_time" id="endTimeFormat" name="endTimeFormat"  value=""  required data-msg-required="请输入结束时间">
                                        <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="system" class="col-sm-2 control-label">系统</label>
                                <div class="col-sm-9">
                                    <select class="form-control form-group-margin" id="system" name="system">
                                        <option value="1">SIE</option>
                                        <option value="2">TRU</option>
                                        <option value="3">SIE&TRU</option>
                                    </select>

                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="system" class="col-sm-2 control-label">教授名称ID</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="professorId" name="professorId" placeholder="professorId" required data-msg-required="请输入教授ID">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="chineseName" class="col-sm-2 control-label">中文名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="chineseName" name="chineseName" placeholder="chineseName" required data-msg-required="请输入中文名称">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="chineseName" class="col-sm-2 control-label">英文名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="englishName" name="englishName" placeholder="englishName" required data-msg-required="请输入中文名称">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div>

                    <div class="row">
                        <div class="col-sm-6" id="sie" hidden>
                            <div class="form-group ">
                                <label for="sieCode" class="col-sm-2 control-label">SIE课程编码</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="sieCode" name="sieCode" placeholder="sieCode" required data-msg-required="请输入SIE课程编码">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

                        <div class="col-sm-6" id="tru" hidden>
                            <div class="form-group ">
                                <label for="truCode" class="col-sm-2 control-label">TRU课程编码</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="truCode" name="truCode" placeholder="truCode" required data-msg-required="请输入TRU课程编码">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

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
<script src="/statics/pages/course/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
    var projects = '${projects}';
</script>