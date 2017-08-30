var dhcc = {};

dhcc.debug = false;

dhcc.info = function(val){
    if(dhcc.debug){
        console.info(val);
    }
};

dhcc.NumberUtil = {};

/**
 * 文件大小字符串转换
 * @param size
 * @param scale
 * @return
 */
dhcc.NumberUtil.conversionUnitMemory = function (size, scale) {
    var value = new Number(0);
    var unitString = " B";
    if (size == null || size == 0 || typeof(size) == "undefined") {
        value = 0;
        unitString = " B";
    } else if (size < Math.pow(1024, 1)) {
        value = size;
        unitString = " KB";
    } else if (size >= Math.pow(1024, 1) && size < Math.pow(1024, 2)) {
        value = size / Math.pow(1024, 1);
        unitString = " KB";
    } else if (size >= Math.pow(1024, 2) && size < Math.pow(1024, 3)) {
        value = size / Math.pow(1024, 2);
        unitString = " MB";
    } else if (size >= Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
        value = size / Math.pow(1024, 3);
        unitString = " GB";
    } else if (size >= Math.pow(1024, 4) && size < Math.pow(1024, 5)) {
        value = size / Math.pow(1024, 4);
        unitString = " TB";
    } else if (size >= Math.pow(1024, 5) && size < Math.pow(1024, 6)) {
        value = size / Math.pow(1024, 5);
        unitString = " PB";
    } else {
        value = size;
        unitString = " B";
    }
    return value.toFixed(scale) + unitString;
};

dhcc.UploadUtil = {};

/** 单个文件上传的方法 */
dhcc.UploadUtil.postFile = function (httpUrl, fileObj, parameterObj, processHandler, callbackHandler) {
    console.info("postFile - httpUrl=" + httpUrl + ", name=" + fileObj.name + ", size=" + fileObj.size + ", parameterObj=" + parameterObj);
    if (typeof(parameterObj) != "object") {
        parameterObj = {};
    }
    if (typeof(processHandler) != "function") {
        processHandler = function () {
        };
    }
    if (typeof(callbackHandler) != "function") {
        callbackHandler = function () {
        };
    }
    var xhr = null;
    if (window.XMLHttpRequest) {
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) {
        xhr = new ActiveXObject("Microsoft.XMLHTTP");
    } else {
        return false;
    }
    xhr.open("post", httpUrl, true);
    xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");

    // 上传开始回调
    xhr.upload.onloadstart = function (event) {
        console.info("postFile - onloadstart - event=" + event);
    };

    // 上传完成回调
    xhr.onload = function (event) {
        if (this.status == 200) {
            var resultObj = eval("(" + this.responseText + ")");
            console.info("postFile - onload - status=" + this.status + ", message=" + resultObj.message + ", event=" + event);
            callbackHandler(resultObj.success, resultObj.message);
        } else {
            //var resultObj = eval("(" + this.responseText + ")");
            console.info("postFile - onload - status=" + this.status + ", message=" + this.responseText + ", event=" + event);
            callbackHandler(false, "上传文件出现错误");
        }
    };
    // 上传错误回调
    xhr.upload.onerror = function (event) {
        console.info("postFile - onerror - 上传失败- event=" + event);
        callbackHandler(false, "上传文件出现错误");
    };
    // 上传过程回调
    xhr.upload.onprogress = function (event) {
        var percent = Math.round(event.loaded / event.total * 100);
        var loadedString = dhcc.NumberUtil.conversionUnitMemory(event.loaded, 2);
        var totalString = dhcc.NumberUtil.conversionUnitMemory(event.total, 2);
        console.info("postFile - onprogress - percent=" + percent + ", " + event.total + "/" + event.loaded + ", " + loadedString + "/" + totalString);
        processHandler(percent, loadedString, totalString);
    };
    var formData = new FormData();
    formData.append("fileData", fileObj);
    for (var attr in parameterObj) {
        formData.append(attr, parameterObj[attr]);
    }
    xhr.send(formData);
};

dhcc.Unit = {}
/** 提交的form, 显示输出结果的renderer */
dhcc.Unit.submit = function (form, renderer, accessToken, platformCode) {
    var data = {};
    var inputs = $(form).find("input");
    for (var i = 0; i < inputs.length; i++) {
        eval("data." + $(inputs[i]).attr("name") + "=" + "'" + $(inputs[i]).val() + "'");
    }

    var params = {
        params:  JSON.stringify(data),
        accessToken: accessToken,
    };
    $.post($(form).attr('url'), params, function(data){
        $(renderer).html(JSON.stringify(data));
    });

};

