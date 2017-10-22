$(function(){

    userInfo = getCookie("loginUser");

    if(userInfo){
        $("#fixedMenu").find(".language a").each(function(i, data){
           if(i<2){
               $(this).hide();
           }else{
               $(this).show();
           }
        })
        userInfo = eval("("+userInfo+")")
        $(".username").html(userInfo.email);
    }else{
        $("#fixedMenu").find(".language a").each(function(i, data){
            if(i<2){
                $(this).show();
            }else{
                $(this).hide();
            }
        })

    }


    //登录按钮

    $("#loginBtn").bind("click", function(){
        var email = $("#login_emiail").val();
        var password = $("#login_password").val().trim();

        $("#login_emiail").next().html("");
        $("#login_password").next().html("");
        if(email == ""){
            $("#login_emiail").next().html("<label>邮箱不能为空</label>");
            return;
        }
        if(!checkEmail(email)){
            $("#login_emiail").next().html("<label>邮箱格式不正确</label>");
            return;
        }
        if(password == ""){
            $("#login_password").next().html("<label>密码不能为空</label>");
            return;
        }
        if(password.length < 6 ){
            $("#login_password").next().html("<label>密码长度不能小于6</label>");
            return;
        }
        var attrs={"userName":email,"password":password};

        dhcc.Unit.ajaxUtil(attrs,"login.json",function(data){
            setCookie("loginUser",JSON.stringify(data));
            userInfo = data;
            dhcc.Unit.successMessage("登录成功", function(){
                window.location.reload();
            });

        },function(data){
            $("#login_password").next().html(data);
        })
    })

    $("#registerBtn").bind("click", function(){
        var email = $("#reggsiter_email").val();
        var password = $("#reggsiter_password").val().trim();
        var repeatPassword = $("#reggsiter_repeat_password").val().trim();

        $("#reggsiter_email").next().html("");
        $("#reggsiter_password").next().html("");
        $("#reggsiter_repeat_password").next().html("");
        if(email == ""){
            $("#reggsiter_email").next().html("邮箱不能为空");
            return;
        }
        if(!checkEmail(email)){
            $("#reggsiter_email").next().html("邮箱格式不正确");
            return;
        }
        if(password == ""){
            $("#reggsiter_password").next().html("密码不能为空");
            return;
        }
        if(password.length < 6 ){
            $("#reggsiter_password").next().html("密码长度不能小于6");
            return;
        }
        if(repeatPassword == ""||repeatPassword != password){
            $("#reggsiter_repeat_password").next().html("密码不一致");
            return;
        }
        var attrs={"email":email,"password":password};

        dhcc.Unit.ajaxUtil(attrs,"register.json",function(data){
            dhcc.Unit.successMessage("注册成功", function(){
                $('#register_login_btn').click();
            });

        },function(data){
            $("#reggsiter_repeat_password").next().html(data);
        })
    })

    $(".userout").bind("click", function(){
        $("#user-head").click();
        delCookie("loginUser");
        dhcc.Unit.successMessage("退出登录");
        $($(".login-box").children("div").get(0)).show();
        $($(".login-box").children("div").get(1)).hide();
        setTimeout(function(){  window.location.href="../index/index.html";}, 1500);
    })

    $("#modifyPasswordBtn").bind("click", function(){
        var password = $("#modifyPassword").val().trim();
        var newPassword = $("#modifyNewPassword").val().trim();
        var repeatPassword = $("#modifyNewPasswordRepeat").val().trim();

        $("#modifyPassword").next().html("");
        $("#modifyNewPassword").next().html("");
        $("#modifyNewPasswordRepeat").next().html("");

        if(password == ""){
            $("#modifyPassword").next().html("密码不能为空");
            return;
        }
        if(password.length < 6 ){
            $("#modifyPassword").next().html("密码长度不能小于6");
            return;
        }
        if(newPassword == ""){
            $("#modifyNewPassword").next().html("新密码不能为空");
            return;
        }
        if(newPassword.length < 6 ){
            $("#modifyNewPassword").next().html("新密码长度不能小于6");
            return;
        }
        if(repeatPassword == ""||repeatPassword != newPassword){
            $("#modifyNewPasswordRepeat").next().html("密码不一致");
            return;
        }
        var attrs={"userName":userInfo.email,"password":password,"newPassword":newPassword};

        dhcc.Unit.ajaxUtil(attrs,"modifyPassword.json",function(data){
            dhcc.Unit.successMessage("密码修改成功");
            dhcc.Unit.hideModal("modal-psd");
        },function(data){
            $("#modifyNewPasswordRepeat").next().html(data);
        })
    })


    $("#resetPasswordBtn").bind("click", function(){
        var email = $("#reset_email").val().trim();
        $("#reset_email").next().html("");

        if(email == ""){
            $("#reset_email").next().html("邮箱不能为空");
            return;
        }
        if(!checkEmail(email)){
            $("#reset_email").next().html("邮箱格式不正确");
            return;
        }
        var attrs={"userName":email};

        dhcc.Unit.ajaxUtil(attrs,"resetPassword.json",function(data){
            dhcc.Unit.successMessage("新密码已发送至邮箱，请验收");
            dhcc.Unit.hideModal("modal_reseat_password");
        },function(data){
            $("#reset_email").next().html(data);
        })
    })
})
