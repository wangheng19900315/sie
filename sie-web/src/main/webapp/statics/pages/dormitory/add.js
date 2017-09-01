$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            $.ajax({
                url: '/dormitory/addOrupdate.json',
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


    //加载项目
    $.ajax({
        url: '/project/getAllProject.json',
        data: {},
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
    if(entity.length > 0){
        $("#data-form").loadJson(eval("("+entity+")"));
    }

})

var initProjectOption = function(){
    //初始化project select框
    projects = JSON.parse(projects);
    console.log(projects);

};

