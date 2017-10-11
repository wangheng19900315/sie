$(function(){

    var userInfo = getCookie("loginUser");
    if(userInfo){
        $($(".login-box").children("div").get(0)).hide();
        $($(".login-box").children("div").get(1)).show();
    }else{
        $($(".login-box").children("div").get(0)).show();
        $($(".login-box").children("div").get(1)).hide();
    }


    //登录按钮

    $("#loginBtn").bind("click", function(){

        var email = $("#login_emiail").val();
        var password = $("#login_password").val();
        var attrs={"userName":email,"password":password};

        dhcc.Unit.ajaxUtil(attrs,"login.json",function(data){
            setCookie("loginUser",data);
        },function(data){
            alert("登录失败");
        })
    })
})
