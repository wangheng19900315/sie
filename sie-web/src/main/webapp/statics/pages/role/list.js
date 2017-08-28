

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
        $('#selectMenu').removeClass('disabled');
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#selectMenu').addClass('disabled');
    }
};
function Modify(id) {   //单击修改链接的操作
};
function Delete(id) { //单击删除链接的操作
    alert("在这里执行异步删除操作，此时点击了id为 "+id+" 的行！");
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/role/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','menuIds', '名称' ,'创建时间' ,'修改时间'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'menuIds', index: 'menuIds', width: 120, sortable: false,hidden: true},
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
        window.location.href="/role/add.html"
    })


    $("#editBtn").bind("click",function(){
        search();
    })

    $("#deleteBtn").bind("click",function(){
        search();
    })

    $("#selectMenu").bind("click", function(){
        $("#showMenubtn").click();
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        var obj = $("#grid-table").jqGrid('getRowData', id);
        $("#selectMenuDiv").find("input[type=checkbox]").attr("checked",false);
        if(obj.menuIds.length  > 0){
            var strs = obj.menuIds.split(",");
            for(var i=0; i<strs.length; i++){
                $("#selectMenuDiv").find("input[value="+strs[i]+"]").attr("checked","checked");
            }
        }


        $("#id").val(id);
    })


    $(".panel-heading input").bind("click", function(){
        var checked = $(this).attr("checked");
        if(!checked){
            checked=false;
        }

        $(this).parent().next().find("input").attr("checked",checked);
    })

    $(".panel-collapse input").bind("click", function(){
        var checked = $(this).attr("checked");
        if(!checked){
            var flag = false;
            $(this).parent().find("input").each(function(){
                if($(this).attr("checked")){
                    flag = true;
                    $(this).parent().parent().prev().find("input").attr("checked", "checked");
                }

                if(!flag){
                    $(this).parent().parent().prev().find("input").attr("checked", false);
                }
            })


        }else{
            $(this).parent().parent().prev().find("input").attr("checked", checked);
        }

    })

    $(".panel-heading input").bind("click", function(){
        var checked = $(this).attr("checked");
        if(!checked){
            checked=false;
        }

        $(this).parent().next().find("input").attr("checked",checked);
    })

    $("#submitBtn").bind("click", function(){
        var menuIds = "";
        $("input[name='menuId']").each(function(){
            if($(this).attr("checked")){
                menuIds += $(this).val()+",";
            }
        })

        if(menuIds.length > 0){
            menuIds = menuIds.substring(0, menuIds.length -1);
        }


        var obj ={"id":$("#id").val(),"menuIds":menuIds}
        $.ajax({
            url: '/role/updateMenu.json',
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