var home = new Vue({
    el: '#wrapper',
    data: {
        mylist: [],
        data1: [],
        msgs: '',
        submitMessage: '',
    },
    methods: {
        submit: function () {
            var _self = this;
            console.log(_self.submitMessage);
        },
        start: function () {
            var _self = this;
            var messages = [];
            messages.push('{"name": "chen1", "message": "test1"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            messages.push('{"name": "chen1", "message": "test3"}');
            _self.mylist = JSON.parse('[' + messages + ']');
            var innerHtml = "";
            for (var i = 0; i < _self.mylist.length; i++) {
                innerHtml += _self.mylist[i].name + ":\n\t" + _self.mylist[i].message + "\n";
            }
            _self.msgs = innerHtml;
            console.log(_self.mylist);
        }
    },
    created() {
        this.start()
    }
});