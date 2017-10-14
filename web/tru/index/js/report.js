var termGrade;
$(function(){

    /**
     * 加载成绩单寄送信息
     */
    var attrs={};
    attrs.studentId=studentId;
    /**
     * 获取学生信息
     */
    dhcc.Unit.ajaxUtil(attrs,"getStudnetInfo.json",function(data){
        $("#data-form").loadJson(data);
        //设置中文姓名 性别 出生日期信息
        $("#chineseName").text(data.chineseName);
        $("#sex").text(data.sex);
        $("#birthday").text(new Date(data.birthday).Format("yyyy-MM-dd"));
    });

    attrs={};
    attrs.studentId=studentId;
    attrs.systemType = systemType;
    /**
     * 获取学生成绩单
     */
    dhcc.Unit.ajaxUtil(attrs,"getReportList.json",function(data){
        //初始化学期
        termGrade = data;
        var term = $("#term").next();
        var termLi = '';
        //初始化学期
        $.each(termGrade,function(key,item){
            termLi = termLi +
                '<li>' + key + '</li>';
        });
        term.empty();
        term.append(termLi);
        initSelect();

        //默认选中第一个学期
        if(termLi != ''){
            term.find("li:first").click();
        }
    });

    $("#saveSendInfo").bind("click",function(){
        //按钮不可用
        $("#saveSendInfo").attr("disabled", true);
        var params = $("#data-form").serializeJson();
        attrs=params;
        dhcc.Unit.ajaxUtil(attrs,"saveGradeSend.json",function(data){
            dhcc.Unit.successMessage("提交成功");
        });
        $("#saveSendInfo").attr("disabled", false);
    });

})

//初始化select方法
function initSelect(onChange){
    $(".dropdown-select li").click(function() {
        if ($(this).attr("class") !="disabled") {
            var txt = $(this).text();
            var oldText = $(this).parent().prev().val();
            $(this).parent().prev().val(txt);
            if(txt != oldText){
                changeTerm(txt);
            }
        }
    });
}

function changeTerm(term){
    var termGrades = termGrade[term];
    var grades = '<tr> <th>课程代码</th>' +
        '<th>课程中文名称</th>' +
        '<th>课程英文名称</th>' +
        '<th>成绩</th> </tr>';
    $.each(termGrades,function(i,grade){
        grade = removeNull(grade);
        grades = grades +
            '<tr><td><label>'+ grade.courseCode + '</label></td>'+
            '<td><label>' + grade.courseChineseName + '</label></td>'+
            '<td><label>' + grade.courseEnglishName + '</label></td>'+
            '<td><label>' + grade.grade + '</label></td></tr>';
    });
    $(".table").empty();
    $(".table").append(grades);
}
