

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
        $('#infoBtn').removeClass('disabled');
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#infoBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/course/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '所在系统','中文名','英文名' ,'总报名人数','时间' ,'教授名称' ,'SIE课程编码','SIE最大学生数','SIE报名人数', 'TRU课程编码','TRU最大学生数','TRU报名人数'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'systemName', index: 'systemName', width: 20,   sortable: false},
            {name: 'chineseName', index: 'chineseName', width: 20,   sortable: false},
            {name: 'englishName', index: 'englishName', width: 20,   sortable: false},
            {name: 'totalNumber', index: 'totalNumber', width: 20,   sortable: false,formatter:function(cellvalue, options, rowObject){
                var total = rowObject.sieTotalNumber + rowObject.truTotalNumber;
                return total;
            }},
            {name: 'start_end', index: 'start_end', width: 20,  sortable: false,formatter:function(cellvalue, options, rowObject){
                var startTime = new Date(rowObject.startTime).Format("hh:mm:ss");
                var endTime = new Date(rowObject.endTime).Format("hh:mm:ss");
                return startTime + "-" + endTime;
            }},
            {name: 'projectId', index: 'projectId', width: 20,  sortable: false},
            {name: 'sieCode', index: 'sieCode', width: 20,  sortable: false},
            {name: 'sieMaxStudent', index: 'sieMaxStudent', width: 20,  sortable: false},
            {name: 'sieTotalNumber', index: 'sieTotalNumber', width: 20,  sortable: false},
            {name: 'truCode', index: 'truCode', width: 20,  sortable: false},
            {name: 'truMaxStudent', index: 'truMaxStudent', width: 20,  sortable: false},
            {name: 'truTotalNumber', index: 'truTotalNumber', width: 20,  sortable: false},
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
        window.location.href="/course/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href="/project/addOrUpdate.html?id="+id;
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
                        url: '/project/delete.json?id='+id,
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

})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#view-btn').addClass('disabled');

    jQuery("#grid-table").jqGrid('setGridParam',{
        url: '/project/list.json',
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
