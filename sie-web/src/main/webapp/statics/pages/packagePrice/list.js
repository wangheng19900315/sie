

$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: '/packagePrice/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '项目数','课程数','系统' ,'人民币','美金' ,'加币'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'projectNumber', index: 'projectNumber', width: 20,   sortable: false},
            {name: 'courseNumber', index: 'courseNumber', width: 20,   sortable: false},
            {name: 'systemName', index: 'systemName', width: 20,   sortable: false},
            {name: 'rmbPrice', index: 'rmbPrice', width: 20,   sortable: false,editable:true, edittype:"text",editrules:{required: true, number: true}},
            {name: 'dollarPrice', index: 'dollarPrice', width: 20,  sortable: false,editable:true, edittype:"text",editrules:{required: true, number: true}},
            {name: 'canadianPrice', index: 'canadianPrice', width: 20,   sortable: false,editable:true, edittype:"text",editrules:{required: true, number: true}}
        ],
        //multiselect: true,
        multiboxonly: true,
        viewrecords: true,
        rowList: [10, 20, 50, 100],
        pager: pager_selector,
        viewrecords:true,
        altRows: true,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        },
        ondblClickRow: function(id){//进行行编辑
            alert(1);
            var rowData = $(grid_selector).jqGrid("getRowData", id);
            $(grid_selector).jqGrid('editRow',id,{
                keys : true,        //这里按[enter]保存
                url: "/packagePrice/update.json",
                mtype : "POST",
                extraparam: {
                    "id": rowData.id,
                    "rmbPrice": $("#"+id+"_rmbPrice").val(),
                    "dollarPrice": $("#"+id+"_dollarPrice").val(),
                    "canadianPrice": $("#"+id+"_canadianPrice").val()
                },
                oneditfunc: function(rowid){
                },
                successfunc: function(response){
                    if(response.success){
                        return true;
                    }else{
                        alert("保存失败");
                    }
                },
                errorfunc: function(rowid, res){
                    alert("保存失败");
                }
            });
        }
    });

})

