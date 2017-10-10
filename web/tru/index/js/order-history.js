var termOrder;
$(function(){
	/**
	 * 加载订单信息
	 */
	var attrs={};
	attrs.studentId="1";
	attrs.systemType=systemType;
	/**
	 * 获取我的订单
	 */
	dhcc.Unit.ajaxUtil(attrs,"getOrderList.json",function(data){
		termOrder = data;
		var term = $("ul[aria-labelledby='term']");
		var termLi = '';
		//初始化学期
		$.each(termOrder,function(key,item){
			termLi = termLi +
					'<li>' + key + '</li>';
		});
		term.empty();
		term.append(termLi);
		//var orders = '<tr> <th>订单编号</th>' +
		//			'<th>状态</th>' +
		//			'<th>支付方式</th>' +
		//			'<th>项目</th>' +
		//			'<th>课程数目</th>' +
		//			'<th>住宿</th>' +
		//			'<th>总价</th> </tr>';
		//var courses = '<tr> <th>课程代码</th>' +
		//				'<th>课程名称</th>' +
		//				'<th>校区</th> </tr>';
		//$.each(data,function(i,order){
		//	orders = orders +
		//		'<tr><td><label>'+ order.code + '</label></td>'+
		//		'<td><label>' + order.statusName + '</label></td>'+
		//		'<td><label>' + order.payTypeName + '</label></td>'+
		//		'<td><label>' + order.projectNames + '</label></td>'+
		//		'<td><label>' + order.courseNumber + '</label></td>'+
		//		'<td><label>' + order.dormitoryNames + '</label></td>'+
		//		'<td><label>' + order.payMoney + '</label></td></tr>';
		//	$.each(order.courses,function(ii,course){
		//		courses = courses +
		//				'<tr> <td><label>' + course.courseID + '</label></td>' +
		//				'<td><label>' + course.truCode + '</label></td>' +
		//				'<td><label>' +course.schoolName + '</label></td> </tr>';
		//	});
        //
		//});
		//var orderTable = $($(".table")[0]);
		//orderTable.empty();
		//orderTable.append(orders);
        //
		//var courseTable = $($(".table")[1]);
		//courseTable.empty();
		//courseTable.append(courses);
	});
})