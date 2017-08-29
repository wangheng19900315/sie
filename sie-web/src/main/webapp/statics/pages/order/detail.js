



$(function(){

    $("#orderBtn").click(function(){
        $("#orderInfo").show();
        $("#orderDetailInfo").hide();

    })
    $("#orderDetailBtn").click(function(){
        $("#orderInfo").hide();
        $("#orderDetailInfo").show();
        jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
        jqGrid.reset(jQuery);
    });
    $("#orderBtn").click();

    var jsonData = "";
    $.ajax({
        url: '/order/detail.json?orderId='+id,
        type: 'get',
        dataType:'json',
        async:false,
        success: function (json, statusText, xhr, $form) {
            if(json != null){
                json.createTime = new Date(json.createTime).Format("yyyy-MM-dd hh:mm:ss");
                json.payTime = new Date(json.payTime).Format("yyyy-MM-dd hh:mm:ss");
                $("#data-form").loadJson(json);
                $("#userInfo").loadJson(json);
                jsonData = json.orderDetailBeen;
            }
        }
    });

    var grid_selector = "#grid-table";

    jQuery(grid_selector).jqGrid({
        url: '#',
        datatype: "local",
        data:jsonData,
        //height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID', '项目名','创建时间','课程数'  ,'课程名' ,'金额' ,'状态'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'projectName', index: 'projectName', width: 120, sortable: false},
            {name: 'createTime', index: 'createTime', width: 160 , sortable: false, formatter:function(cellvalue, options, rowObject){
                var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
                return time1;
            }
            },
            {name: 'courseCount', index: 'courseCount', width: 120, sortable: false},
            {name: 'custerNames', index: 'custerNames', width: 120, sortable: false},
            {name: 'total', index: 'total', width: 120, sortable: false},
            {name: 'orderDetailStatusName', index: 'orderDetailStatusName', width: 120, sortable: false}
        ],
        multiselect: true,
        multiboxonly: true,
        altRows: true,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });



})

