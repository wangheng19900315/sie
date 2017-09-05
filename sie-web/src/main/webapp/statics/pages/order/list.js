

var selectRows =new Array();

/**
 * 按钮有效性切换
 */
function selectRow() {
    var curpagenum = $("#grid-table").jqGrid('getGridParam', 'page');
    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
    selectRows[curpagenum-1]=ids;

    if (ids && ids.length == 1) {
        $('#editBtn').removeClass('disabled');
        $('#infoBtn').removeClass('disabled');
        var status = $("#grid-table").jqGrid('getRowData', ids[0]).status;
        //Fixme 已经完成的订单才可以进行退款和加课
        if(status == '2'){
            $('#refundBtn').removeClass('disabled');
            $('#addOrderBtn').removeClass('disabled');
        }
        //已经取消的订单可以进行删除
        if(status == '5'){
            $('#deleteBtn').removeClass('disabled');
        }
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#infoBtn').addClass('disabled');
        $('#refundBtn').addClass('disabled');
        $('#addOrderBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/order/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','订单号','创建时间','systemType','提交系统','订单类型','学生姓名','学生ID','支付金额','订单状态','订单状态','支付时间','项目名称','课程数' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'code', index: 'code', width: 120, sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'systemType', index: 'systemType', hidden: true,width: 120, sortable: false},
            {name: 'systemTypeName', index: 'systemTypeName', width: 120, sortable: false},
            {name: 'orderTypeName', index: 'orderTypeName', width: 120, sortable: false},
            {name: 'studentChineseName', index: 'studentChineseName', width: 120, sortable: false},
            {name: 'studentID', index: 'studentID', width: 120, sortable: false},
            //{name: 'money', index: 'money', width: 120, sortable: false},
            //{name: 'discount', index: 'discount', width: 120, sortable: false},
            //{name: 'couponDiscount', index: 'couponDiscount', width: 120, sortable: false},
            //{name: 'crDiscount', index: 'crDiscount', width: 120, sortable: false},
            {name: 'payMoney', index: 'payMoney', width: 120, sortable: false},
            {name: 'statusName', index: 'statusName', width: 120, sortable: false},
            {name: 'status', index: 'status', width: 120,hidden: true,  sortable: false},
            {name: 'payTime', index: 'payTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'projectNames', index: 'projectNames', width: 120, sortable: false},
            {name: 'courseNumber', index: 'courseNumber', width: 80, sortable: false}
        ],
        //rowNum:0,
        rownumbers: true,
        hoverrows:false,
        multiselect: true,
        multiboxonly: true,
        viewrecords: true,
        rowList: [10, 20, 50, 100],
        pager: pager_selector,
        viewrecords:true,
        altRows: true,
        onSelectRow: selectRow,
        onSelectAll: selectRow,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });


    /**
     *  按enter搜索
     */
    $("#key").keypress(function (e) {
        var keyCode;
        if (e == null) {
            keyCode = event.keyCode;
        } else {
            keyCode = e.keyCode;
        }
        if (keyCode == 13) {
            search();
            e.preventDefault();
        }
    });


    $("#searchBtn").bind("click",function(){
        search();
    })

    $("#addBtn").bind("click",function(){
        window.location.href="/order/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href="/order/update.html?id="+id;

    })

    $("#infoBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href="/order/detail.html?id="+id;
    })

    //退课获取课程信息
    $("#refundBtn").bind("click",function(){
        $("#myModalLabel").html("退款");
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        var objRow =$("#grid-table").jqGrid('getRowData', id);
        $.ajax({
            url: '/order/detail.json?orderId='+id,
            type: 'get',
            dataType:'json',
            async:false,
            success: function (json, statusText, xhr, $form) {
                if(json != null){
                    var detail = json.orderDetailBean;
                    $("#studentId").val(json.studentId);
                    $("#status").val(4);//申请退款
                    $("#systemType").val(objRow.systemType);//订单系统
                    var html;
                    $("#details").empty();
                    $.each(detail,function(i,item){
                        html = '<div class="form-group">' +
                            '<label  class="col-sm-2 control-label">';
                        if($.trim(item.dormitoryId)==''){
                            //课程明细需要提起课程列表
                            html = html + item.projectName+'</label>';
                            html = html + '<div class="col-sm-10 courses">';
                            html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
                            if(item.courseIds != ''){
                                var courseIds = item.courseIds.split(",");
                                var courseNames = item.custerNames.split(",");
                                //遍历课程列表
                                $.each(courseIds,function(i,courseId){
                                    html = html + '<input name="courseids" type="checkbox" value="'+courseId+'"/>'+ courseNames[i]+'&nbsp;&nbsp;&nbsp;&nbsp;';
                                });
                            }

                        }else{
                            html = html + item.projectName+'</label>';
                            html = html + '<div class="col-sm-10 dormitory">';
                            html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
                            html = html + '<input name="dormitoryid" type="checkbox" value="'+item.dormitoryId+'"/>' + item.dormitoryName;
                        }
                        html = html + '</div></div>';

                        $("#details").append(html);
                    });
                }
            }
        });
    });


    //加课获取可以添加课程的列表
    $("#addOrderBtn").bind("click",function(){
        $("#myModalLabel").html("加课");
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        var objRow =$("#grid-table").jqGrid('getRowData', id);
        $.ajax({
            url: '/order/detail.json?orderId='+id,
            type: 'get',
            dataType:'json',
            async:false,
            success: function (json, statusText, xhr, $form) {
                if(json != null){
                    var detail = json.orderDetailBean;
                    $("#studentId").val(json.studentId);
                    $("#status").val(1);//提交订单
                    $("#systemType").val(objRow.systemType);//订单系统
                    var html;
                    $("#details").empty();
                    $.each(detail,function(i,item){
                        //html = '<div class="form-group">' +
                        //    '<label  class="col-sm-2 control-label">';
                        if($.trim(item.dormitoryId)==''){
                            //订单明细为课程
                            var courseIds = item.courseIds.split(",");
                            //去后台请求project下所有的课程列表
                            $.ajax({
                                url: '/course/getCourses.json?projectId='+item.projectId+"&systemType=" +json.systemType,
                                type: 'get',
                                dataType:'json',
                                success: function (data, statusText, xhr, $form) {
                                    //console.log(data);
                                    if(data != null){
                                        if(data.length != courseIds.length){
                                            //项目中可以进行加课
                                            html = '<div class="form-group">' +
                                                '<label  class="col-sm-2 control-label">';
                                            html = html + item.projectName+'</label>';
                                            html = html + '<div class="col-sm-10 courses">';
                                            html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
                                            $.each(data,function(i,course){
                                                if($.inArray(course.id.toString(), courseIds) == -1){
                                                    //没有选课
                                                    html = html + '<input name="courseids" type="checkbox"  value="'+course.id+'"/>'+ course.chineseName+'&nbsp;&nbsp;&nbsp;&nbsp;';
                                                }

                                            });
                                            html = html + '</div></div>';
                                            $("#details").append(html);
                                        }
                                    }
                                }
                            });

                            //Fixme 以前下单的还可以再选择住宿吗  添加住宿
                            //去后台请求project下所有的住宿信息
                            $.ajax({
                                url: '/dormitory/getDormitory.json?projectId='+item.projectId,
                                type: 'get',
                                dataType:'json',
                                success: function (data, statusText, xhr, $form) {
                                    if(data != null){
                                        //添加住宿
                                        html = '<div class="form-group">' +
                                            '<label  class="col-sm-2 control-label">';
                                        html = html + item.projectName+'</label>';
                                        html = html + '<div class="col-sm-10 courses">';
                                        html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
                                        html = html + '<input name="dormitoryid" type="checkbox"value="'+data.id+'"/>'+data.name;
                                        html = html + '</div></div>';
                                        $("#details").append(html);
                                    }
                                }
                            });
                        }

                    });
                }
            }
        });
    });


    $("#refundSubmitBtn").bind("click",function(){
        if($("#details").find("input[type='checkbox']:checked").length == 0){
            alert("请选择项目");
            return;
        }
        var order = {};
        order["studentId"] = $("#studentId").val();
        order["money"] = $("#money").val();
        order["status"] = $("#status").val();

        var orderDetailBean = [];
        $("#details").find(".courses").each(function(){
            var coursesObj = $(this).find("input[type='checkbox']:checked");
            if(coursesObj.length != 0){
                var detail = {};
                console.log($(this).find("input[name=projectId]").val());
                detail["projectId"] = $(this).find("input[name=projectId]").val();

                //遍历所有的课程
                var courseIds = "";
                coursesObj.each(function(){
                    courseIds += $(this).val()+",";
                });
                courseIds = courseIds.substring(0,courseIds.length-1);
                detail["courseIds"] = courseIds;
                orderDetailBean.push(detail);
            }
        });
        order["orderDetailBean"] =  orderDetailBean;

        console.log(order);

        $.ajax({
            url: '/order/add.json',
            type: 'post',
            async:false,
            dataType:'json',
            data: {"order":JSON.stringify(order)},
            tranditional:true,
            success: function (data) {
                if (data.success) {
                    alert("保存成功！");
                    $("#detailCanclBtn").click();
                    window.location.reload();
                } else {
                    alert("保存数据出现错误，请稍候重试！");
                }
            },
            error: function () {
                alert("提交保存信息出现错误！");
            }
        });
    })


    //已经取消的订单进行删除
    $("#deleteBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        if(id == null){
            alert("请选择记录!");
            return;
        }
        bootbox.confirm({
            message: "确定要删除该条记录?",
            callback: function(result) {
                if(result){

                    $.ajax({
                        url: '/order/delete.json?id='+id,
                        type: 'get',
                        dataType:'json',
                        success: function (json, statusText, xhr, $form) {
                            if (json.success) {
                                alert("删除完成!");
                                $("#grid-table").trigger('reloadGrid');
                            } else {
                                alert( json.message);
                            }
                        }
                    });
                }
            },
            className: "bootbox-sm"
        });
    })


    /**
     * 导入Excel
     */
    $("#importBtn").bind("click", function(){
        $("#resultMessage").html("");
        $("#showImportFile").click();
    })


    $("#importSubmitBtn").bind("click", function(){
        var formData = new FormData(document.getElementById("fileFrom"));
        $.ajax({
            url: '/order/import.json',
            data: formData,
            type: 'post',
            dataType: 'json',
            cache: false,
            processData:false,
            contentType:false,
            success: function (data) {
                if (data.success) {
                    //alert("导入数据成功！");
                    $("#resultMessage").html(data.message);
                    reloadGrid();
                } else {
                    $("#resultMessage").html(data.message);
                }
            },
            error: function () {
                alert("导入数据失败！");
            }
        });
    });

    $("#exportBtn").bind("click", function(){
        var studentName = $("#name").val();
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    $("#search-form").attr("action", "/order/export.json");
                    $("#search-form").submit();
                }
            },
            className: "bootbox-sm"
        });
    });

    $("#importBtn").bind("click", function(){
        $("#resultMessage").html("");
        $("#showImportFile").click();
    })
})

/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#infoBtn').addClass('disabled');

    reloadGrid();
}

function reloadGrid(){
    jQuery("#grid-table").jqGrid('setGridParam',{
        url: '/order/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson()
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
