

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
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/send/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '学生姓名','学生ID','街道' ,'寄送县/市' ,'寄送州/市' ,'寄送邮编' ,'寄送联系人/部门','寄送电话','快递公司' ,'寄送单号'  ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'studentName', index: 'studentName', width: 20,   sortable: false},
            {name: 'userID', index: 'userID', width: 20,   sortable: false},
            {name: 'sendStreet', index: 'sendStreet', width: 20,   sortable: false},
            {name: 'sendCountry', index: 'sendCountry', width: 20,   sortable: false},
            {name: 'sendProvince', index: 'sendProvince', width: 20,  sortable: false},
            {name: 'sendPostCode', index: 'sendPostCode', width: 20,  sortable: false},
            {name: 'sendPerson', index: 'sendPerson', width: 20,  sortable: false},
            {name: 'sendTel', index: 'sendTel', width: 20, sortable: false},
            {name: 'expressCompany', index: 'expressCompany', width: 20 , sortable: false},
            {name: 'trackingNumber', index: 'trackingNumber', width: 20 , sortable: false}
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
        window.location.href=pageRootPath+"/send/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/send/addOrUpdate.html?id="+id;
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
                        url: pageRootPath+'/send/delete.json?id='+id,
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

    $("#infoBtn").bind("click",function(){
        search();
    })

    $("#importBtn").bind("click", function(){
        $("#resultMessage").html("");
        $("#showImportFile").click();
    })


    $("#submitBtn").bind("click", function(){
            var formData = new FormData(document.getElementById("fileFrom"));
            $.ajax({
                url: pageRootPath+'/send/import.json',
                data: formData,
                type: 'post',
                dataType: 'json',
                cache: false,
                processData:false,
                contentType:false,
                success: function (data) {
                    if (data.success) {
                        $("#resultMessage").html(data.message);
                        search();
                    } else {
                        $("#resultMessage").html(data.message);
                        //alert("保存数据出现错误，请稍候重试！");
                    }
                },
                error: function () {
                    alert("导入数据失败！");
                }
            });
    });

    $("#exportBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    $("#search-form").attr("action", "/send/export.json");
                    $("#search-form").submit();
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
        url: pageRootPath+'/send/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {
            //name: $("#name").val()
        }
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
