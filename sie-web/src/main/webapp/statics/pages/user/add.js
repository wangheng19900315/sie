$(function(){



    $("#submitBtn").click(function(){


        var formData = $("#data-form").serializeJson();
        $.ajax({
            url: '/user/addOrupdate.json',
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
    })
})

