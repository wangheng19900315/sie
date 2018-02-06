<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="Tru网站编辑">






    <div class="row">
        <div class="col-sm-3" role="navigation"  style="position: relative;height: 100%;background-color: lightgrey;">
            <ul id="tree" class="ztree"></ul>
        </div>
        <div class="col-sm-9">
            <form id="data-form"  class="panel form-horizontal" novalidate="novalidate" id="data-form" enctype="multipart/form-data">
                    <div class="panel-heading">
                        <span class="panel-title">标题内容编辑</span>
                    </div>
                    <div class="panel-body">
                        <input type="hidden" id="id" name="id">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="name" class="col-sm-4 control-label">标题</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="name" name="name" placeholder="name" >
                                    </div>
                                </div>
                            </div><!-- col-sm-12 -->
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="sort" class="col-sm-2 control-label">排序</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="sort" name="sort" placeholder="name" >
                                    </div>
                                </div>
                            </div><!-- col-sm-12 -->
                        </div><!-- row -->
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="form-group ">
                                    <label for="subName" class="col-sm-2 control-label">副标题</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="subName" name="subName" placeholder="subName">
                                    </div>
                                </div>
                            </div><!-- col-sm-12 -->
                        </div><!-- row -->
                        <div class="row" id="contentRow">
                            <div class="col-sm-12">
                                <div class="form-group ">
                                    <label for="content" class="col-sm-2 control-label">内容</label>
                                    <div class="col-sm-9">
                                        <textarea name="content" id="content"></textarea>
                                        <%--<input type="text" class="form-control" id="content" name="content" placeholder="content">--%>
                                    </div>
                                </div>
                            </div><!-- col-sm-12 -->
                        </div><!-- row -->
                        <%--<div class="row">--%>
                            <%--<div class="col-sm-12">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label for="chineseName" class="col-sm-2 control-label">图片</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="chineseName3" name="chineseName" placeholder="chineseName" required data-msg-required="请输入姓名" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-12 -->--%>
                        <%--</div><!-- row -->--%>
                    </div>
                    <div class="panel-footer text-center">
                        <button class="btn btn-primary" id="submitBtn" type="button" >提交</button>
                    </div>
                </form>
        </div>
    </div>



    <%--<div id="selectRoles" class="modal fade" tabindex="-1" role="dialog" style="display: none;">--%>
        <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
                <%--<div class="modal-header">--%>
                    <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
                    <%--<h4 class="modal-title" id="myModalLabel">修改角色</h4>--%>
                <%--</div>--%>
                <%--<div class="modal-body">--%>
                    <%--<div class="form-group ">--%>
                        <%--<input type="hidden" name="id" id="id">--%>
                        <%--<label   class="col-sm-2 control-label">角色</label>--%>
                        <%--<div class="col-sm-6">--%>
                            <%--<select class="form-control" id="roleId" name="roleId" >--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<div class="modal-footer">--%>
                    <%--<button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                    <%--<button type="button" id="submitBtn" class="btn btn-primary">提交</button>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
</ot:layout>
<%--ckeditor文件引入--%>
<script src="${rootPath}/statics/ckeditor/ckeditor.js"></script>
<script src="${rootPath}/statics/pages/truWebEditor/titleTree.js"></script>
<%--ztree文件引入--%>
<script src="${rootPath}/statics/ztree/js/jquery.ztree.core.js"></script>


<script type="text/javascript">
    var zNodes = '${zNodes}';
</script>
