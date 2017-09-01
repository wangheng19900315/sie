<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="菜单学校">




    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">学校信息</span>
                </div>
                <div class="panel-body">
                    <div class="row">

                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="nationality" class="col-sm-2 control-label">国家</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="nationality" name="nationality" placeholder="nationality" required data-msg-required="请输入国家">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="province" class="col-sm-2 control-label">省份</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="province" name="province" placeholder="province" required data-msg-required="请输入省份">
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="name" class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="name" name="name" placeholder="name" required data-msg-required="请输入名称">
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
<script src="/statics/pages/school/add.js"></script>
<script type="text/javascript">
    var entity = '${entity}';
</script>
