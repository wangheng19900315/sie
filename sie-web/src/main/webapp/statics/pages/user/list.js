

var selectRows =new Array();

/**
 * 按钮有效性切换
 */
function selectRow() {
    var curpagenum = $("#grid-table").jqGrid('getGridParam', 'page');
    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
    selectRows[curpagenum-1]=ids;

    //if (ids && ids.length == 1) {
    //    $('#edit-btn').removeClass('disabled');
    //    $('#del-btn').removeClass('disabled');
    //    $('#view-btn').removeClass('disabled');
    //} else {
    //    $('#edit-btn').addClass('disabled');
    //    $('#del-btn').addClass('disabled');
    //    $('#view-btn').addClass('disabled');
    //}
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/wisdom/monitor/list.ajax',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '创建时间' ,'创建人' ,'类型' ,'作业' ,'地址' ,'描述' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'createUserName', index: 'createUserName', width: 120, sortable: false},
            {name: 'typeName', index: 'typeName', width: 120, sortable: false},
            {name: 'jobName', index: 'jobName', width: 120, sortable: false},
            {name: 'url', index: 'url', width: 120, sortable: false, formatter:function(cellvalue, options, rowObject){
                var result = "<a href='javascript:logView(\""+cellvalue+"\")'>监控链接</a>";
                return result;
            }
            },
            {name: 'description', index: 'description', width: 120, sortable: false},

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
            jqGrid.initWidth(jQuery, '.jd-table', ".jd-table-parent");
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



})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#view-btn').addClass('disabled');

    jQuery("#grid-table").jqGrid('setGridParam',{
        url: '/wisdom/monitor/list.ajax',
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