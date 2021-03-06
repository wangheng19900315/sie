$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            console.log(formData);
            $.ajax({
                url: pageRootPath+'/course/addOrupdate.json',
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
        initProjectOption();
    });

    ////时间格式初始化
    //var options = {
    //    minuteStep: 1,
    //    showSeconds: true,
    //    showMeridian: false,
    //    showInputs: false,
    //    orientation: $('body').hasClass('right-to-left') ? { x: 'right', y: 'auto'} : { x: 'auto', y: 'auto'}
    //}
    //$('.form_time').timepicker(options);

    initSchoolOption();
    if(entity.length > 0){
        var entityJson = eval("("+entity+")");
        $("#data-form").loadJson(entityJson);
        //出发select选中事件
        $("#system").change();
        $("#projectId").val(entityJson.projectId);
    }else{
        $("#system").change();
    }

});

var initSchoolOption = function(){
    //初始化校区
    var html = '';
    $.each(JSON.parse(schools),function(key,value){
        html = html + '<option value="'+ key + '">' + value + '</option>';
    });
    $("#school").empty();
    $("#school").append(html);
}

var initProjectOption = function(){
    $("#projectId").empty();
    //加载项目
    $.ajax({
        url: pageRootPath+'/project/getAllProject.json',
        data: {"system":$("#system").val()},
        type: 'post',
        async: false,
        dataType: 'json',
        cache: false,
        success: function (data) {
            var projectOption;
            $.each(data, function (key, value) {
                projectOption += '<option value="' + key + '">' + value + "</option>";
            });
            $("#projectId").append(projectOption);
        },
        error: function () {
            alert("加载项目失败！");
        }
    });
};


