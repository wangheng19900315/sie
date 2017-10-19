
$(function(){
	if(!judgeLogin()){
		return;
	}

	var attrs={};
	attrs.studentId=userInfo.id + "";
	attrs.systemType=systemType;
	/**
	 * 获取我的已经完成的订单
	 */
	dhcc.Unit.ajaxUtil(attrs,"getCompleteOrderList.json",function(data){
		var orderCode = $("#order-code").next();
		var orderCodeLi = '';
		$.each(data,function(i,item){
			orderCodeLi = orderCodeLi +
				'<li li-value="'+ item.id +'">' + item.code + '</li>';
		});
		orderCode.empty();
		orderCode.append(orderCodeLi);
		initSelect();

		if(orderCodeLi != ''){
			orderCode.find("li:first").click();
		}
	});

	$("#refund-button").bind("click",function(){
		var params = $("#data-form").serializeJson();
		params.id = $("#order-code").attr("li-value");
		params.studentId=userInfo.id + "";
		params.systemType=systemType;
		console.log(params);

		/**
		 * 提交退款
		 */
		dhcc.Unit.ajaxUtil(params,"refundOrder.json",function(data){
			dhcc.Unit.successMessage("提交成功",function(){
				//页面进行跳转
				//window.location.href = "project-registration.html";
			});
		});
	});
});

//初始化select方法
function initSelect(){
	var orderCode = $("#order-code");
	orderCode.next().find("li").click(function() {
		if ($(this).attr("class") !="disabled") {
			var txt = $(this).text();
			var key = $(this).attr("li-value");
			var oldKey = $(this).parent().prev().attr("li-value");
			$(this).parent().prev().val(txt);
			$(this).parent().prev().attr("li-value",key);
		}
	});
}