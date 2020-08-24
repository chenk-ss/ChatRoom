function login(data) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    $.ajax({
        async: false,
        type: 'POST',
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
                sessionStorage.setItem('username', username);
                console.log(sessionStorage.getItem('token'));
                window.location.href = "Index.html";
            }
        }
    })
}

function register(data) {
    window.location.href = 'Register.html';
}