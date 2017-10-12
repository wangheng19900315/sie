
$(function(){

    /**
     * 加载成绩单寄送信息
     */
    var attrs={};
    attrs.studentId=studentId;
    /**
     * 获取学生信息
     */
    dhcc.Unit.ajaxUtil(attrs,"getStudnetInfo.json",function(data){
        $("#data-form").loadJson(data);
        //设置中文姓名 性别 出生日期信息
        $("#chineseName").text(data.chineseName);
        $("#sex").text(data.sex);
        $("#birthday").text(new Date(data.birthday).Format("yyyy-MM-dd"));
    });

    $("#saveSendInfo").bind("click",function(){
        //按钮不可用
        $("#saveSendInfo").attr("disabled", true);
        var params = $("#data-form").serializeJson();
        attrs=params;
        dhcc.Unit.ajaxUtil(attrs,"saveGradeSend.json",function(data){
            $('#modal-success').modal('show');
            //等待1.5秒后消失
            setTimeout(function(){$('#modal-success').modal('hide');}, 1500);
        });
        $("#saveSendInfo").attr("disabled", false);
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
