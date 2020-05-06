function register(data) {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var passwordCheck = document.getElementById("passwordCheck").value;
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
                window.location.href = API() + '/?path=Login';
            }
        }
    })
}