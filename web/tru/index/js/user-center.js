$(function(){

    /**
     * 获取我的课程
     */
    var attrs={};
    attrs.studentId="1";
    attrs.systemType="1";
    //dhcc.Unit.ajaxUtil(attrs,"getOrderList.json",function(data){
    //
    //})

    /**
     * 获取我的订单
     */
    dhcc.Unit.ajaxUtil(attrs,"getOrderList.json",function(data){
        //alert(data);
    })


})