var school;
$(function(){

    //绑定国籍变化事件
    $('input[type=radio][name=nationality]').change(function() {
        if (this.value == '中国') {
            $("input[name='idNumber']").parent().show();
            $("input[name='passportNumber']").parent().hide();
        }
        else if (this.value == '非中国') {
            $("input[name='idNumber']").parent().hide();
            $("input[name='passportNumber']").parent().show();
        }
    });

    /**
     * 加载学生信息
     */
    var attrs={};
    attrs.studentId="1";
    /**
     * 获取我的订单
     */
    dhcc.Unit.ajaxUtil(attrs,"getStudnetInfo.json",function(data){
        var birthDay =    new Date(data.birthday).Format("yyyy-MM-dd");
        data.birthday=birthDay;
        $("#data-form").loadJson(data);
        $("input[name='nationality'][value='" + data.nationality + "']").change();//出发change事件
        //加载照片
        if (data.image != null && data.image != undefined && data.image != '') {
            var params = {"image":data.image};
            //$('#uploadCard').attr('src', rootPath + 'loadImage?params=' + JSON.stringify(params) + '&accessToken='+accessToken );
            //$("#test").trigger("click")
        }
    });

    $("#saveApplication").bind("click",function(){
        var params = $("#data-form").serializeJson();
        console.log(params)
        attrs=params;
        dhcc.Unit.ajaxUtil(attrs,"saveApplicationForm.json",function(data){

        });
    });

    //
    ////加载学校列表
    //$.ajax({
    //    url: pageRootPath+'/student/university_list.json',
    //    type: 'post',
    //    dataType: 'json',
    //    async:false,
    //    cache: false,
    //    processData:false,
    //    contentType:false,
    //    success: function (data) {
    //        var schools;
    //        $.each(data,function(i,value){
    //            schools = schools + '<option value="'+ value +'">' + value +'</option>';
    //        });
    //        $("#schoolName").append(schools);
    //    },
    //    error: function () {
    //        alert("加载学校失败！");
    //    }
    //});
    //
    //
    ////加载学生信息
    //if(entity.length > 0){
    //    var json = eval("("+entity+")");
    //
    //    var birthDay =    new Date(json.birthday).Format("yyyy-MM-dd");
    //    json.birthday=birthDay;
    //    $("#data-form").loadJson(json);
    //    //加载照片
    //    if (json.image != null && json.image != undefined && json.image != '') {
    //        $('#ImgPr').attr('src', pageRootPath + '/student/loadImage?image=' + json.image);
    //    }
    //}
    //
    ////绑定学校保存按钮
    //$("#college-select-button").bind("click",function(){
    //    var setinput = $(".chosen-search").find("input");
    //})


})
