


var selectCourseId;
$(function(){

    $("#orderBtn").click(function(){
        $("#orderInfo").show();
        $("#orderDetailInfo").hide();

    })
    $("#orderDetailBtn").click(function(){
        $("#orderInfo").hide();
        $("#orderDetailInfo").show();
        jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
        jqGrid.reset(jQuery);
    });
    $("#orderBtn").click();

    var jsonData = "";
    $.ajax({
        url: '/order/detail.json?orderId='+id,
        type: 'get',
        dataType:'json',
        async:false,
        success: function (json, statusText, xhr, $form) {
            if(json != null){
                json.createTime = new Date(json.createTime).Format("yyyy-MM-dd hh:mm:ss");
                json.payTime = new Date(json.payTime).Format("yyyy-MM-dd hh:mm:ss");
                $("#data-form").loadJson(json);
                jsonData = json.orderDetailBeen;
            }
        }
    });

    var grid_selector = "#grid-table";

    jQuery(grid_selector).jqGrid({
        url: '#',
        datatype: "local",
        data:jsonData,
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','projectId','courseIds', '项目名','创建时间','课程数'  ,'课程名' ,'金额' ,'状态', '操作'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'projectId', index: 'projectId', width: 20, hidden: true,   sortable: false},
            {name: 'courseIds', index: 'courseIds', width: 20, hidden: true,  sortable: false},
            {name: 'projectName', index: 'projectName', width: 120, sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'courseCount', index: 'courseCount', width: 120, sortable: false},
            {name: 'custerNames', index: 'custerNames', width: 120, sortable: false},
            {name: 'total', index: 'total', width: 120, sortable: false},
            {name: 'orderDetailStatusName', index: 'orderDetailStatusName', width: 120, sortable: false},
            {name: 'operate', index: 'operate', width: 120, sortable: false, formatter:function(cellvalue, options, rowObject){
                if(rowObject.courseCount >0 ){
                    return "<a href='javascript:selectCourses("+rowObject.id+")'>选择课程</a>"
                }
                return "暂无操作";

            }
            }
        ],
        multiselect: true,
        multiboxonly: true,
        altRows: true,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });


    $("#detailSubmitBtn").bind("click", function(){
        var rowData = $("#grid-table").jqGrid('getRowData', selectCourseId);

        var courseIds = "";
        $("input[type=checkbox]").each(function(){
            if($(this).attr("checked")){
                courseIds += $(this).val();
            }
        })
        var obj = {"id":selectCourseId,"courseIds":courseIds};
        $.ajax({
            url: '/course/getCourseCheckbox.json',
            type: 'post',
            async:false,
            dataType:'json',
            data:obj,
            success: function (data) {
                if(data.success){
                    alert("修改成功")
                }
            }
        });


    })



})

function selectCourses(id){
    selectCourseId = id;
    var rowData = $("#grid-table").jqGrid('getRowData', id);
    var custerNames = rowData.custerNames;
    var courseCount = rowData.courseCount;
    var projectId = rowData.projectId;
    var courseIds = rowData.courseIds;
    var systemType = $("#systemType").val();
    $.ajax({
        url: '/course/getCourseCheckbox.json?projectId='+projectId+"&systemType=" +systemType,
        type: 'get',
        async:false,
        success: function (data, statusText, xhr, $form) {
            if(data != null && data.length > 0){
                $("#courseNameDivs").html(data);
            }
        }
    });
    $("#selectBtn").click();
}





