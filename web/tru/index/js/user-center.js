
$(function(){
	if(!judgeLogin()){
		return;
	}

	var attrs={};
	attrs.studentId=userInfo.id + "";
	attrs.systemType=systemType;

	/**
	 * 获取我的课程
	 */
	dhcc.Unit.ajaxUtil(attrs,"getComAndSubOrderCourse.json",function(data){
		var courses = '<tr><th>课程时间</th>' +
			' <th>课程代码</th>' +
			'<th>课程名称</th>' +
			'<th>校区</th> ' +
			'<th>教室</th> </tr>';
		$.each(data,function(i,course){
			course = removeNull(course);
			courses = courses +
				'<tr> <td><label>' + course.startTime + ' -- ' + course.endTime +'</label></td>' +
				'<td><label>' + course.code + '</label></td>' +
				'<td><label>' + course.englishName + '</label></td>' +
				'<td><label>' +course.schoolName + '</label></td> ' +
				'<td><label>' +course.classroom + '</label></td> </tr>';
		});
		var courseTable = $($(".table")[0]);
		courseTable.empty();
		courseTable.append(courses);
	});
	/**
	 * 获取我的订单
	 */
	dhcc.Unit.ajaxUtil(attrs,"getLatestOrderInfo.json",function(data){
		var orders = '<tr> <th>订单编号</th>' +
			'<th>状态</th>' +
			'<th>支付方式</th>' +
			'<th>项目</th>' +
			'<th>课程数目</th>' +
			'<th>住宿</th>' +
			'<th>总价</th> </tr>';
		data = removeNull(data);
		orders = orders +
			'<tr><td><label>'+ data.code + '</label></td>'+
			'<td><label>' + data.statusName + '</label></td>'+
			'<td><label>' + data.payTypeName + '</label></td>'+
			'<td><label>' + data.projectNames + '</label></td>'+
			'<td><label>' + data.courseNumber + '</label></td>'+
			'<td><label>' + data.dormitoryNames + '</label></td>'+
			'<td><label>' + data.payMoney + '</label></td></tr>';
		var orderTable = $($(".table")[1]);
		orderTable.empty();
		orderTable.append(orders);
	});
});