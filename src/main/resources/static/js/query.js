
$("#submit").click(function() {
	var request = {
		brand : $("#brand").val(),
		province : $("#province").val(),
		city : $("#city").val(),
		appsku : $("#appsku").val(),
		channel : $("#channel").val(),
		zt : $("#zt").val(),
		key : $("#keyword").val(),
		start_time : $("#start_time").val(),
		end_time : $("#end_time").val(),
		page_type : $("#pageype").val()
	};

//	var brand = $("#brand").val();
//	var province = $("#province").val();
//	var city = $("#city").val();
//	var appsku = $("#appsku]").val();
//	var channel = $("#channel]").val();
//	var zt = $("#zt").val();
//	var key = $("#keyword").val();
//	var starttime = $("#start_time").val();
//	var endtime = $("#end_time").val();
//	var pageType = $("#pageype").val();

	$.ajax({
		url : "api/car/search",
		type : "POST",
		dataType : "json",
		data : JSON.stringify(request),
		contentType: 'application/json',
		success : function(data) {
			if (data == "") {

			} else {
				showTip(data);
			}
		}
	});

});

// 根据车型id和城市获取经销商
function showGrouponPrice_location(carId, cityCode) {
	// alert(carId+" - "+cityName);
	$
			.ajax({
				url : "api/car/cardealerlist",
				type : "POST",
				dataType : "json",
				data : {
					carid : carId,
					cityid : cityCode
				},
				success : function(data) {

					if (data == null || data == "") {
						$("#countNumber").text(0);
						$("#arrow").hide();
						$("#jinxiaoshan").show();
						return;
					}

					var length = 0;
					$("#topDealers li").remove();
					$
							.each(
									data,
									function(index, item) {

										var li = "";
										var leftbox = "";
										var h6 = "";
										var address = "";
										var phone = "";

										if (item.dealerShortName.indexOf("店") > 0) {
											if (item.dealerShortName
													.indexOf("店") > 4) {
												h6 = "<h6>"
														+ item.dealerShortName
														+ "</h6>";
											} else {
												var carNames = item.dealerShortName
														.split("店");
												h6 = "<h6><span style='color:red'>"
														+ carNames[0]
														+ "-</span>"
														+ carNames[1] + "</h6>";
											}
										} else {
											h6 = "<h6>" + item.dealerShortName
													+ "</h6>";
										}
										address = "<p>地址：" + item.address
												+ "</p>";

										if (index < 3) {
											leftbox = "<div class='leftbox' id='boxDiv"
													+ index
													+ "' alt='"
													+ item.dealerId
													+ "' onclick='choose("
													+ index
													+ ")'><i id='checkbox' class='checkbox checked'></i></div>";
											$("#countNumber").text(index + 1);
											li = "<li>" + leftbox + h6
													+ address + "</li>";
										} else {
											leftbox = "<div class='leftbox' id='boxDiv"
													+ index
													+ "' alt='"
													+ item.dealerId
													+ "' onclick='choose("
													+ index
													+ ")'><i id='checkbox' class='checkbox'></i></div>";
											li = "<li style='display:none' class='hiddenLi'>"
													+ leftbox
													+ h6
													+ address
													+ "</li>";
										}

										$("#topDealers").append(li);
										length++;
									});

					if (length == 0) {
						$("#countNumber").text(0);
						$("#arrow").hide();
						$("#jinxiaoshan").show();
					} else {
						$("#arrow").show();
						$("#arrowup").hide();
						$("#jinxiaoshan").hide();
					}

				}
			});
}


function showTip(text) {
	$("#models_balloon").text(text).show();
	setTimeout('$("#models_balloon").hide();', 2000);
}

function showSuccess() {
	$(".popup-succes").show();
	setTimeout('$(".popup-succes").hide();', 2000);
}

