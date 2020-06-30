function register(data) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var passwordCheck = document.getElementById("passwordCheck").value;
    username = username.replace(/^\s+|\s+$/g, "");
    password = password.replace(/^\s+|\s+$/g, "");
    if (username == '') {
        alert("用户名不能为空 ")
        return;
    }
    if (password == '') {
        alert("密码不能为空")
        return;
    }
    if (password != passwordCheck) {
        alert("两次输入的密码不一致");
        return;
    }
    $.ajax({
        async: false,
        type: 'post',
        url: API() + '/user/register',
        data: {
            userName: username,
            password: SHA256(password)
        },
        success: function (data) {
            if (!data.success) {
                alert(data.message);
            } else {
                window.location.href = 'Login.html';
            }
        }
    })
}