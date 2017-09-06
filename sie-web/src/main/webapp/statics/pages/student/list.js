

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
        url: '/student/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','用户ID','中文名称' ,'性别' ,'微信号' ,'在读大学','国内联系电话','Email' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            //{name: 'userName', index: 'userName', width: 20,   sortable: false},
            {name: 'userID', index: 'userID', width: 20,   sortable: false},
            {name: 'chineseName', index: 'chineseName', width: 20,   sortable: false},
            {name: 'sex', index: 'sex', width: 10,  sortable: false},
            {name: 'weiXin', index: 'weiXin', width: 20,  sortable: false},
            {name: 'schoolName', index: 'schoolName', width: 20,  sortable: false},
            {name: 'telephone', index: 'telephone', width: 20, sortable: false},
            {name: 'email', index: 'email', width: 20, sortable: false}
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


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href="/student/update.html?id="+id;
    })

    $("#infoBtn").bind("click",function(){
        search();
    })

    //绑定导出事件
    $("#exportBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    var url = "/student/export.json";
                    var params = $("#search-form").getGetMethodUrl();
                    if(params != ''){
                        url += "?" + params;
                    }
                    window.location.href = url;
                }
            },
            className: "bootbox-sm"
        });
    });

    /**
     * 导入Excel
     */
    $("#importBtn").bind("click", function(){
        $("#resultMessage").html("");
        $("#showImportFile").click();
    });
    //绑定导入事件
    $("#importSubmitBtn").bind("click", function(){
        var formData = new FormData(document.getElementById("fileFrom"));
        $.ajax({
            url: '/student/import.json',
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

})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    //$('#del-btn').addClass('disabled');
    //$('#view-btn').addClass('disabled');
    reloadGrid();

}

function reloadGrid(){
    var searData = $("#search-form").serializeJson();
    jQuery("#grid-table").jqGrid('setGridParam',{
        url: '/student/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: searData
    }).trigger('reloadGrid');
}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
