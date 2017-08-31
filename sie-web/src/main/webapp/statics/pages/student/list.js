

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
        colNames: ['ID','用户名','用户名ID','中文名称' ,'性别' ,'微信号' ,'出生日期','国籍','护照号' ,'身份证号'  ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'userName', index: 'userName', width: 20,   sortable: false},
            {name: 'userID', index: 'userID', width: 20,   sortable: false},
            {name: 'chineseName', index: 'chineseName', width: 20,   sortable: false},
            {name: 'sex', index: 'sex', width: 20,  sortable: false},
            {name: 'weiXin', index: 'weiXin', width: 20,  sortable: false},
            {name: 'birthday', index: 'birthday', width: 20,  sortable: false},
            {name: 'nationality', index: 'nationality', width: 20, sortable: false},
            {name: 'passportNumber', index: 'passportNumber', width: 20, sortable: false},
            {name: 'idNumber', index: 'idNumber', width: 20 , sortable: false}
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
        window.location.href="/student/update.html?id="+id;
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
