$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            console.log(formData);
            $.ajax({
                url: '/project/addOrupdate.json',
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

    //绑定课程数量发生变化事件
    $("#sieMaxCourse").bind("change", function(){
        var num = $("#sieMaxCourse").val();
        if(num == ''){
            num = 0;
        }
        //编辑num增加价格
        for(var i=0;i<num;i++){
            $("#sie_price_"+i).show();
        }
        for(var i=num;i<5;i++){
            $("#sie_price_"+i).hide();
        }
    });
    $("#truMaxCourse").bind("change", function(){
        var num = $("#truMaxCourse").val();
        if(num == ''){
            num = 0;
        }
        //编辑num增加价格
        for(var i=0;i<num;i++){
            $("#tru_price_"+i).show();
        }
        for(var i=num;i<5;i++){
            $("#tru_price_"+i).hide();
        }
    });
    //绑定系统选择事件
    $("#system").bind("change",function(){
        var type = $("#system").val();
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

    if(entity.length > 0){
        $("#data-form").loadJson(eval("("+entity+")"));
        var jsonObj = eval("("+entity+")");
        //设置sie价格
        $.each(jsonObj.siePrice,function(i,value){
            if(value != null){
                $("#siePrice_" + i +"_courseNumber").val(value.courseNumber);
                $("#siePrice_" + i + "_rmbPrice").val(value.rmbPrice);
                $("#siePrice_" + i + "_dollarPrice").val(value.dollarPrice);
                $("#siePrice_" + i + "_canadianPrice").val(value.canadianPrice);
            }
        });
        //设置tru价格
        $.each(jsonObj.truPrice,function(i,value){
            if(value != null){
                $("#truPrice_" + i +"_courseNumber").val(value.courseNumber);
                $("#truPrice_" + i + "_rmbPrice").val(value.rmbPrice);
                $("#truPrice_" + i + "_dollarPrice").val(value.dollarPrice);
                $("#truPrice_" + i + "_canadianPrice").val(value.canadianPrice);
            }
        });
    }

    //出发select选中事件
    $("#system").change();
    $("#sieMaxCourse").change();
    $("#truMaxCourse").change();

})

