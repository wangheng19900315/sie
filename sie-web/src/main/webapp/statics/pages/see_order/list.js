

var selectRows =new Array();

/**
 * 按钮有效性切换
 */
function selectRow() {
    var curpagenum = $("#grid-table").jqGrid('getGridParam', 'page');
    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
    selectRows[curpagenum-1]=ids;

    if (ids && ids.length == 1) {
        $('#infoBtn').removeClass('disabled');
    } else {
        $('#infoBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/order/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),
        colNames: ['ID','订单号','创建时间','systemType','提交系统','订单类型','学生姓名','学生ID','支付金额','订单状态','订单状态','支付时间','项目名称','课程数','住宿名称' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'code', index: 'code', width: 120, sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'systemType', index: 'systemType', hidden: true,width: 120, sortable: false},
            {name: 'systemTypeName', index: 'systemTypeName', width: 80, sortable: false},
            {name: 'orderTypeName', index: 'orderTypeName', width: 80, sortable: false},
            {name: 'studentChineseName', index: 'studentChineseName', width: 120, sortable: false},
            {name: 'studentID', index: 'studentID', width: 150, sortable: false},
            //{name: 'money', index: 'money', width: 120, sortable: false},
            //{name: 'discount', index: 'discount', width: 120, sortable: false},
            //{name: 'couponDiscount', index: 'couponDiscount', width: 120, sortable: false},
            //{name: 'crDiscount', index: 'crDiscount', width: 120, sortable: false},
            {name: 'payMoney', index: 'payMoney', width: 80, sortable: false},
            {name: 'statusName', index: 'statusName', width: 120, sortable: false},
            {name: 'status', index: 'status', width: 80,hidden: true,  sortable: false},
            {name: 'payTime', index: 'payTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'projectNames', index: 'projectNames', width: 120, sortable: false},
            {name: 'courseNumber', index: 'courseNumber', width: 80, sortable: false},
            {name: 'dormitoryNames', index: 'dormitoryNames', width: 200, sortable: false}
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

    $("#infoBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/order/detail.html?id="+id;
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
        url: pageRootPath+'/order/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),
        page:1
    }).trigger('reloadGrid');

}

