

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
function Modify(id) {   //单击修改链接的操作
    var model = jQuery("#grid-table").jqGrid('getRowData', id);
    //alert(model.Id);
    $("#editname").val(model.name);
    //$("#txtCityName").val(model.Name);
    //$("#txtFID").val(model.Fid);
    $("#modifyform").dialog({
        height:230,
        width: 400,
        modal:true,  //这里就是控制弹出为模态
        resizable:false,
        buttons:{
            "确定":function(){
                alert("在这里对数据进行修改！");
                $(this).dialog("close");
            },
            "取消":function(){$(this).dialog("close");}
        }
        //height:230,
        //width:400,
        //resizable:false,
        //modal:true,  //这里就是控制弹出为模态
        //buttons:{
        //    "确定":function(){
        //        alert("在这里对数据进行修改！");
        //        $(this).dialog("close");
        //    },
        //    "取消":function(){$(this).dialog("close");}
        //}
    });
};
function Delete(id) { //单击删除链接的操作
    alert("在这里执行异步删除操作，此时点击了id为 "+id+" 的行！");
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/menu/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '名称' ,'创建时间' ,'修改时间','修改' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'name', index: 'name', width: 120, sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'updateTime', index: 'updateTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
                }
            },
            {name:'Modify',index:'id',width:80,align:"center",sortable:false}
            //{name:'Delete',index:'id',width:80,align:'center',sortable:false}
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

            //在此事件中循环为每一行添加修改和删除链接
            var ids=jQuery("#grid-table").jqGrid('getDataIDs');
            for(var i=0; i<ids.length; i++){
                var id=ids[i];
                modify = "<a href='#' style='color:#f60' onclick='Modify(" + id + ")'>修改</a>";  //这里的onclick就是调用了上面的javascript函数 Modify(id)
                //del = "<a href='#'  style='color:#f60' onclick='Delete(" + id + ")' >删除</a>";
                jQuery("#grid-table").jqGrid('setRowData', ids[i], { Modify: modify });
            }

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
        window.location.href="/menu/add.html"
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
        url: '/role/list.json',
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