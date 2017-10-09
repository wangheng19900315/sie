<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="成绩修改" home="成绩单管理">




    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">成绩信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label   class="col-sm-2 control-label">学生</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="studentName" name="studentName"  readonly>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label   class="col-sm-2 control-label">项目名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="projectName" name="projectName" readonly>
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label   class="col-sm-2 control-label">课程名</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="courseName" name="courseName" readonly >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label   class="col-sm-2 control-label">分数</label>
                                <div class="col-sm-9">
                                    <select   class="form-control" id="grade" name="grade"  >
                                        <option value="">...</option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                        <option value="D">D</option>
                                    </select>
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->

                </div>
                <div class="panel-footer text-center">
                    <button class="btn btn-primary" id="submitBtn" type="submit" >提交</button>
                    <button class="btn btn-danger" type="button" onclick="window.history.go(-1)">取消</button>
                </div>
            </form>
        </div>


    </div>



</ot:layout>
<script src="${rootPath}/statics/pages/grade/add.js"></script>
<script type="text/javascript">
    var bean = '${bean}';
</script>
