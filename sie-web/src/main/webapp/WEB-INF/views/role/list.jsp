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
                    <form class="form-inline">
                        <div class="form-group">
                            <input type="text" class="form-control" id="name" placeholder="输入名称">
                        </div>
                        <button type="btn" id="searchBtn" class="btn btn-primary">查找</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                        <button class="btn" id="addBtn" onclick="return false;">添加</button> &nbsp;&nbsp;
                        <button class="btn btn-primary disabled" id="editBtn"  onclick="return false;">修改</button> &nbsp;&nbsp;
                        <button class="btn btn-danger disabled" id="deleteBtn" onclick="return false;" >删除</button> &nbsp;&nbsp;
                        <button type="button" class="btn btn-info disabled" id="selectMenu" >修改权限</button> &nbsp;&nbsp;
                        <button type="button"  id="showMenubtn" style="display: none" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#selectMenuDiv"></button>

                    <%--<button class="btn btn-success" id="infoBtn" onclick="return false;">查看</button> &nbsp;&nbsp;--%>
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


    <div id="selectMenuDiv" class="modal fade" tabindex="-1" role="dialog" style="display: none;">
        <div class="modal-dialog" style="width:50%">
            <div class="modal-content">
                <div class="modal-header">
                    <input type="hidden" name="id" id="id">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h4 class="modal-title" id="myModalLabel">修改权限</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-sm-6">
                             <div class="panel">
                                 <div class="panel-heading">
                                     <input type="checkbox" name="menuId" value="1">订单管理
                                     <a   class="accordion-toggle" data-toggle="collapse"   href="#orderInfo" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                 </div>



                                <div id="orderInfo" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="8">订单管理
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .collapse -->
                            </div>

                        </div>

                        <div class="col-sm-6">
                            <div class="panel">

                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="2">学生管理
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#studentInfo" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="studentInfo" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="9">学生管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="10">成绩单管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="11">寄送管理&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .collapse -->
                            </div>

                        </div>

                    </div>


                    <div class="row">
                        <div class="col-sm-6">
                            <div class="panel">
                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="3">项目管理
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#projectInfo" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="projectInfo" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="12">项目管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="13">课程管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="14">宿舍管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="15">组合价格&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .cocollapse -->
                            </div>

                        </div>

                        <div class="col-sm-6">
                            <div class="panel">

                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="4">优惠管理
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#conuponInfo" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="conuponInfo" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="16">CR管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="17">优惠码管理&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .collapse -->
                            </div>

                        </div>

                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="panel">
                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="5">报表管理
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#reportDiv" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="reportDiv" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="18">订单报表&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .cocollapse -->
                            </div>

                        </div>

                        <div class="col-sm-6">
                            <div class="panel">

                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="6">数据字典
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#dictoryDiv" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="dictoryDiv" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="19">学校管理&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .cocollapse -->
                            </div>

                        </div>


                    </div>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="panel">
                                <div class="panel-heading">
                                    <input type="checkbox" name="menuId" value="7">权限管理
                                    <a class="accordion-toggle" data-toggle="collapse"   href="#userInfo" style="float:left;width: 50%;position: initial;padding: 0 0"></a>
                                </div>
                                <div id="userInfo" class="panel-collapse collapse in" style="height: auto;">
                                    <div class="panel-body">
                                        <input type="checkbox" name="menuId" value="20">用户管理&nbsp;&nbsp;
                                        <input type="checkbox" name="menuId" value="21">角色管理&nbsp;&nbsp;
                                    </div> <!-- / .panel-body -->
                                </div> <!-- / .cocollapse -->
                            </div>

                        </div>



                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancelBtn" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" id="submitBtn" class="btn btn-primary">提交</button>
                </div>
            </div>
        </div>
    </div>
</ot:layout>
<script src="/statics/pages/role/list.js"></script>
