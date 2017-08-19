$(function(){
    $("#data-form").validate({
        submitHandler: function() {
            var formData = $("#data-form").serializeJson();
            console.log(formData);
            $.ajax({
                url: '/project/addOrupdate.json',
                data: formData,
                type: 'post',
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (data.success) {
                        alert("数据保存成功！");
                        history.go(-1);
                    } else {
                        alert("保存数据出现错误，请稍候重试！");
                    }
                },
                error: function () {
                    alert("提交保存信息出现错误！");
                }
            });
        }
    });

    if(entity.length > 0){
        $("#data-form").loadJson(eval("("+entity+")"));
    }

    var insertSie = function(){
        var startRow = '<div class="row">';
        var sieName = '<div class="col-sm-4">' +
                            '<div class="form-group ">' +
                                '<label for="sieName" class="col-sm-3 control-label">SIE项目名称</label>' +
                                '<div class="col-sm-9">' +
                                    '<input type="text" class="form-control" id="sieName" name="sieName" placeholder="sieName" required data-msg-required="请输入SIE系统名称" >' +
                                '</div>' +
                            '</div>' +
                        '</div>';
        var sieMaxCourse = '<div class="col-sm-4">' +
                                '<div class="form-group ">' +
                                    '<label for="sieMaxCourse" class="col-sm-3 control-label">SIE最大课程数</label>' +
                                    '<div class="col-sm-9">' +
                                        '<input type="text" class="form-control" id="sieMaxCourse" name="sieMaxCourse" placeholder="sieMaxCourse" required data-msg-required="请输入SIE最大课程数" data-rule-age="true" data-msg-age="请输入正确的整数">' +
                                    '</div>' +
                                '</div>' +
                            '</div>';
        var siePrice = '<div id="siePrice"></div>';
        var endRow = '</div>';
        $("#sie").append(startRow,sieName,sieMaxCourse,siePrice,endRow);
        //绑定课程数量发生变化事件
        $("#sieMaxCourse").bind("input propertychange", function(){
            alert("出发事件了");
            $("#siePrice").empty();
            var num = $("#sieMaxCourse").val();
            //编辑num增加价格
            for(var i=0;i<num;i++){
                var startRow = '<div class="row">'+
                                '<input type="hidden" name="siePrice[' + i + '].courseNumber" value="' + (i+1) + '">';
                var rmb = '<div class="col-sm-4">' +
                                '<div class="form-group ">' +
                                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(RMB)</label>' +
                                    '<div class="col-sm-9">' +
                                        '<input type="text" class="form-control" name="siePrice[' + i + '].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                                    '</div>' +
                                '</div>' +
                            '</div><!-- col-sm-4 -->';
                var dollar = '<div class="col-sm-4">' +
                    '<div class="form-group ">' +
                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(美金)</label>' +
                    '<div class="col-sm-9">' +
                    '<input type="text" class="form-control" name="siePrice[' + i + '].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                    '</div>' +
                    '</div>' +
                    '</div><!-- col-sm-4 -->';
                var canadian = '<div class="col-sm-4">' +
                    '<div class="form-group ">' +
                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(加币)</label>' +
                    '<div class="col-sm-9">' +
                    '<input type="text" class="form-control" name="siePrice[' + i + '].canadianPrice" placeholder="Canadian" required data-msg-required="请输加币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                    '</div>' +
                    '</div>' +
                    '</div><!-- col-sm-4 -->';
                var endRow = '</div>';
                $("#siePrice").append(startRow,rmb,dollar,canadian,endRow);
            }

        });
    };

    var insertTru = function(){
        var startRow = '<div class="row">';
        var truName = '<div class="col-sm-4">' +
            '<div class="form-group ">' +
            '<label for="truName" class="col-sm-3 control-label">TRU项目名称</label>' +
            '<div class="col-sm-9">' +
            '<input type="text" class="form-control" id="truName" name="truName" placeholder="truName" required data-msg-required="请输入TRU系统名称" >' +
            '</div>' +
            '</div>' +
            '</div>';
        var truMaxCourse = '<div class="col-sm-4">' +
            '<div class="form-group ">' +
            '<label for="truMaxCourse" class="col-sm-3 control-label">TRU最大课程数</label>' +
            '<div class="col-sm-9">' +
            '<input type="text" class="form-control" id="truMaxCourse" name="truMaxCourse" placeholder="truMaxCourse" required data-msg-required="请输入TRU最大课程数" data-rule-age="true" data-msg-age="请输入正确的整数">' +
            '</div>' +
            '</div>' +
            '</div>';
        var truPrice = '<div id="truPrice"></div>';
        var endRow = '</div>';
        $("#tru").append(startRow,truName,truMaxCourse,truPrice,endRow);
        //绑定课程数量发生变化事件
        $("#truMaxCourse").bind("input propertychange", function(){
            alert("出发事件了");
            $("#truPrice").empty();
            var num = $("#sieMaxCourse").val();
            //编辑num增加价格
            for(var i=0;i<num;i++){
                var startRow = '<div class="row">'+
                    '<input type="hidden" name="truPrice[' + i + '].courseNumber" value="' + (i+1) + '">';
                var rmb = '<div class="col-sm-4">' +
                    '<div class="form-group ">' +
                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(RMB)</label>' +
                    '<div class="col-sm-9">' +
                    '<input type="text" class="form-control" name="truPrice[' + i + '].rmbPrice" placeholder="RMB" required data-msg-required="请输入人民币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                    '</div>' +
                    '</div>' +
                    '</div><!-- col-sm-4 -->';
                var dollar = '<div class="col-sm-4">' +
                    '<div class="form-group ">' +
                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(美金)</label>' +
                    '<div class="col-sm-9">' +
                    '<input type="text" class="form-control" name="truPrice[' + i + '].dollarPrice" placeholder="Dollar" required data-msg-required="请输入美金价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                    '</div>' +
                    '</div>' +
                    '</div><!-- col-sm-4 -->';
                var canadian = '<div class="col-sm-4">' +
                    '<div class="form-group ">' +
                    '<label  class="col-sm-3 control-label">' + (i+1) + '门课程价格(加币)</label>' +
                    '<div class="col-sm-9">' +
                    '<input type="text" class="form-control" name="truPrice[' + i + '].canadianPrice" placeholder="Canadian" required data-msg-required="请输加币价格" data-rule-number="true" data-msg-number="请输入正确的数字" >' +
                    '</div>' +
                    '</div>' +
                    '</div><!-- col-sm-4 -->';
                var endRow = '</div>';
                $("#truPrice").append(startRow,rmb,dollar,canadian,endRow);
            }

        });
    };
    //绑定系统选择事件
    $("#system").bind("change",function(){
        var type = $("#system").val();
        //清空原有配置
        $("#sie").empty();
        $("#tru").empty();
        if(type == "1"){
            insertSie();
        }else if(type == "2"){
            insertTru();
        }else if(type == "3"){
            insertSie();
            insertTru();
        }else{
            //有误
        }
    });

})

