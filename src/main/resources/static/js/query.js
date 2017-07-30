$("#submit")
    .click(
        function() {
            var request = {
                brand: $("#brand").val(),
                province: $("#province").val(),
                city: $("#city").val(),
                appsku: $("#appsku").val(),
                channel: $("#channel").val(),
                zt: $("#zt").val(),
                key: $("#keyword").val(),
                start_time: $("#start_time").val(),
                end_time: $("#end_time").val(),
                page_type: $("#pageype").val()
            };

            $
                .ajax({
                    url: "api/car/search",
                    type: "POST",
                    dataType: "json",
                    data: JSON.stringify(request),
                    contentType: 'application/json',
                    success: function(data) {

                        var emptyLine = "<tr><td id=\"no_record\" style=\"display:true\" colspan=\"14\" align=\"center\">NO RECORD FOUND</td></tr>";

                        if (data == "") {
                            $("#no_record").remove();
                            $("#request_list_tbl").remove();
                            $("#request_list")
                                .append(
                                    "<tbody id = \"request_list_tbl\"></tbody>")
                            $("#request_list_tbl")
                                .append(emptyLine);
                        } else {
                            $("#no_record").remove();
                            $("#request_list_tbl").remove();
                            $("#request_list")
                                .append(
                                    "<tbody id = \"request_list_tbl\"></tbody>")

                            var length = data.list.length;
                            if (length > 0) {

                                $.each(data.list, function(index,
                                    item) {
                                    var line = "<tr>" + "<td>" + item.id + "</td>" + "<td>" + item.name + "</td>" + "<td>" + item.phone + "</td>" + "<td>" + item.province + " " + item.city + "</td>" + "<td>" + item.brand + "</td>" + "<td>" + item.carName + "</td>" + "<td>" + item.serialName + "</td>" + "<td>" + item.dealer + "</td>" + "<td>" + item.appsku + "</td>" + "<td>" + item.channel + "</td>" + "<td>" + item.zt + "</td>" + "<td>&nbsp;</td>" + "<td>" + item.pagetype + "</td>" + "<td>" + item.requestTime + "</td>" + "</tr>";
                                    $("#request_list_tbl").append(
                                        line);
                                });

                            } else {
                                $("#request_list_tbl").append(
                                    emptyLine);
                            }
                        }
                    }
                });

        });

// 根据车型id和城市获取经销商

function showTip(text) {
    $("#models_balloon").text(text).show();
    setTimeout('$("#models_balloon").hide();', 2000);
}

function showSuccess() {
    $(".popup-succes").show();
    setTimeout('$(".popup-succes").hide();', 2000);
}

function loadSMSConfig() {
    $.ajax({
        url: "api/car/config/sms",
        type: "GET",
        dataType: "text",
        data: "",
        success: function(data) {
            if (data == "OFF") {
                $(":radio[name='sms_switch'][value='OFF']").attr("checked", true);
            } else {
                $(":radio[name='sms_switch'][value='ON']").attr("checked", true);
            }

        }
    });
}

function loadMailList() {
    $.ajax({
        url: "api/car/config/maillist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
            $("#mail_list").remove();
            $("#mail_tbl").append("<tbody id = \"mail_list\"></tbody>");
            var length = data.length;
            if (length > 0) {

                $.each(data, function(index,
                    item) {
                    var line = "<tr>" + "<td style=\"width: 20%\"><input type=\"checkbox\" name=\"mailid\" value= \"" + item.id + "\" ></td>" + "<td style=\"width: 40%\">" + item.name + "</td>" + "<td style=\"width: 40%\">" + item.mail + "</td>" + "</tr>";
                    $("#mail_list").append(
                        line);
                });

            } else {
                $("#mail_list").remove();
                $("#mail_tbl").append("<tbody id = \"mail_list\"></tbody>");
            }
        }
    });
}

$("#save_sms_switch").click(
    function() {
        var value = $("input[name='sms_switch']:checked").val();
        $.ajax({
            url: "api/car/config/sms",
            type: "PUT",
            dataType: "text",
            data: {
                value: value
            },
            success: function(data) {

            }
        });
    }
);

$("#delete").click(
    function() {
        var chk_ids = []; //定义一个数组    
        var cnt = 0;
        $('input[name="mailid"]:checked').each(function() {     
            chk_ids.push($(this).val()); 
            cnt = cnt + 1;
        });
        if (cnt > 0) {
            $.ajax({
                url: "api/car/config/maillist",
                type: "POST",
                data: {
                    "chk_ids": chk_ids
                },
                traditional: true,
                success: function(data) {}
            });
            loadMailList();
        }
    }
);

$("#add_mail").click(
    function() {
        var name = $("#name").val();
        var mail = $("#mail").val();
        $.ajax({
            url: "api/car/config/maillist",
            type: "PUT",
            dataType: "text",
            data: {
                name: name,
                mail: mail
            },
            success: function(data) {

            }
        });
        $("#name").val("");
        $("#mail").val("");
        loadMailList();
    }
);