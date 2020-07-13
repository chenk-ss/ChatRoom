var socket;
window.setInterval(function () { //每隔60秒钟发送一次心跳，避免websocket连接因超时而自动断开
    var ping = "HeartBeatCheck";
    socket.send(ping);
}, 60000);

if (typeof (WebSocket) == "undefined") {
    console.log("您的浏览器不支持WebSocket");
} else {
    console.log("您的浏览器支持WebSocket");
    //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
    socket = new WebSocket("ws://" + WEBSOCKETIP() + "/websocket/1");
    //打开事件
    socket.onopen = function () {
        console.log("Socket 已打开");
    };
    //获得消息事件
    socket.onmessage = function (msg) {
        var message = msg.data;
        console.log(message);
        if (msg.data.split(":")[0] == "num") {
            document.getElementById("onlineNum").innerHTML = "在线人数:" + msg.data.split(":")[1];
        } else if (msg.data != "HeartBeatCheck" && msg.data != "连接成功") {
            var json = eval('(' + msg.data + ')');
            // console.log("message:" + json);
            document.getElementById("main").innerHTML = dateFormat(new Date(json.createTime)) + "&nbsp;&nbsp;" + json.userName + "&nbsp;&nbsp;:&nbsp;&nbsp;" + json.message + "<br/>" + document.getElementById("main").innerHTML;
            document.getElementById("sendMessage").value = "";
        }
        //发现消息进入    开始处理前端触发逻辑
    };
    //关闭事件
    socket.onclose = function () {
        console.log("Socket已关闭");
    };
    //发生了错误事件
    socket.onerror = function () {
        //此时可以尝试刷新页面
    }
}

/**
 * 时间格式化
 * @param fmt
 * @returns {string | void}
 */
Date.prototype.format = function (fmt) {
    var o = {
        "y+": this.getFullYear, //年
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds() //秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

function send(data) {
    var message = document.getElementById("sendMessage").value;
    $.ajax({
        async: false,
        type: 'get',
        headers: {
            "Token": getToken()
        },
        data: {"message": message},
        url: API() + '/send',
        success: function (data) {
            if (!data.success) {
                window.location.href = API() + '/?path=Login';
                return;
            }
            console.log("发送成功");
        }
    })
}

function queryhistory(data) {
    let dataRead = {};
    postAjax(API() + '/query/history', JSON.stringify(dataRead), function (ret, err) {
        if (ret && ret.success === true) {
            var list = ret.data;
            console.log(list);
            var innerHtml = "";
            for (var i = 0; i < list.length; i++) {
                innerHtml += dateFormat(new Date(list[i].createTime)) + "&nbsp;&nbsp;" + list[i].userName + "&nbsp;&nbsp;:&nbsp;&nbsp;" + list[i].message + "<br/>";
            }
            document.getElementById("main").innerHTML = innerHtml;
        }
    });
}

function login(data) {
    window.location.href = 'Login.html';
}