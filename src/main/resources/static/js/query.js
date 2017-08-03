$("#submit")
    .click(function() {
        doSearch();
    });

$("#page_size")
	.change(function() { 
		$("#page_no").val(1);
		doSearch(); 
	});

$("#nextp")
.click(function() { 
	var cur_page_no = Number($("#page_no").val());
	cur_page_no = cur_page_no + 1;
	$("#page_no").val(cur_page_no);
	doSearch(); 
});

$("#prep")
.click(function() { 
	var cur_page_no = Number($("#page_no").val());
	cur_page_no = cur_page_no - 1;
	$("#page_no").val(cur_page_no);
	doSearch(); 
});

function doSearch() {
    var request = {
        brand: $("#brand").val(),
        province: $("#province").val(),
        city: $("#city").val(),
        appsku: $("#appsku").val(),
        channel: $("#channel").val(),
        zt: $("#zt").val(),
        key: $("#key").val(),
        start_time: $("#start_time").val(),
        end_time: $("#end_time").val(),
        page_type: $("#pageype").val(),
        page_size:$("#page_size").val(),
        page_no:$("#page_no").val()
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
                	$("#page_info").attr("style", "display:none");
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
                        
                        $("#page_info").attr("style", "display:true");
                        $("#total_cnt").text("共 "　+ data.record_count + " 条");
                        $("#client_cnt").text("名单总人数： "+data.group_count+" 人");
                        if(data.total_page == 1)
                        {
                        	$("#page_no").val(1);
                        	$("#paging").attr("style", "display:true");
                        	$("#page_go").attr("style", "display:none");
                        	$("#prep").attr("style", "display:none");
                    		$("#firstp").attr("style", "display:none");
                    		$("#lastp").attr("style", "display:none");
                    		$("#nextp").attr("style", "display:none");
                        	$("#lb_summary").empty();
                        	$("#lb_summary").append("共&nbsp;"+ data.total_page +"&nbsp;页&nbsp;第&nbsp;"+ data.page_no + "&nbsp;页");
                        	$("#lb_pgsize").attr("style", "display:true");
                        }
                        else
                        {
                        	$("#page_go").attr("style", "display:true");
                        	$("#paging").attr("style", "display:true");
                        	$("#page_no").val(data.page_no);
//                        	$("#paging").append("<a href=\"javascript:void(0);\" id=\"firstp\">首页</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" id=\"pre\">上页</a>");
                        	if (data.page_no == 1)
                        	{
                        		$("#prep").attr("style", "display:none");
                        		$("#firstp").attr("style", "display:none");
                        		$("#lastp").attr("style", "display:true");
                        		$("#nextp").attr("style", "display:true");
                        		
                        	}
                        	
//                        	$("#paging").append("<a href=\"javascript:void(0);\" id=\"next\">下页</a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:void(0);\" id=\"lastp\">末页</a>");
                        	if (data.page_no == data.total_page)
                        	{
                        		$("#prep").attr("style", "display:true");
                        		$("#firstp").attr("style", "display:true");
                        		$("#lastp").attr("style", "display:none");
                        		$("#nextp").attr("style", "display:none");
                        	}
                        	
                        	$("#lb_summary").empty();
                        	$("#lb_summary").append("共&nbsp;"+ data.total_page +"&nbsp;页&nbsp;第&nbsp;"+ data.page_no + "&nbsp;页");
                        	$("#lb_pgsize").attr("style", "display:true");
                        }
                        

                    } else {
                    	$("#page_info").attr("style", "display:none");
                        $("#request_list_tbl").append(
                            emptyLine);
                    }
                }
            }
        });
}
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

function initQueryPage() {
    $.ajax({
        url: "api/car/query/brandlist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#brand").empty();
        	$("#brand").append("<option disabled selected>请选择</option>");
            var length = data.length;
            if (length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#brand").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "api/car/query/ztlist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#zt").empty();
        	$("#zt").append("<option disabled selected>请选择</option>");
            var length = data.length;
            if (length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#zt").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "api/car/query/pagetypelist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#pagetype").empty();
        	$("#pagetype").append("<option disabled selected>请选择</option>");
            var length = data.length;
            if (length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#pagetype").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "api/car/query/appskulist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#appsku").empty();
        	$("#appsku").append("<option disabled selected>请选择</option>");
            var length = data.length;
            if (length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#appsku").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "api/car/query/channellist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#channel").empty();
        	$("#channel").append("<option disabled selected>请选择</option>");
            var length = data.length;
            if (length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#channel").append(opt);
                });
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

//$("#page_size").on( "change", function() { doSearch(); } );

