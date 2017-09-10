$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            $.ajax({
                url:pageRootPath+ '/send/addOrupdate.json',
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

    initStudentOption();
    if(entity.length > 0){
        $("#data-form").loadJson(eval("("+entity+")"));
        console.log(eval("("+entity+")"));
        //判断订单是否为默认订单
        if($("#defaultSend").val() == 1){
            //设置学生 寄送信息不可更改
            $("#studentId").attr("disabled","disabled");
            $("#sendStreet").attr("disabled","disabled");
            $("#sendCountry").attr("disabled","disabled");
            $("#sendProvince").attr("disabled","disabled");
            $("#sendPostCode").attr("disabled","disabled");
            $("#sendPerson").attr("disabled","disabled");
            $("#sendTel").attr("disabled","disabled");
        }
    }

})

var initStudentOption = function(){
    //初始化student select框
    students = JSON.parse(students);
    var studentOption;
    $.each(students, function (key, value) {
        studentOption += '<option value="' + key + '">' + value + "</option>";
    });
    $("#studentId").append(studentOption);
};

