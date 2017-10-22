
$(function(){
	if(!judgeLogin()){
		return;
	}


	/**
	 * 加载订单信息
	 */
	var attrs={};
	attrs.studentId=userInfo.id + "";
	attrs.systemType=systemType;
	attrs.orderStatus="1";
	/**
	 * 获取我的订单
	 */
	dhcc.Unit.ajaxUtil(attrs,"getOrderList.json",function(data){

		if(data){
			var html = "";
			for(var name in data){
				var objs = data[name];
				for(var i=0; i<objs.length; i++){

					var obj = objs[i];
					obj = removeNull(obj);
					html = html +
						'<tr><td><label>'+ obj.code + '</label></td>'+
						'<td><label>' + obj.statusName + '</label></td>'+
						'<td><label>' + obj.projectNames + '</label></td>'+
						'<td><label>' + obj.courseNumber + '</label></td>'+
						'<td><label>' + obj.dormitoryNames + '</label></td>'+
						'<td><label>' + obj.payMoney + '</label></td></tr>';
				}
			}




			$("#orderTable").append(html);
		}
	});
});