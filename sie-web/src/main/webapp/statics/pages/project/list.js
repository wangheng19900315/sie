

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
        $('#deleteBtn').removeClass('disabled');
        $('#infoBtn').removeClass('disabled');
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#infoBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/project/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','项目编号', '所在系统','SIE系统名称' ,'SIE系统最多报课程数' ,'TRU系统名称' ,'TRU系统最多报课程数','开始时间' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'code', index: 'code', width: 20,   sortable: false},
            {name: 'systemName', index: 'systemName', width: 20,   sortable: false},
            {name: 'sieName', index: 'sieName', width: 20,   sortable: false},
            {name: 'sieMaxCourse', index: 'sieMaxCourse', width: 20,  sortable: false},
            {name: 'truName', index: 'truName', width: 20,  sortable: false},
            {name: 'truMaxCourse', index: 'truMaxCourse', width: 20,  sortable: false},
            {name: 'startTimeFormat', index: 'startTimeFormat', width: 20, sortable: false}
        ],
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
        window.location.href=pageRootPath+"/project/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/project/addOrUpdate.html?id="+id;
    })

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
                        url: pageRootPath+'/project/delete.json?id='+id,
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

    //绑定导出事件
    $("#exportBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    var url = pageRootPath+"/project/export.json";
                    var params = $("#search-form").getGetMethodUrl();
                    if(params != ''){
                        url += "?" + params;
                    }
                    window.location.href = url;
                }
            },
            className: "bootbox-sm"
        });
    })

    //报名项目获取后台可报名项目数据
    $("#registrationProjectBtn").bind("click",function(){
        //var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        //var objRow =$("#grid-table").jqGrid('getRowData', id);
        //$.ajax({
        //    url: pageRootPath+'/order/detail.json?orderId='+id,
        //    type: 'get',
        //    dataType:'json',
        //    async:false,
        //    success: function (json, statusText, xhr, $form) {
        //        if(json != null){
        //            var detail = json.orderDetailBean;
        //            $("#studentId").val(json.studentId);
        //            $("#status").val(4);//申请退款
        //            $("#systemType").val(json.systemType);//订单系统
        //            var html;
        //            $("#details").empty();
        //            $.each(detail,function(i,item){
        //                html = '<div class="form-group">' +
        //                    '<label  class="col-sm-2 control-label">';
        //                if($.trim(item.dormitoryId)==''){
        //                    //课程明细需要提起课程列表
        //                    html = html + item.projectName+'</label>';
        //                    html = html + '<div class="col-sm-10 courses">';
        //                    html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
        //                    if(item.courseIds != ''){
        //                        var courseIds = item.courseIds.split(",");
        //                        var courseNames = item.custerNames.split(",");
        //                        //遍历课程列表
        //                        $.each(courseIds,function(i,courseId){
        //                            html = html + '<input name="courseids" type="checkbox" value="'+courseId+'"/>'+ courseNames[i]+'&nbsp;&nbsp;&nbsp;&nbsp;';
        //                        });
        //                    }
        //
        //                }else{
        //                    html = html + item.projectName+'</label>';
        //                    html = html + '<div class="col-sm-10 dormitory">';
        //                    html = html + '<input type="hidden" name="projectId" value="'+item.projectId+'">';
        //                    html = html + '<input name="dormitoryid" type="checkbox" value="'+item.dormitoryId+'"/>' + item.dormitoryName;
        //                }
        //                html = html + '</div></div>';
        //
        //                $("#details").append(html);
        //            });
        //        }
        //    }
        //});
    });

})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#view-btn').addClass('disabled');

    jQuery("#grid-table").jqGrid('setGridParam',{
        postData: $("#search-form").serializeJson()
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
