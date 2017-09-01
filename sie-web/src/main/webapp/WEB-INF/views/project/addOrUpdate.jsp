<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ot" tagdir="/WEB-INF/tags" %>
<ot:layout title="项目添加">

    <div class="row">
        <div class="col-sm-12">
            <form   class="panel form-horizontal" novalidate="novalidate" id="data-form" >
                <input type="hidden" id="id" name="id">
                <div class="panel-heading">
                    <span class="panel-title">项目信息</span>
                </div>
                <div class="panel-body">
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
                                <label for="code" class="col-sm-2 control-label">项目编码</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="code" name="code" placeholder="code" required data-msg-required="请输入项目编码" >
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                    </div><!-- row -->
                    <div class="row">

                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="startTimeFormat" class="col-sm-2 control-label">开始时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date form_date">
                                        <input type="text" class="form-control" id="startTimeFormat" name="startTimeFormat" data-date-format="yyyy-mm-dd" value=""  required data-msg-required="请输入开始时间">
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->
                        <div class="col-sm-6">
                            <div class="form-group ">
                                <label for="endTimeFormat" class="col-sm-2 control-label">结束时间</label>
                                <div class="col-sm-9">
                                    <div class="input-group date form_date"   data-link-field="endTime" >
                                        <input class="form-control" size="16" type="text"  id="endTimeFormat" name="endTimeFormat" data-date-format="yyyy-mm-dd" value=""  required data-msg-required="请输入结束时间" >
                                        <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                                    </div>
                                </div>
                            </div>
                        </div><!-- col-sm-6 -->

                    </div><!-- row -->
                    <div id="sie" hidden>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="sieName" class="col-sm-2 control-label">SIE项目名称</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="sieName" name="sieName" placeholder="sieName" required data-msg-required="请输入SIE系统名称" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="sieMaxCourse" class="col-sm-2 control-label">SIE最大课程数</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="sieMaxCourse" name="sieMaxCourse" placeholder="sieMaxCourse" required data-msg-required="请输入SIE最大课程数" data-rule-age="true" data-msg-age="请输入正确的整数" min="1" max="5">
                                    </div>
                                </div>
                            </div>
                        </div>
                        <%--<div class="row" id="sie_price_0" hidden>--%>
                            <%--<input type="hidden" id="siePrice_0_courseNumber"  name="siePrice[0].courseNumber" value="1">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_0_rmbPrice" name="siePrice[0].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_0_dollarPrice" name="siePrice[0].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_0_canadianPrice" name="siePrice[0].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie one course price -->--%>
                        <%--<div class="row" id="sie_price_1" hidden>--%>
                            <%--<input type="hidden" id="siePrice_1_courseNumber" name="siePrice[1].courseNumber" value="2">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_1_rmbPrice" name="siePrice[1].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_1_dollarPrice" name="siePrice[1].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_1_canadianPrice" name="siePrice[1].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie two course price -->--%>
                        <%--<div class="row" id="sie_price_2" hidden>--%>
                            <%--<input type="hidden" id="siePrice_2_courseNumber" name="siePrice[2].courseNumber" value="3">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_2_rmbPrice" name="siePrice[2].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_2_dollarPrice" name="siePrice[2].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="siePrice_2_canadianPrice" name="siePrice[2].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie three course price -->--%>
                    </div>


                    <div id="tru" hidden>
                        <div class="row" >
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="truName" class="col-sm-2 control-label">TRU项目名称</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="truName" name="truName" placeholder="truName" required data-msg-required="请输入TRU系统名称" >
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group ">
                                    <label for="truMaxCourse" class="col-sm-2 control-label">TRU最大课程数</label>
                                    <div class="col-sm-9">
                                        <input type="text" class="form-control" id="truMaxCourse" name="truMaxCourse" placeholder="truMaxCourse" required data-msg-required="请输入TRU最大课程数" data-rule-age="true" data-msg-age="请输入正确的整数">
                                    </div>
                                </div>
                            </div>
                        </div><!-- end sie project -->

                        <%--<div class="row" id="tru_price_0" hidden>--%>
                            <%--<input type="hidden" id="truPrice_0_courseNumber" name="truPrice[0].courseNumber" value="1">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_0_rmbPrice" name="truPrice[0].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_0_dollarPrice" name="truPrice[0].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">1门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_0_canadianPrice"  name="truPrice[0].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie one course price -->--%>
                        <%--<div class="row" id="tru_price_1" hidden>--%>
                            <%--<input type="hidden" id="truPrice_1_courseNumber" name="truPrice[1].courseNumber" value="2">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_1_rmbPrice" name="truPrice[1].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_1_dollarPrice" name="truPrice[1].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">2门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_1_canadianPrice" name="truPrice[1].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie two course price -->--%>
                        <%--<div class="row" id="tru_price_2" hidden>--%>
                            <%--<input type="hidden" id="truPrice_2_courseNumber" name="truPrice[2].courseNumber" value="3">--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(RMB)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_2_rmbPrice" name="truPrice[2].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(美金)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_2_dollarPrice" name="truPrice[2].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                            <%--<div class="col-sm-4">--%>
                                <%--<div class="form-group ">--%>
                                    <%--<label  class="col-sm-3 control-label">3门课程价格(加币)</label>--%>
                                    <%--<div class="col-sm-9">--%>
                                        <%--<input type="text" class="form-control" id="truPrice_2_canadianPrice" name="truPrice[2].canadianPrice" placeholder="Canadian" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div><!-- col-sm-4 -->--%>
                        <%--</div><!-- end sie three course price -->--%>
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