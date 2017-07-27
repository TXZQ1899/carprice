/**
 * Created by Administrator on 2016/7/28.
 */
(function(JQuery){
    $._right={};
    $.extend($._right,{
        proviceDom:$("#province1"),
        cityDom:$("#cityinfo1"),
        isOne:false,
        loadingDataErr: function () {
            return;
        },
        provinceData:'',
        cityData:'',
        getProvice: function () {
            $.ajax({
                url:'http://nc.xiaobaigouche.com/cybcar/static/homeCarAlert/js/city.json',
                data:'JSON',
                beforeSend: function (XMLHttpRequest) {

                },
                success: function (data) {
                    //请求成功
                    if (data && data != "") {
                        if(data.info.error=='0'){
                            $._right.provinceData = data.info.province;
                            //$._right.initProviceDom();
                        }else{
                            //数据获取失败
                            $._right.loadingDataErr();
                        }
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {

                },
                error: function () {
                    $._right.loadingDataErr();
                }
            });
        },
        getCity:function(code,proviceName){
            if($._right.provinceData){//先有省份
                $.each($._right.provinceData,function(k,v){
                    if(proviceName.match(v['name'])){
                        $._right.cityData = v['city'];
                        $._right.initCityDom(proviceName);
                        return;
                    };
                });
            }
        },
        initProviceDom:function(){
            if(this.isOne){
                $._right.proviceDom.empty();
                $._right.proviceDom.find('dd');
            }
        },
        initCityDom:function(proviceName){
            var _ul = $._right.cityDom.find('ul');
            _ul.empty();
            var _lis = '';
            if($._right.cityData.length && $._right.cityData.length>0){
                $.each($._right.cityData,function(k,v){
                    _lis+='<li data-code=\"'+v['code']+'\" data-py=\"'+v['py']+'\">'+v['name']+'</li>'
                });
            }else{
                _lis+='<li data-code=\"'+$._right.cityData['code']+'\" data-py=\"'+$._right.cityData['py']+'\">'+$._right.cityData['name']+'</li>'
            }

            _ul.html(_lis);
            
            $("#cityinfo").show();
            $("#cityinfo1").show();
            $('#cityinfo1,#province1').removeClass('Sideslip_hd02').addClass('Sideslip_hd');
            $('#cityinfo1').find('li').click(function() {
            	$("#selectprovince").text(proviceName);
                $('.cityname').text($(this).text());
                $("#choicecitycode").text($(this).attr('data-code'));
                $('#cityinfo,#province').hide();
                $('#cityinfo1,#province1').removeClass('Sideslip_hd').addClass('Sideslip_hd02');
                showGrouponPrice_location($("#carId").val(), $(this).attr('data-code'));
            });
        }
    })


})($);

$(document).ready(function () {
    $._right.getProvice();
});