


var selectCourseId;
$(function(){

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
        colNames: ['ID','projectId','courseIds', '项目名','住宿名称','课程数'  ,'课程名', '操作'],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true, sorttype: "int", sortable: false},
            {name: 'projectId', index: 'projectId', width: 20, hidden: true,   sortable: false},
            {name: 'courseIds', index: 'courseIds', width: 20, hidden: true,  sortable: false},
            {name: 'projectName', index: 'projectName', width: 120, sortable: false},
            {name: 'dormitoryName', index: 'dormitoryName', width: 120, sortable: false},
            {name: 'courseCount', index: 'courseCount', width: 60, sortable: false},
            {name: 'custerNames', index: 'custerNames', width: 360, sortable: false},
            {name: 'operate', index: 'operate', width: 120, sortable: false, formatter:function(cellvalue, options, rowObject){
                if(rowObject.courseCount >0 ){
                    return "<a href='javascript:selectCourses("+rowObject.id+")'>选择课程</a>"
                }
                return "暂无操作";

            }
            }
        ],
        //multiselect: true,
        //multiboxonly: true,
        hoverrows:false,
        altRows: true,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });


    $("#detailSubmitBtn").bind("click", function(){
        var rowData = $("#grid-table").jqGrid('getRowData', selectCourseId);
        var courseCount = rowData.courseCount;
        var courseIds = "";
        var count = 0;
        $("input[name=courseIds]").each(function(){
            if($(this).attr("checked")){
                courseIds += $(this).val()+",";
                count++;
            }
        })
        if(courseCount < count){
            alert("课程选择的数目不对，请重新选择");
            return;
        }
        courseIds = courseIds.substring(0,courseIds.length-1);
        var obj = {"id":selectCourseId,"courseIds":courseIds};
        $.ajax({
            url: '/order/updateCourseIds.json',
            type: 'post',
            async:false,
            dataType:'json',
            data:obj,
            success: function (data) {
                if (data.success) {
                    alert("修改成功！");
                    $("#detailCanclBtn").click();
                    window.location.reload();
                } else {
                    alert("保存数据出现错误，请稍候重试！");
                }
            },
            error: function () {
                alert("提交保存信息出现错误！");
            }
        });


    })



    //修改金额
    $("#discount").bind("change", function(){
        //if($(this).val()  == ""){
        //    alert("请输入折扣金额！");
        //    return;
        //}

        var payMonety = $("#money").val()-$("#crDiscount").val()-$("#couponDiscount").val()-$("#discount").val();
        if(payMonety  < 0){
            $("#payMoney").val(payMonety);
            alert("折扣金额大于总金额！");
            return;
        }
        $("#payMoney").val(payMonety);
    })


    $("#submitBtn").click(function(){
        //if($("#discount").val()  == ""){
        //    alert("请输入管理员折扣金额！");
        //    return;
        //}

        if( $("#payMoney").val() < 0){
            alert("折扣金额大于总金额！");
            return;
        }

        var formData = $("#data-form").serializeJson();
        $.ajax({
            url: '/order/updateOrderInfo.json',
            type: 'post',
            async:false,
            dataType:'json',
            data:formData,
            success: function (data) {
                if (data.success) {
                    alert("修改成功！");
                    $("#detailCanclBtn").click();
                    history.go(-1);
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

function selectCourses(id){
    selectCourseId = id;
    var rowData = $("#grid-table").jqGrid('getRowData', id);
    var projectId = rowData.projectId;
    var courseIds = rowData.courseIds.split(",");
    var systemType = $("#systemType").val();
    $.ajax({
        url: '/course/getCourses.json?projectId='+projectId+"&systemType=" +systemType,
        type: 'get',
        dataType:'json',
        async:false,
        success: function (data, statusText, xhr, $form) {
            $("#courseNameDivs").empty();
            var courseChecks;
            if(data != null){
                $.each(data,function(i,course){
                    courseChecks = '<div class="row" style="margin-bottom: 10px;">' +
                            '<input name="courseIds" type="checkbox" value="'+course.id+'"';
                    //判断课程是否已经选课
                    console.log(courseIds);
                    if($.inArray(course.id.toString(), courseIds) > -1){
                        courseChecks += 'checked';
                    }
                    courseChecks += ' />'+course.chineseName + '</div>';
                    $("#courseNameDivs").append(courseChecks);
                });
            }
            //绑定事件
            //$("input[name=courseIds]").each(function(){
            //    $(this).bind("click",function(){
            //        //状态切换
            //        if ($(this).is(":checked")) {
            //            alert(true);
            //            //$(this).prop("checked") == false
            //            $(this).prop('checked',true);//设置属性
            //        } else {
            //            alert(false);
            //            //$(this).removeAttrs("checked");
            //            $(this).prop("checked",false);
            //        }
            //        alert($("input[name=courseIds][checked=true]").length);
            //        //if($("input[name=courseIds][checked]").length >= courseNum){
            //        //    //设置为不可用
            //        //    //$("input[name=courseIds]").attr('disabled');
            //        //}else{
            //        //    //把不可用的checkbox变为可用
            //        //    $("input[name=courseIds][disabled]").removeAttrs('disabled');
            //        //}
            //    });
            //});
        }
    });
    $("#selectBtn").click();
}





