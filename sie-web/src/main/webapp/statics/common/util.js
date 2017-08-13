/**
 * 时间格式化
 * @param fmt 格式
 * @returns
 */
Date.prototype.Format = function (fmt) {
    if(!this  || !this.getMonth()){
        return "";
    }
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };

    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}


$.fn.serializeJson=function(){
    var serializeObj={};
    $(this.serializeArray()).each(function(){
        serializeObj[this.name]=this.value;
    });
    return serializeObj;
};
