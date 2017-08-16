

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
        url: '/coupon/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '优惠编码' ,'优惠金额' ,'开始时间' ,'结束时间','状态','创建时间' ,'修改时间'  ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'code', index: 'code', width: 20,   sortable: false},
            {name: 'price', index: 'price', width: 20,  sortable: false},
            {name: 'startTime', index: 'startTime', width: 20,  sortable: false,formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }},
            {name: 'endTime', index: 'endTime', width: 20, sortable: false,formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }},
            {name: 'status', index: 'status', width: 20, sortable: false},
            {name: 'createTime', index: 'createTime', width: 20 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
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

    $("#addBtn").bind("click",function(){
        window.location.href="/coupon/add.html"
    })


    $("#editBtn").bind("click",function(){
        search();
    })

    $("#deleteBtn").bind("click",function(){
        search();
    })

    $("#infoBtn").bind("click",function(){
        search();
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
        url: '/coupon/list.json',
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
