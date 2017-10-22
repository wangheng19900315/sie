var school;
$(function(){

    (function ($) {
        $.extend($.validator.messages, {
            required: "× 必填项",
            remote: "请修正该信息",
            email: "非法邮件",
            url: "请输入合法的网址",
            date: "请输入合法的日期",
            dateISO: "请输入合法的日期 (ISO).",
            number: "请输入合法的数字",
            digits: "只能输入整数",
            creditcard: "请输入合法的信用卡号",
            equalTo: "请再次输入相同的值",
            accept: "请输入拥有合法后缀名的字符串",
            maxlength: $.validator.format("请输入一个长度最多是 {0} 的字符串"),
            minlength: $.validator.format("请输入一个长度最少是 {0} 的字符串"),
            rangelength: $.validator.format("请输入一个长度介于 {0} 和 {1} 之间的字符串"),
            range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
            max: $.validator.format("请输入一个最大为 {0} 的值"),
            min: $.validator.format("请输入一个最小为 {0} 的值")
        });
    }(jQuery));

    if(!judgeLogin()){
        return;
    }

    //添加校验规则
    $("#data-form").validate({
        //errorPlacement: function(error, element){
        //    if(element.attr("id")=="province"||element.attr("id")=="city"||element.attr("id")=="area"){
        //        var error_td = element.parent().parent('dd').next();
        //    }else{
        //        var error_td = element.parent('dd').next();
        //    }
        //    error_td.html("");
        //    error_td.append(error);
        //    element.addClass("user_regNok");
        //},
        //error:function(label,element){
        //    element.addClass("user_regNok");
        //},
        //success       : function(label,element){
        //    label.addClass('reg_validate_right').text('');
        //    element.removeClass("user_regNok");
        //},
        //submitHandler:function(form){
        //    console.info("submit:"+$(form).serializeArray());
        //    form.submit();
        //},
        //onkeyup: false,
        rules : {
            chineseName :{
                required: true,
                minlength: 2
            },
            email:{
                email:true
            }
        }
    });

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
    attrs.studentId=userInfo.id+"";

    initApplicationStep();

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
            //$('#uploadCard').attr('src',  );
            //$("#test").trigger("click")
            $(".file-img").html('<img src="" class="img-responsive" alt="头像">')
            $(".file-img").find("img").attr("src",rootPath + 'loadImage?params=' + JSON.stringify(params) + '&accessToken='+accessToken)
        }
    });

    $("#saveApplication").bind("click",function(){
        if(!$("#data-form").valid()){
            alert("验证失败");
            return;
        }
        //$("#saveApplication").attr("disabled", true);
        //var params = $("#data-form").serializeJson();
        //
        ////TODO 表单里面的image没有提交
        //attrs=params;
        //dhcc.Unit.ajaxFile(attrs,"headImage","saveApplicationForm.json",function(data){
        //    //等待1.5秒后消失
        //    dhcc.Unit.successMessage("提交成功",function(){
        //        //页面进行跳转
        //        window.location.href = "project-registration.html";
        //    });
        //});
    });

    dhcc.Unit.ajaxUtil({}, "getSchool.json",function(data){
        if(data && data.length > 0){
           var schools = {};
           for(var i=0; i<data.length; i++){
               var obj = data[i];
               if(schools[obj.nationality]){
                   if(schools[obj.nationality][obj.province]){
                       schools[obj.nationality][obj.province].push(obj.name)
                   }else{
                       schools[obj.nationality][obj.province]=[];
                       schools[obj.nationality][obj.province].push(obj.name);
                   }
               }else{
                   schools[obj.nationality]={};
                   schools[obj.nationality][obj.province]=[];
                   schools[obj.nationality][obj.province].push(obj.name);
               }

               $("#president").append("<option>"+obj.name+"</option>")
           }

            $('#president').chosen({allow_single_deselect: true});
            var chosen = $(".chosen-single");
            var setinput = $(".chosen-search").find("input");
            chosen.addClass('form-control');
            setinput.addClass('form-control');

           var i=0;
           for(var nationality in schools){
               var active = "";
               if(i==0){
                   active = 'active'
               }
               $(".college-tab ul").append(' <li role="presentation" class="'+active+'" ><a href="#college'+i+'" onclick="selectNation('+i+')" aria-controls="#college'+i+'" role="tab" data-toggle="tab">'+nationality+'</a></li>');

               $(".college-tab .tab-content").append(' <div role="tabpanel" class="tab-pane '+active+'" id="college'+i+'">'+
                  ' <div class="college-a"></div></div>')
               $("#college"+i).append('<div class="college-ul">'+
                   '<ul class="clearfix">')

                var n=0;
               for(var province in schools[nationality]){
                   $("#college"+i).find(".college-a").append('<a id="province'+i+n+'" onclick="seletProvince(\''+province+'\',\'province'+i+n+'\')" href="javascript:;">'+province+'</a>');
                  for(var j=0; j< schools[nationality][province].length; j++){
                      $("#college"+i).find(".clearfix").append('<li class="'+province+'"  onclick="selectSchool(\''+schools[nationality][province][j]+'\')"><i class="fa fa-university"></i>'+schools[nationality][province][j]+'</li>');
                  }
                  n++;
               }
               i++;
           }

            $("#college0").find(".college-a").children(":first").click();
        }
    });




    ////绑定学校保存按钮
    $("#select_school_btn").bind("click",function(){
        var school = $("#president_chosen a").find("span").html();
        $("#schoolName").val(school);
        dhcc.Unit.hideModal("modal-college");
    })


})

function selectNation(index){
    $("#college"+index).find(".college-a").children(":first").click();
}

function seletProvince(province, id){
    $("#"+id).parent().children().removeClass("active");
    $("#"+id).addClass("active");
    $(".college-tab .tab-content").find(".clearfix li").hide();
    $(".college-tab .tab-content").find(".clearfix ."+province).show();


}

function selectSchool(school){
    $("#schoolName").val(school);
    dhcc.Unit.hideModal("modal-college");
}
