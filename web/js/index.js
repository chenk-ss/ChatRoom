var home = new Vue({
    el: '#wrapper',
    data: {
        msgs: [],
        submitMessage: '',
    },
    methods: {
        send: function (data) {
            var _self = this;
            // var message = document.getElementById("sendMessage").value;
            $.ajax({
                async: false,
                type: 'get',
                headers: {
                    "Token": getToken()
                },
                data: {
                    "message": _self.submitMessage,
                    "friendId": '1'
                },
                url: API() + '/send',
                success: function (data) {
                    if (!data.success) {
                        window.location.href = API() + '/?path=Login';
                        return;
                    }
                    console.log("发送成功");
                    _self.submitMessage = '';
                }
            })
        },

        queryhistory: function (data) {
            var _self = this;
            let dataRead = {};
            postAjax(API() + '/query/history', JSON.stringify(dataRead), function (ret, err) {
                if (ret && ret.success === true) {
                    var list = ret.data;
                    console.log(list);
                    _self.msgs = ret.data;
                    _self.$nextTick(() =>{
                        _self.toEnd();
                    })
                }
            });
        },

        toEnd: function(){
            var obj = document.getElementById("messages");
            obj.scrollTop = obj.scrollHeight;
        },

        login: function (data) {
            window.location.href = 'Login.html';
        }
    },
    created() {
        var _self = this;
        this.queryhistory()
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
            socket = new WebSocket(WEBSOCKETIP());
            //打开事件
            socket.onopen = function () {
                console.log("Socket 已打开");
            };
            //获得消息事件
            socket.onmessage = function (msg) {
                var message = msg.data;
                // console.log(message);
                if (msg.data.split(":")[0] == "num") {
                    // document.getElementById("onlineNum").innerHTML = "在线人数:" + msg.data.split(":")[1];
                } else if (msg.data != "HeartBeatCheck" && msg.data != "连接成功") {
                    var json = eval('(' + message + ')');
                    console.log("message2:" + json);
                    _self.msgs.push(json);
                    _self.$nextTick(() =>{
                        _self.toEnd();
                    })

                }
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
    }
})