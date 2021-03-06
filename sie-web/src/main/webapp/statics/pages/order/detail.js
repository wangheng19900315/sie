



$(function(){

    $("#refundType").bind("change",function(){
        var ariaId = $("#refundType option:selected").attr("aria-id");
        $("#refundWayBank").hide();
        $("#refundWayAlipay").hide();
        $("#refundWayWechat").hide();
        $("#refundWayEMT").hide();
        $("#"+ariaId).show();
    });

    $("#status").bind("change",function(){
        $("#refund-div").hide();
        var status = $("#status option:selected").val();
        if(status == '3' || status == '4'){
            $("#refund-div").show();
        }

    });

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
        url: pageRootPath+'/order/detail.json?orderId='+id,
        type: 'get',
        dataType:'json',
        async:false,
        success: function (json, statusText, xhr, $form) {
            if(json != null){
                json.createTime = new Date(json.createTime).Format("yyyy-MM-dd hh:mm:ss");
                json.payTime = new Date(json.payTime).Format("yyyy-MM-dd hh:mm:ss");
                $("#data-form").loadJson(json);
                $("#userInfo").loadJson(json);
                $("#refundType").change();
                $("#status").change();
                jsonData = json.orderDetailBean;
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
        colNames: ['ID','projectId','courseIds', '项目名','住宿名称','课程数'  ,'课程名'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'projectId', index: 'projectId', width: 20, hidden: true,   sortable: false},
            {name: 'courseIds', index: 'courseIds', width: 20, hidden: true,  sortable: false},
            {name: 'projectName', index: 'projectName', width: 120, sortable: false},
            //{name: 'createTime', index: 'createTime', width: 120 , sortable: false, formatter:function(cellvalue, options, rowObject){
            //    var time1 = new Date(cellvalue).Format("yyyy-MM-dd hh:mm:ss");
            //    return time1;
            //}
            //},
            {name: 'dormitoryName', index: 'dormitoryName', width: 120, sortable: false},
            {name: 'courseCount', index: 'courseCount', width: 60, sortable: false},
            {name: 'custerNames', index: 'custerNames', width: 360, sortable: false}
        ],
        hoverrows:false,
        //multiselect: true,
        //multiboxonly: true,
        altRows: true,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });



})

