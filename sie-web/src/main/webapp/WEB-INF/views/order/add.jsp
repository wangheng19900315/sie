<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="订单添加">


    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">订单信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="system" class="col-sm-2 control-label">系统</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="system" name="system">
                                        <option value="1">SIE</option>
                                        <option value="2">TRU</option>
                                    </select>

                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="studentID" class="col-sm-2 control-label">学生</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="studentID" name="studentID">
                                        <option value="1">李易</option>
                                    </select>

                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="projectId" class="col-sm-2 control-label">项目</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="projectId" name="projectId">
                                        <option value="">...</option>
                                    </select>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="courseNumber" class="col-sm-2 control-label">课程数</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="courseNumber" name="courseNumber" placeholder="courseNumber" required data-msg-required="请输入课程数" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="form-group ">
                                <label class="col-sm-1 control-label">课程选择</label>
                                <div class="col-sm-11">
                                    <label><input type="checkbox" name="courseIds"value="1" >课程1</label>
                                    <label><input type="checkbox" name="courseIds"value="2" >课程2</label>
                                </div>
                            </div>
                        </div>
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
<script src="${rootPath}/statics/pages/project/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
</script>