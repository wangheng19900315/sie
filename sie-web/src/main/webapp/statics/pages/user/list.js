

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
        $('#selectRoleBtn').removeClass('disabled');
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#selectRoleBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/user/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),

        colNames: ['ID','角色id', '名称' ,'角色名称' ,'Email','电话','创建时间' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'roleEntity.id', index: 'roleEntity.id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'name', index: 'name', width: 120, sortable: false},
            {name: 'roleEntity.name', index: 'roleEntity.name', width: 120, sortable: false},
            {name: 'telephone', index: 'telephone', width: 120, sortable: false},
            {name: 'email', index: 'email', width: 120, sortable: false},

            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            }
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
        window.location.href=pageRootPath+"/user/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/user/addOrUpdate.html?id="+id;

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
                        url: pageRootPath+'/user/delete.json?id='+id,
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

    $.ajax({
        url: pageRootPath+'/role/getSelect.json',
        type: 'get',
        cache: false,
        success: function (data) {
            if(data.length > 0){
                $("#roleId").html(data);
            }
        }
    });

    $("#selectRoleBtn").bind("click", function(){
        $("#showSelectRoles").click();

        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        var objRow =$("#grid-table").jqGrid('getRowData', id);
        $("#roleId option").each(function(){
            if($(this).val() == objRow["roleEntity.id"]){
                $(this).attr("selected","selected");
            }else{
                $(this).attr("selected","");
            }
        })
        $("#id").val(id);

    })

    $("#submitBtn").bind("click", function(){
        var obj = {id:$("#id").val(),roleId:$("#roleId").val()};
        $.ajax({
            url: pageRootPath+'/user/updateRole.json',
            data: obj,
            type: 'post',
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.success) {
                    alert("数据保存成功！");
                    $("#cancelBtn").click();
                    search();
                } else {
                    alert("保存数据出现错误，请稍候重试！");
                }
            },
            error: function () {
                alert("提交保存信息出现错误！");
            }
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
        url: pageRootPath+'/user/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: $("#search-form").serializeJson(),
        page:1
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
