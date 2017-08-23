

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
    } else {
        $('#editBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/grade/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '创建时间' ,'姓名' ,'系统','项目名' ,'课程名' ,'分数','修改时间'  ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'createTime', index: 'createTime', width: 20 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'studentName', index: 'studentName', width: 20,   sortable: false},
            {name: 'systemTypename', index: 'systemTypename', width: 20,   sortable: false},
            {name: 'projectName', index: 'projectName', width: 20,  sortable: false},
            {name: 'courseName', index: 'courseName', width: 20,  sortable: false},
            {name: 'grade', index: 'grade', width: 20, sortable: false},
            {name: 'updateTime', index: 'updateTime', width: 20 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            }
        ],
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
        window.location.href="/grade/addOrUpdate.html?id="+id;
    })



    $("#excelBtn").bind("click", function(){
        var studentName = $("#name").val();
        var page = $('#grid-table').getGridParam('page'); // current page
        var rows = $('#grid-table').getGridParam('rows'); // rows
        if(rows == null){
            rows=20;
        }
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    window.location.href="/grade/export.json?studentName="+studentName+"&rows="+rows+"&page="+page;
                }
            },
            className: "bootbox-sm"
        });
    })


})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#view-btn').addClass('disabled');

    jQuery("#grid-table").jqGrid('setGridParam',{
        url: '/grade/list.json',
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
