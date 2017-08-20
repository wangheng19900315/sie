$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            console.log(formData);
            $.ajax({
                url: '/course/addOrupdate.json',
                data: formData,
                type: 'post',
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (data.success) {
                        alert("数据保存成功！");
                        history.go(-1);
                    } else {
                        alert("保存数据出现错误，请稍候重试！");
                    }
                },
                error: function () {
                    alert("提交保存信息出现错误！");
                }
            });
        }
    });

    //绑定系统选择事件
    $("#system").bind("change",function(){
        alert(1);
        var type = $("#system").val();
        //清空原有配置

        if(type == "1"){
            $("#sie").show();
            $("#tru").hide();
        }else if(type == "2"){
            $("#sie").hide();
            $("#tru").show();
        }else if(type == "3"){
            $("#sie").show();
            $("#tru").show();
        }else{
            //有误
        }
    });

    //日期格式初始化
    $('.form_time').datetimepicker({
        language: 'zh-CN',//显示中文
        format: "yyyy-mm-dd hh:ii:ss",
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 1,
        minView: 0,
        maxView: 1,
        forceParse: 0
    });

    //出发select选中事件
    $("#system").change();
    if(entity.length > 0){
        $("#data-form").loadJson(eval("("+entity+")"));
    }

    initProjectOption();
});

var initProjectOption = function(){
    //初始化project select框
    projects = JSON.parse(projects);
    console.log(projects);
    var projectOption;
    $.each(projects, function (key, value) {
        projectOption += '<option value="' + key + '">' + value + "</option>";
    });
    $("#projectId").append(projectOption);
};


