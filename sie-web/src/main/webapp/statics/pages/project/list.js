

var selectRows =new Array();

/**
 * 按钮有效性切换
 */
function selectRow() {
    var curpagenum = $("#grid-table").jqGrid('getGridParam', 'page');
    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
    selectRows[curpagenum-1]=ids;

    if (ids && ids.length == 1) {
        $('#editBtn').removeClass('disabled');
        $('#deleteBtn').removeClass('disabled');
        $('#infoBtn').removeClass('disabled');
    } else {
        $('#editBtn').addClass('disabled');
        $('#deleteBtn').addClass('disabled');
        $('#infoBtn').addClass('disabled');
    }

    if(ids && ids.length > 0){
        $('#exportStudentCourseBtn').removeClass('disabled');
    }else{
        $('#exportStudentCourseBtn').addClass('disabled');
    }
};
$(function(){

    var grid_selector = "#grid-table";
    var pager_selector = "#grid-pager";

    jQuery(grid_selector).jqGrid({
        url: pageRootPath+'/project/list.json',
        datatype: "json",
        height: '100%',
        mtype: 'post',
        postData: {},
        colNames: ['ID','项目编号', '所在系统','SIE系统名称' ,'SIE系统最多报课程数' ,'TRU系统名称' ,'TRU系统最多报课程数','开始时间' ],
        colModel: [
            {name: 'id', index: 'id', width: 20, hidden: true,  sortable: false},
            {name: 'code', index: 'code', width: 20,   sortable: false},
            {name: 'systemName', index: 'systemName', width: 20,   sortable: false},
            {name: 'sieName', index: 'sieName', width: 20,   sortable: false},
            {name: 'sieMaxCourse', index: 'sieMaxCourse', width: 20,  sortable: false},
            {name: 'truName', index: 'truName', width: 20,  sortable: false},
            {name: 'truMaxCourse', index: 'truMaxCourse', width: 20,  sortable: false},
            {name: 'startTimeFormat', index: 'startTimeFormat', width: 20, sortable: false}
        ],
        rownumbers: true,
        hoverrows:false,
        multiselect: true,
        multiboxonly: true,
        viewrecords: true,
        rowList: [10, 20, 50, 100],
        pager: pager_selector,
        viewrecords:true,
        altRows: true,
        onSelectRow: selectRow,
        onSelectAll: selectRow,
        loadComplete: function () {
            jqGrid.initWidth(jQuery, '#grid-table', ".table-primary");
            jqGrid.reset(jQuery);
        }
    });


    /**
     *  按enter搜索
     */
    $("#key").keypress(function (e) {
        var keyCode;
        if (e == null) {
            keyCode = event.keyCode;
        } else {
            keyCode = e.keyCode;
        }
        if (keyCode == 13) {
            search();
            e.preventDefault();
        }
    });


    $("#searchBtn").bind("click",function(){
        search();
    })

    $("#addBtn").bind("click",function(){
        window.location.href=pageRootPath+"/project/addOrUpdate.html"
    })


    $("#editBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        window.location.href=pageRootPath+"/project/addOrUpdate.html?id="+id;
    })

    $("#deleteBtn").bind("click",function(){
        var id = $("#grid-table").jqGrid('getGridParam', 'selrow');
        if(id == null){
            alert("请选择记录!");
            return;
        }
        bootbox.confirm({
            message: "确定要删除该条记录?",
            callback: function(result) {
                if(result){

                    $.ajax({
                        url: pageRootPath+'/project/delete.json?id='+id,
                        type: 'get',
                        dataType:'json',
                        success: function (json, statusText, xhr, $form) {
                            if (json.success) {
                                alert("删除完成!");
                                $("#grid-table").trigger('reloadGrid');
                            } else {
                                alert( json.message);
                            }
                        }
                    });
                }
            },
            className: "bootbox-sm"
        });
    })

    //绑定导出事件
    $("#exportBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    var url = pageRootPath+"/project/export.json";
                    var params = $("#search-form").getGetMethodUrl();
                    if(params != ''){
                        url += "?" + params;
                    }
                    window.location.href = url;
                }
            },
            className: "bootbox-sm"
        });
    });

    //绑定导出学生课程信息
    $("#exportStudentCourseBtn").bind("click", function(){
        bootbox.confirm({
            message: "确认要导出excel?",
            callback: function(result) {
                if(result){
                    var ids = $("#grid-table").jqGrid('getGridParam', 'selarrrow');
                    $.each(ids,function(i,item){
                        var url = pageRootPath+"/project/exportStudentCourse.json?projectId="+item;
                        window.open(url, "学生课程下载"+i);
                    });
                }
            },
            className: "bootbox-sm"
        });
    });


    //报名项目获取后台可报名项目数据
    $("#registrationProjectBtn").bind("click",function(){
        $.ajax({
           url: pageRootPath+'/project/getProjects.json',
           type: 'get',
           dataType:'json',
           async:false,
           success: function (json, statusText, xhr, $form) {
               var html = '';
               var checked = '';
               if(json != null){
                   // console.log(json);
                   $.each(json,function (i,item) {
                        if(item.checked){
                            checked = 'checked';
                        }else{
                            checked = '';
                        }
                       html = html +
                             '<div class="form-group">' +
                                 '<div class="col-sm-12 twoProject">' +
                                    '<input type="hidden" name="projectOneId" value="'+ item.projectOneId +'">' +
                                    '<input type="hidden" name="projectTwoId" value="' + item.projectTwoId + '">' +
                                    '<input type="checkbox" '+ checked +'>'+ item.projectsName +
                                 '</div>'+
                             '</div>';

                   });
                   $('#registration-form').empty();
                   $('#registration-form').append(html);
               }
           }
        });
    });

    $("#registrationProjectSubmitBtn").bind("click",function(){
        if($("#registration-form").find("input[type='checkbox']:checked").length == 0){
            alert("请选择组合项目");
            return;
        }
        var twoProject = {};
        twoProject["projectOneId"] = $("#studentId").val();
        twoProject["projectTwoId"] = $("#money").val();
        var twoProjects = [];
        $("#registration-form").find(".twoProject").each(function(){
            var checkBox = $(this).find("input[type='checkbox']");
            console.log(checkBox);
            if(checkBox.is(':checked')){
                var twoProject = {};
                twoProject["projectOneId"] = $(this).find("input[name='projectOneId']").val();
                twoProject["projectTwoId"] = $(this).find("input[name='projectTwoId']").val();
                twoProjects.push(twoProject);
            }
        });

        console.log(twoProjects);

        $.ajax({
            url: pageRootPath+'/project/saveRegistrationProjects.json',
            type: 'post',
            async:false,
            dataType:'json',
            data: {"registrationProjects":JSON.stringify(twoProjects)},
            tranditional:true,
            success: function (data) {
                if (data.success) {
                    alert("保存成功！");
                    $("#registrationCancelBtn").click();
                    //window.location.reload();
                } else {
                    alert("保存数据出现错误，请稍候重试！");
                }
            },
            error: function () {
                alert("提交保存信息出现错误！");
            }
        });
    })

})


/**
 * 查询
 */
function search() {
    $('#edit-btn').addClass('disabled');
    $('#del-btn').addClass('disabled');
    $('#view-btn').addClass('disabled');

    jQuery("#grid-table").jqGrid('setGridParam',{
        postData: $("#search-form").serializeJson()
    }).trigger('reloadGrid');

}

/**
 * 重置
 */
function reset() {
    $("#name").val("");
}
