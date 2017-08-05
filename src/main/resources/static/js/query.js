$("#submit")
    .click(function() {
        doSearch();
    });

//<input type="button" class="btn btn-primary btn-sm" id="r_jt" value = "今天" disabled="disabled">
//<input type="button" class="btn btn-primary btn-sm" id="r_zt" value = "昨天" >
//<input type="button" class="btn btn-primary btn-sm" id="r_bz" value = "本周" >
//<input type="button" class="btn btn-primary btn-sm" id="r_sz" value = "上周" >
//<input type="button" class="btn btn-primary btn-sm" id="r_sy" value = "上月" >
//<input type="button" class="btn btn-primary btn-sm" id="r_yz" value = "最近7天" >
//<input type="button" class="btn btn-primary btn-sm" id="r_yy" value = "最近30天" >

function resetSearchCondition()
{
	$("#brand").val("");
	$("#zt").val("");
	$("#channel").val("");
	$("#appsku").val("");
	$("#pagetype").val("");
	$("#province").val("");
	$("#city").val("");
}

$("#r_jt")
.click(function() { 
	resetSearchCondition();
	start_time = getCurrentDate;
	end_time = getCurrentDate;
//	$("#brand").val("");
//	$("#brand").attr("value",""); 
//	$("#brand option[text='请选择']").attr("selected",true);
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
	
	doSearch(); 
});



$("#r_zt")
.click(function() { 
	resetSearchCondition();
	time = getYesterdayDate;
	$("#start_time").val(time);
	$("#end_time").val(time);
	doSearch(); 
});

$("#r_bz")
.click(function() { 
	resetSearchCondition();
	start_time = getWeekStartDate;
	end_time = getCurrentDate;
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
	doSearch(); 
});

$("#r_sz")
.click(function() { 
	resetSearchCondition();
	start_time = getUpWeekStartDate;
	end_time = getUpWeekEndDate;
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
	doSearch(); 
});

$("#r_export")
.click(function() {
	start_time = $("#start_time").val();
	end_time = $("#end_time").val();
	params = 'start_time=' + start_time + '&end_time=' + end_time;
	$.download('localhost:9527/api/car/export/shortcut',params, 'post');
});

$("#r_sy")
.click(function() { 
	resetSearchCondition();
	start_time = getLastMonthStartDate;
	end_time = getLastMonthEndDate;
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
	doSearch(); 
});

$("#r_yz")
.click(function() { 
	resetSearchCondition();
	start_time = GetDateStr(-6);
	end_time = getCurrentDate;
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
	doSearch(); 
});

$("#r_yy")
.click(function() { 
	resetSearchCondition();
	start_time = GetDateStr(-30);
	end_time = getCurrentDate;
	$("#start_time").val(start_time);
	$("#end_time").val(end_time);
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

$("#lastp")
.click(function() { 
	$("#page_no").val($("#total_page").val());
	doSearch(); 
});

jQuery.download = function(url, data, method){
    // 获得url和data
    if( url && data ){ 
        // data 是 string 或者 array/object
        data = typeof data == 'string' ? data : jQuery.param(data);
        // 把参数组装成 form的  input
        var inputs = '';
        jQuery.each(data.split('&'), function(){ 
            var pair = this.split('=');
            inputs+='<input type="hidden" name="'+ pair[0] +'" value="'+ pair[1] +'" />'; 
        });
        // request发送请求
        jQuery('<form action="'+ url +'" method="'+ (method||'post') +'">'+inputs+'</form>')
        .appendTo('body').submit().remove();
    };
};


$("#go_to")
.click(function() {
	var g = Number($("#go_to_page").val());
	var t = Number($("#total_page").val());
	
	if (g > 0 && g <= t)
	{
		$("#page_no").val(g);
		doSearch(); 
	}
	
	$("#go_to_page").val("");
	
});

$("#firstp")
.click(function() { 
	$("#page_no").val(1);
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

    $.ajax({
            url: "localhost:9527/api/car/search",
            type: "POST",
            dataType: "json",
            data: JSON.stringify(request),
            contentType: 'application/json',
            success: function(data) {

                var emptyLine = "<tr><td id=\"no_record\" style=\"display:true\" colspan=\"14\" align=\"center\">NO RECORD FOUND</td></tr>";

                if (data == "") {
                	$("#r_export").attr("disabled", "disabled");
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
                            var line = "<tr>" + "<td>" + item.id + "</td>" + "<td>" + item.name + "</td>" + "<td>" + item.phone + "</td>" + "<td>" + item.province + " " + item.city + "</td>" + "<td>" + item.brand + "</td>" + "<td>" + item.serialName + "</td>" + "<td>" + item.carName + "</td>" + "<td>" + item.dealer + "</td>" + "<td>" + item.appsku + "</td>" + "<td>" + item.channel + "</td>" + "<td>" + item.zt + "</td>" + "<td>&nbsp;</td>" + "<td>" + item.pagetype + "</td>" + "<td>" + item.requestTime + "</td>" + "</tr>";
                            $("#request_list_tbl").append(
                                line);
                        });
                        $("#r_export").removeAttr("disabled");
                        $("#page_info").attr("style", "display:true");
                        $("#total_cnt").text("共 "　+ data.record_count + " 条");
                        $("#client_cnt").text("名单总人数： "+data.group_count+" 人");
                        $("#total_page").val(data.total_page);
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
                        	if (data.page_no == 1)
                        	{
                        		$("#prep").attr("style", "display:none");
                        		$("#firstp").attr("style", "display:none");
                        		
                        	}
                        	else
                        	{
                        		$("#prep").attr("style", "display:true");
                        		$("#firstp").attr("style", "display:true");
                        	}
                        	
                        	if (data.page_no == data.total_page)
                        	{
                        		$("#lastp").attr("style", "display:none");
                        		$("#nextp").attr("style", "display:none");
                        	}
                        	else
                        	{
                        		$("#lastp").attr("style", "display:true");
                        		$("#nextp").attr("style", "display:true");
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
        url: "localhost:9527/api/car/config/sms",
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
        url: "localhost:9527/api/car/config/maillist",
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
        url: "localhost:9527/api/car/query/brandlist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#brand").empty();
        	$("#brand").append("<option selected value=''>请选择</option>");
        	
            
            if (data != null && data.length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#brand").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "localhost:9527/api/car/query/ztlist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#zt").empty();
        	$("#zt").append("<option disabled selected value=''>请选择</option>");
            if (data != null && data.length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#zt").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "localhost:9527/api/car/query/pagetypelist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#pagetype").empty();
        	$("#pagetype").append("<option disabled selected value=''>请选择</option>");
            if (data != null && data.length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#pagetype").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "localhost:9527/api/car/query/appskulist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#appsku").empty();
        	$("#appsku").append("<option disabled selected value=''>请选择</option>");
            if (data != null && data.length > 0) {
                $.each(data, function(index,
                    item) {
                		var opt = "<option value='" + item + "'>" + item + "</option>"
                		$("#appsku").append(opt);
                });
            }
        }
    });
    
    $.ajax({
        url: "localhost:9527/api/car/query/channellist",
        type: "GET",
        dataType: "json",
        data: "",
        success: function(data) {
        	$("#channel").empty();
        	$("#channel").append("<option disabled selected value=''>请选择</option>");
            if (data != null && data.length > 0) {
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
            url: "localhost:9527/api/car/config/sms",
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
                url: "localhost:9527/api/car/config/maillist",
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
            url: "localhost:9527/api/car/config/maillist",
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

