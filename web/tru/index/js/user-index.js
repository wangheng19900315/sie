function application(){
    var userInfo = getCookie("loginUser");
    if(userInfo){
        window.location.href="../user/application.html";
    }else{
        $("#login_modal_btn").click()
    }
}