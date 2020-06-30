function login(data) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    $.ajax({
        async: false,
        type: 'get',
        url: API() + '/user/login',
        data: {
            userName: username,
            password: SHA256(password)
        },
        success: function (data) {
            if (!data.success) {
                alert(data.message);
            } else {
                sessionStorage.setItem('token', data.data);
                console.log(sessionStorage.getItem('token'));
                window.location.href = API();
            }
        }
    })
}

function register(data) {
    $.ajax({
        async: false,
        type: 'get',
        url: '',
        success: function (data) {
            window.location.href = API() + '/?path=Register';
        }
    })
}