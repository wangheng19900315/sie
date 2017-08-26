$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = new FormData(document.getElementById("data-form"));
            //var formData = $("#data-form").serializeJson();
            $.ajax({
                url: '/student/addOrupdate.json',
                data: formData,
                type: 'post',
                dataType: 'json',
                cache: false,
                processData:false,
                contentType:false,
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

    if(entity.length > 0){
        var json = eval("("+entity+")");

        var birthDay =    new Date(json.birthday).Format("yyyy-MM-dd");
        json.birthday=birthDay;
        $("#data-form").loadJson(json);
    }

})

