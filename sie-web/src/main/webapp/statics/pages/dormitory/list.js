

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
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
    }
    if(ids && ids.length > 0){
        $('#exportStudentBtn').removeClass('disabled');
    }else{
        $('#exportStudentBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/dormitory/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),
        colNames: ['ID', '名称','编号','项目编号' ,'价格' ,'地址' ,'人数限制','总人数','女生人数' ,'男士人数'  ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'name', index: 'name', width: 20,   sortable: false},
            {name: 'code', index: 'code', width: 20,   sortable: false},
            {name: 'projectCode', index: 'projectCode', width: 20,   sortable: false},
            {name: 'price', index: 'price', width: 20,  sortable: false},
            {name: 'address', index: 'address', width: 20,  sortable: false},
            {name: 'maxNumber', index: 'maxNumber', width: 20,  sortable: false},
            {name: 'totalNumber', index: 'totalNumber', width: 20, sortable: false},
            {name: 'womanNumber', index: 'womanNumber', width: 20 , sortable: false},
            {name: 'manNumber', index: 'manNumber', width: 20 , sortable: false}
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
        window.location.href=pageRootPath+"/dormitory/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/dormitory/addOrUpdate.html?id="+id;
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
                        url: pageRootPath+'/dormitory/delete.json?id='+id,
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
    //$("#exportBtn").bind("click", function(){
    //    bootbox.confirm({
    //        message: "确认要导出excel?",
    //        callback: function(result) {
    //            if(result){
    //                var url = pageRootPath+"/dormitory/export.json";
    //                var params = $("#search-form").getGetMethodUrl();
    //                if(params != ''){
    //                    url += "?" + params;
    //                }
    //                window.location.href = url;
    //            }
    //        },
    //        className: "bootbox-sm"
    //    });
    //});
    //绑定导出学生事件
    $("#exportStudentBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
                    $.each(ids,function(i,item){
                        var url = pageRootPath+"/dormitory/exportStudent.json?dormitoryId="+item;
                        window.open(url, "学生下载"+i);
                    });
                }
            },
            className: "bootbox-sm"
        });
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
        url: pageRootPath+'/dormitory/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),
        page:1
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
