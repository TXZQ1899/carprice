      // 获取车型信息
      function getCarInfo(serialId, carId){
    	  //alert(serialId+" - "+carId);
          $.ajax({
			  	url:"api/car/carlist",
			  	type:"get",
				dataType:"json",
				data:{serialid:serialId},
				success:function(data){					
					$("#jxs-ul li").remove();
					
					var year = data[0].carYear;
					$.each(data,function(index,item){
						
						if(index == 0){
							$("#jxs-ul").append("<li id='othertit' style='background:#e7e7e7;'>"+year+" 款</li>");
						}
						
						if(year != item.carYear){
							year = item.carYear;
							
							$("#jxs-ul").append("<li id='othertit' style='background:#e7e7e7;'>"+item.carYear+" 款</li>");
						}
						
						var option = "<li onclick='getInfo("+serialId+","+item.carId+",&apos;"+item.carYear+" 款 "+item.carName+"&apos;)'>"+item.carName+"</li>";
						
						$("#jxs-ul").append(option);
						
						if(item.carId == carId){
							getInfo(serialId, item.carId, item.carYear+" 款 "+item.carName);
						}
						
					});
				}
			});
      }
      
      function getInfo(serialId, carId, carName){
    	  // 使用carId和城市名称获取经销商信息
    	  cityName = $("#choicecity").text();
    	  cityCode = $("#choicecitycode").text();
    	  $("#rCurrentCarName").text(carName);
    	  $("#carId").val(carId);
//    	  if(cityName!="选择城市"){
        	  showGrouponPrice_location(carId,cityCode);
        	  $("#sycabc").hide();
              $('#sycabc1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
//    	  }
          
          // 判断是否提交日志的参数          
         /* if($("#rz").val()=="y"){
              // 保存车型id
              saveModels(serialId,carId,"0");// 这里不会点击提交按钮，subBut为0
          }*/
      }

	
	$("#submitOrder").click(function(){
		// 姓名
		var ren = /((^[\u4E00-\u9FA5]{2,5}$)|(^[a-zA-Z]+[\s\.]?([a-zA-Z]+[\s\.]?){0,4}[a-zA-Z]$))/;
		var name = $("input[name=name]").val();
		if(name==""){
			showTip("请输入您的姓名");
			return;
		}else if(name.length<2){
			showTip("姓名至少包含两个字");
			return;
		}else if (!/^[\u4e00-\u9fa5]+$/i.test(name)) {
			showTip("姓名只能是纯汉字");
			return;
	    }else if(name.length>6){
	    	showTip("姓名长度不能超过6个汉字");
			return;
	    }
		// 手机号
		var rep = /^1\d{10}$/;
		var partten = /^1[3,4,5,7,8]\d{9}$/;
		var phone = $("input[name=phone]").val();
		if(phone==""){
			showTip("请输入手机号");
			return;
		}else if (!rep.test(phone)) {
			showTip("手机号格式有误");
			return;
	    }else if(!partten.test(phone)){
	    	showTip("手机号格式有误");
			return;
	    }
		// ids
		var ids = "";
		$(".leftbox").each(function(i){
			if($("#boxDiv"+i+" i").attr("class") == "checkbox checked"){
				ids+=$("#boxDiv"+i+"").attr("alt")+",";
			}
		});
		
		if(ids==""){
			showTip("请选择一家经销商询价");
			return;
		}
		
		var carId = $("#carId").val();
		var cityName = $("#choicecity").text();
		var cityCode = $("#choicecitycode").text();
		var appsku = $("#appsku").val();
		var channel = $("#channel").val();
		var zt = $("#zt").val();
		var key = $("#key").val();
		var code = $("#code").val();
		var pageType = $("#pageType").val();
		
		var serialId = $("#serialId").val();// 获取车款id，发送给后台日志

		// 判断是否提交日志的参数          
        /*if($("#rz").val()=="y"){
			// 保存车型id
	        saveModels(serialId,carId,"1");// 这里点击了提交按钮，subBut为1
        }*/
        
        /*$.ajax({
		  	url:"h5Controller/ckCode.do",
		  	type:"POST",
			dataType:"json",
			async:false,
			data:{phone:phone,code:code},
			success:function(data){

				if(data.code=="1"){
					console.info("验证码验证成功："+data.message);*/
        
					$.ajax({
					  	url:"h5Controller/saveOrderInfoTest",
					  	type:"POST",
						dataType:"json",
						data:{carId:carId,cityName:cityName,ids:ids,name:name,phone:phone,appsku:appsku,channel:channel,zt:zt,key:key,pageType:pageType},
						success:function(data){
							
						}
					});
					
					// 跳转到成功页
					$("#gs_name").val(name);
					$("#gs_phone").val(phone);
					$('#homeCitys').attr("action","h5AlertController/successPageSMS");
					$('#submit').click();		
					//////////////////
				/*}else{
					showTip(data.message);
					$("#submitOrder").attr('disabled',false);
					return;
				}
			}
		})*/
		
		// 跳转到成功页
		
		
		/*$('#homeCitys').attr("action","GenSui/index");
		$('#submit').click();	*/	
	});
      
    // 根据车型id和城市获取经销商
	function showGrouponPrice_location(carId,cityCode){
		//alert(carId+" - "+cityName);
		$.ajax({
		  	url:"api/car/cardealerlist",
		  	type:"POST",
			dataType:"json",
			data:{carid:carId,cityid:cityCode},
			success:function(data){
				// alert(data);
				
				if(data == null){
					$("#countNumber").text(0);
					$("#arrow").hide();
					$("#jinxiaoshan").show();
				}
				
				$("#topDealers li").remove();
				$.each(data,function(index,item){
					
			        var li = "";
			        var leftbox = "";
			        var h6 = "";
			        var address = "";
			        var phone = "";
			        
			        if(item.dealerShortName.indexOf("店") > 0){
			        	if(item.dealerShortName.indexOf("店")>4){
					        h6 = "<h6>"+item.dealerShortName+"</h6>";
			        	}else{
					        var carNames = item.dealerShortName.split("店");
					        h6 = "<h6><span style='color:red'>"+carNames[0]+"-</span>"+carNames[1]+"</h6>";
			        	}
			        }else{
				        h6 = "<h6>"+item.dealerShortName+"</h6>";
			        }
			        address = "<p>地址："+item.address+"</p>";
			        
			        if(index < 3){
			        	leftbox = "<div class='leftbox' id='boxDiv"+index+"' alt='"+item.id+"' onclick='choose("+index+")'><i id='checkbox' class='checkbox checked'></i></div>";
			        	$("#countNumber").text(index+1);
				        li="<li>"+leftbox + h6+ address +"</li>";
		            }else{
		            	leftbox = "<div class='leftbox' id='boxDiv"+index+"' alt='"+item.id+"' onclick='choose("+index+")'><i id='checkbox' class='checkbox'></i></div>";
		            	li="<li style='display:none' class='hiddenLi'>"+leftbox + h6+ address +"</li>";
		            }

					$("#topDealers").append(li);
				});
				
				$("#arrow").show();
				$("#arrowup").hide();
				$("#jinxiaoshan").hide();
			}
		});	
	}
	
	function choose(index){
		if($("#boxDiv"+index+" i").attr("class") == "checkbox checked"){
			$("#boxDiv"+index+" i").attr("class","checkbox");
			
			var index = Number($("#countNumber").text());
			$("#countNumber").text(index-1);
		}else{
			$("#boxDiv"+index+" i").attr("class","checkbox checked");
			
			var index = Number($("#countNumber").text());
			$("#countNumber").text(index+1);
		}
	}
	
	// 展示全部
	$("#arrow").click(function(){
		if($("#topDealers li").length>3){
			if($(".hiddenLi").is(":hidden")){
				$('.hiddenLi').toggle();

				$('#arrow').toggle();
				$('#arrowup').toggle();
			}
		}else{
			// 如果经销商少于三家，则显示收起全部
			$('#arrow').toggle();
			$('#arrowup').toggle();
		}
	});
	// 收起全部
	$("#arrowup").click(function(){
		if(!$(".hiddenLi").is(":hidden")){
			$('.hiddenLi').toggle();

			$('#arrow').toggle();
			$('#arrowup').toggle();
		}
	});
	
	// 定位
	var y=$("#choicecity");
	var _x=$("#choicecitycode");
	function getLocation(){
		getCityByIp();
  	}

  	function getCityByIp(){
		$.ajax({
		  	url:"api/car/locatecity",
			type:'get',
			//async:false,
			timeout:3500,
			success:function(data){
				res = data.trim();
				res = "北京,201";
				if(res==""){
					if(y.text()==""){
						y.text("深圳");
						_x.text("502");
						showGrouponPrice_location($("#carId").val(),_x.text());
					}
				}else{
					//ip定位成功
					if(y.text()==""){
						y.text(res.split(",")[0]);
						_x.text(res.split(",")[1]);
						showGrouponPrice_location($("#carId").val(),_x.text());
					}
				}
			},
			error:function(err){
				//alert("超时");
				if(y.text()==""){
					y.text("深圳");
					_x.text("502");
					showGrouponPrice_location($("#carId").val(),_x.text());
				}
			}
		});	
  	}
  	
//	function showPosition(position){
//		var lat = position.coords.latitude;
//		var lng = position.coords.longitude;
//		// gps定位
//		$.post('/getCityByGpsOrIp.do', {lat:lat,lng:lng}, function(res){
//			res = res.trim();
//			// gps定位失败
//			if(res==""){
//				y.text("上海");
//				showGrouponPrice_location($("#carId").val(),y.text());
//			}else{
//				// 定位成功
//				y.text(res);
//				showGrouponPrice_location($("#carId").val(),res);
//			}
//        });
//  	}

	//城市
      $(document).ready(function(){
    	  
    	  // 加载城市
          $("#provin").click(function(){
              $("#province").show();
              $("#province1").show();
              $('#province1').removeClass('Sideslip_hd02').addClass('Sideslip_hd');
              $('#province1').removeClass('Sideslip_hd02').addClass('Sideslip_hd');
          });
          $("#none").click(function(){
              $("#province").hide();
              $("#cityinfo").hide();
              $('#province1,#cityinfo1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
          });

          $("#childcity").click(function(){
              $("#cityinfo").show();
              $("#cityinfo1").show();				
              $('#cityinfo1').removeClass('Sideslip_hd02').addClass('Sideslip_hd');
          });
          $("#listnone").click(function(){
        	  $("#province").hide();
              $("#cityinfo").hide();
              $('#cityinfo1,#province1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
          });
    
        //返回
    	$('#pr-name').click(function () {
              $('#cityinfo').hide();
              $('#cityinfo1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
        });

      });
      
	function showTip(text){
	    $("#models_balloon").text(text).show();
	    setTimeout('$("#models_balloon").hide();',2000);
	}
	
	function showSuccess(){
		$(".popup-succes").show();
	    setTimeout('$(".popup-succes").hide();',2000);
	}

	  //经销商
	  $(document).ready(function(){
	      $("#showa").click(function(){
			  $("#sycabc").show();
			  $("#sycabc1").show();
			  $('#sycabc1').removeClass('Sideslip_hd02').addClass('Sideslip_hd');
			 //checked();
			  });
			  $("#hide").click(function(){
			  $("#sycabc").hide();
			  $('#sycabc1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
	      });
	  });
     
	  //end经销商
	  
	  
	  function showTip(text){
			$("#models_balloon").text(text).show();
			setTimeout('$("#models_balloon").hide();',2000);
		}