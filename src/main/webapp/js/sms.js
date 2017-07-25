$(function() {
    function timekeeping() {
        $("#btn").attr("disabled", true);
        var interval = setInterval(function() {
            total = $.cookie("total");
            $("#btn").val("请等待" + total + "秒");
            total--;
            if (total == 0) {
                clearInterval(interval);
                total = $.cookie("total", total, {
                    expires: -1
                });
                $("#btn").val("重新发送");
                $("#btn").attr("disabled", false)
            } else {
                $.cookie("total", total)
            }
        }, 1000)
    }
    $("#btn").click(function(event) {
        var rep = /^1\d{10}$/;
        var partten = /^1[3,4,5,7,8]\d{9}$/;
        var phone = $("#inPhone").val();
        if (phone == "") {
            showTip("请输入手机号");
            return
        } else {
            if (!rep.test(phone)) {
                showTip("手机号格式有误");
                return
            } else {
                if (!partten.test(phone)) {
                    showTip("手机号格式有误");
                    return
                } else {}
            }
        }
        $.ajax({
            url: "h5Controller/send.do",
            type: "POST",
            dataType: "json",
            data: {
                phone: phone
            }
        }).done(function(re) {
            $.cookie("total", 60);
            timekeeping();
            if (re.code == "1") {
                showTip(re.message)
            } else {
                showTip(re.message)
            }
        }).fail(function() {
            console.log("error")
        }).always(function() {
            console.log("complete")
        })
    })
});