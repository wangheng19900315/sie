var school;
$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = new FormData(document.getElementById("data-form"));
            //var formData = $("#data-form").serializeJson();
            $.ajax({
                url:pageRootPath+ '/student/addOrupdate.json',
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

    //加载学校列表
    $.ajax({
        url: pageRootPath+'/student/university_list.json',
        type: 'post',
        dataType: 'json',
        async:false,
        cache: false,
        processData:false,
        contentType:false,
        success: function (data) {
            var schools;
            $.each(data,function(i,value){
                schools = schools + '<option value="'+ value +'">' + value +'</option>';
            });
            $("#schoolName").append(schools);
        },
        error: function () {
            alert("加载学校失败！");
        }
    });


    //加载学生信息
    if(entity.length > 0){
        var json = eval("("+entity+")");

        var birthDay =    new Date(json.birthday).Format("yyyy-MM-dd");
        json.birthday=birthDay;
        $("#data-form").loadJson(json);
        //加载照片
        if (json.image != null && json.image != undefined && json.image != '') {
            $('#ImgPr').attr('src', pageRootPath + '/student/loadImage?image=' + json.image);
        }
    }

    //绑定学校保存按钮
    $("#college-select-button").bind("click",function(){
        var setinput = $(".chosen-search").find("input");
    })


})

