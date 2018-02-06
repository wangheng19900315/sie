var zTree;
var demoIframe;

var setting = {
    view: {
        dblClickExpand: false,
        showLine: true,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable:true,
            idKey: "id",
            pIdKey: "pId",
            rootPId: ""
        }
    },
    callback: {
        beforeClick: function(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("tree");

            //获取标题内容
            $.ajax({
                url: pageRootPath+'/truWebEditor/getTitle.json',
                data: {"id":treeNode.id},
                type: 'post',
                dataType: 'json',
                cache: false,
                success: function (data) {
                    if (data.success) {
                        //设置内容的值
                        CKEDITOR.instances.content.setData(data.data.content);
                        $("#data-form").loadJson(data.data);
                        if (treeNode.isParent) {
                            //只对标题和副标题进行编辑
                            $("#contentRow").hide();
                        }else{
                            $("#contentRow").show();
                        }
                    } else {
                        alert("加载标题内容失败");
                    }
                },
                error: function () {
                    alert("提交信息出现错误！");
                }
            });
        }
    }
};

var initZtree = function(){
    alert(1);
    //获取所有的节点
    $.ajax({
        url: pageRootPath+'/truWebEditor/getAllNode.json',
        type: 'post',
        dataType: 'json',
        cache: false,
        success: function (data) {
            if (data.success) {
                var t = $("#tree");
                t = $.fn.zTree.init(t, setting, data.data);
                var zTree = $.fn.zTree.getZTreeObj("tree");
                //zTree.selectNode(zTree.getNodeByParam("id", 101));
                //展开所有节点
                zTree.expandAll(true);
            } else {
                alert("加载标题列表失败");
            }
        },
        error: function () {
            alert("提交信息出现错误！");
        }
    });

}

$(document).ready(function(){
    //初始化editor插件
    CKEDITOR.replace('content');

    //初始化ztree
    initZtree();

    //绑定保存title事件
    $("#submitBtn").bind("click",function(){
        var formData = $("#data-form").serializeJson();
        formData.content = CKEDITOR.instances.content.getData();//获取文本编辑的内容
        $.ajax({
            url: pageRootPath+'/truWebEditor/addOrupdate.json',
            data: formData,
            type: 'post',
            dataType: 'json',
            cache: false,
            success: function (data) {
                if (data.success) {
                    alert("数据保存成功！");
                    //刷新ztree
                    initZtree();

                } else {
                    alert("保存数据出现错误，请稍候重试！");
                }
            },
            error: function () {
                alert("提交保存信息出现错误！");
            }
        });
    });


});

function loadReady() {
    var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
        htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
        maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
        h = demoIframe.height() >= maxH ? minH:maxH ;
    if (h < 530) h = 530;
    demoIframe.height(h);
}