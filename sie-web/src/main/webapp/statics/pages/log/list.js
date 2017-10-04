

var selectRows =new Array();

$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/log/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','操作人', '操作地址','备注' ,'时间'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'userId', index: 'userId', width: 20,   sortable: false},
            {name: 'operateUrl', index: 'operateUrl', width: 20,   sortable: false},
            {name: 'comment', index: 'comment', width: 100,    formatter:function(cellvalue, options, rowObject){
                return "<span>"+cellvalue+"</span>";
             }
            },
            {name: 'createTime', index: 'createTime', width: 20 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            }
        ],
        rownumbers: true,
        hoverrows:false,
        viewrecords: true,
        rowList: [10, 20, 50, 100],
        pager: pager_selector,
        viewrecords:true,
        altRows: true,
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

})


/**
 * 查询
 */
function search() {
    jQuery("#grid-table").jqGrid('setGridParam',{
        postData: $("#search-form").serializeJson()
    }).trigger('reloadGrid');

}
