$(function(){

    userInfo = getCookie("loginUser");
    if(userInfo){
        $($(".login-box").children("div").get(0)).hide();
        $($(".login-box").children("div").get(1)).show();
        userInfo = eval("("+userInfo+")")
        $(".username").html(userInfo.email);
    }else{
        $($(".login-box").children("div").get(0)).show();
        $($(".login-box").children("div").get(1)).hide();
    }


    //登录按钮

    $("#loginBtn").bind("click", function(){
        var email = $("#login_emiail").val();
        var password = $("#login_password").val();

        $("#login_emiail").next().html("");
        $("#login_password").next().html("");
        if(email == ""){
            $("#login_emiail").next().html("邮箱不能为空");
            return;
        }
        if(password == ""){
            $("#login_password").next().html("密码不能为空");
            return;
        }
        var attrs={"userName":email,"password":password};

        dhcc.Unit.ajaxUtil(attrs,"login.json",function(data){
            setCookie("loginUser",JSON.stringify(data));
            userInfo = data;
            $($(".login-box").children("div").get(0)).hide();
            $($(".login-box").children("div").get(1)).show();
            $("#loginCloseBtn").click();
            dhcc.Unit.successMessage("登录成功");
        },function(data){
            $("#login_password").next().html(data);
        })
    })

    $("#registerBtn").bind("click", function(){
        var email = $("#reggsiter_email").val();
        var password = $("#reggsiter_password").val();
        var repeatPassword = $("#reggsiter_repeat_password").val();

        $("#reggsiter_email").next().html("");
        $("#reggsiter_password").next().html("");
        $("#reggsiter_repeat_password").next().html("");
        if(email == ""){
            $("#reggsiter_email").next().html("邮箱不能为空");
            return;
        }
        if(password == ""){
            $("#reggsiter_password").next().html("密码不能为空");
            return;
        }
        if(repeatPassword == ""||repeatPassword != password){
            $("#reggsiter_repeat_password").next().html("密码不一致");
            return;
        }
        var attrs={"email":email,"password":password};

        dhcc.Unit.ajaxUtil(attrs,"register.json",function(data){
            dhcc.Unit.successMessage("注册成功");
            $('a[title="注册"]').click();
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
    })
})
