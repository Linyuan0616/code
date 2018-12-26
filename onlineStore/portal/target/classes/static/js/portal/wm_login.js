$(document).ready(function() {
    localStorage.url = "http://localhost:8081",
    $("#loginBtn").on("click", function(a) {
        a.preventDefault();
        var t = $(this);
        t.addClass("not-allow");
        var e = $("#tel").val()
          , l = $("#password").val();
        return "" == e ? (alert("手机号码不能为空!"),
        !1) : /^1[3|4|5|7|8]\d{9}$/.test(e) ? "" == l ? (alert("密码不能为空!"),
        !1) : void $.ajax({
            url: localStorage.url + "/user/login",
            type: "POST",
            dataType: "json",
            xhrFields: {
                withCredentials: !0
            },
            crossDomain: !0,
            data: {
                phone: e,
                password: l
            },
            success: function(a) {
                a.data!=null ? (localStorage.userId = a.data.userId,
                alert("欢迎进入水管家管理中心"),
                location.href = "html/user.html") : alert("登录失败")
            },
            complete: function() {
                t.removeClass("not-allow")
            }
        }) : (alert("手机号码输入错误"),
        !1)
    })
});
