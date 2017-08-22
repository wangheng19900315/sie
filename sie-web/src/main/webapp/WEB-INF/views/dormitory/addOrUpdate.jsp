<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="宿舍添加">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">优惠码信息</span>
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
                                <label for="name" class="col-sm-2 control-label">宿舍名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="name" required data-msg-required="请输入优惠码">
                                </div>
                            </div>

                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="maxNumber" class="col-sm-2 control-label">最大人数</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="maxNumber" name="maxNumber" placeholder="maxNumber" required data-msg-required="请输入最大人数" data-rule-age="true" data-msg-age="请输入正确的数字" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="address" class="col-sm-2 control-label">地址</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="address" name="address" placeholder="address" required data-msg-required="请输入地址">
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
<script src="/statics/pages/dormitory/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
    var projects = '${projects}';
</script>