/*
 * 使用方法：
 * 	  1.页面引入;
 * 	  2.在需要验证的文本对象中 添加属性 valid = "验证类型,弹框提示";
 * 	  3.提交方式
 * 		用ajax提交	在ajax中加：beforeSend: function(){return check_val();},
 * 		or
 * 		if(check_val()){  等于true 提交}
 *  valid = "feik,alert=用户名"
 */
/*
 * 参数详情
 *
 * alert:弹框信息
 * minlen:最小长度
 * maxlen:最大长度
 * jine：金额
 * youx：邮箱
 * dianh：电话
 * qq：QQ
 * select：多选框，默认判断为0
 * zhongwen:中文
 * shoujihaom:手机号码
 * zhengshu：整数
 * riqi:日期
 */
//验证方法两个参数 第一个为文本对象，第二个为弹出信息
function check_val(){
	var vali = $("[valid]");
	//vali.length 获取所有带vali属性
	//vali[i].attributes["valid"].value;：获取valid的值
	//vali[i].value：获取带有valid属性 对象的value的值
	var boo = false;
	for (var i = 0;i<vali.length; i++){
		var method = vali[i].attributes["valid"].value.split(",");
		var aler="";
		for(var j=0;j<method.length;j++){
			var array1=[];
			array1 = method[j].split("=");
			if(array1.length > 1 && array1[0] == "alert"){
				aler = array1[1];
				break;
			}
		}
		boo = false;
		var ret;
		for(var k=0;k<method.length;k++){
			var str = method[k];
			var array2 = [];
			array2 = str.split("=");
			if(array2.length == 1){
				if(array2 == 'feik'){ret = feik(vali[i],aler);}
				else if(array2 == 'jine'){ret = jine(vali[i],aler);}
				else if(array2 == 'zhongwen'){ret = zhongwen(vali[i],aler);}
				else if(array2 == 'youx'){ret = youx(vali[i],aler);}
				else if(array2 == 'dianh'){ret = dianh(vali[i],aler);}
				else if(array2 == 'qq'){ret = qq(vali[i],aler);}
				else if(array2 == 'select'){ret = select(vali[i],aler);}
				else if(array2 == 'shoujihaom'){ret = shoujihaom(vali[i],aler);}
				else if(array2 == 'zhengshu'){ret = zhengshu(vali[i],aler);}
				else if(array2 == 'riqi'){ret = riqi(vali[i],aler);}
			}else if(array2.length == 2){
				if(array2[0] == 'minlen'){ret = minlen(vali[i],array2[1],aler);}
				if(array2[0] == 'maxlen'){ret = maxlen(vali[i],array2[1],aler);}
			}
			if(!ret){
				return false;
			}
			boo = true;
		}
	}
	return boo;
}
function win_alert(val){
	var reg=/^\S/;
	if(reg.test(val)){alert(val);}
}

/*非空*/
function feik(doc,text){
	var value = doc.value;
	var reg=/^\S/;
	if(value.replace(/^\s+|\s+$/g,'') == ''){win_alert("请输入-"+text);doc.select();return false;}else{return true;}
}

/*中文*/
function zhongwen(doc,text){
	var value = doc.value;
	//value=value.replace(/[^\u4E00-\u9FA5]/g,'');
	var reg=/^[\u4e00-\u9fa5]+$/;
	if(!reg.test(value)){win_alert(text+"-只能输入中文字符");doc.select();return false;}else{return true;}
}

/*手机号码*/
function shoujihaom(doc,text){
	var value = doc.value;
	var reg=/^[1][358][0-9]{9}$/;
	if(!reg.test(value)){win_alert("请正确输入："+text);doc.select();return false;}else{return true;}
}
/*select选择*/
function select(doc,text){
	var value = doc.value;
	if(value == '0'){win_alert("请选择"+text);doc.select();return false;}else{return true;}
}
/*金额*/
function jine(doc,text){
	var reg = /^(([1-9]\d*)|0)(\.\d{1,2})?$/;
	var value = doc.value;
	if(!reg.test(value)){win_alert("请正确输入："+text);doc.select();return false;}else{return true;}
}
/*邮箱*/
function youx(doc,text){
	var value = doc.value;
	var reg=/^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
    var reg1=/^\S/;
	var boo = true;
	if(reg1.test(value)){
		if(!reg.test(value)||!reg.test(value)){win_alert("请正确输入："+text);doc.select();boo = false;}else{boo = true;}
	}
	return boo;
}
/*最小长度为val*/
function minlen(doc,val,text){
	if(doc.value.length < val){alert(text+"-长度必须大于"+val+"位");doc.select();return false;}else{return true;}
}
/*最大长度为val*/
function maxlen(doc,val,text){
	if(doc.value.length > val){alert(text+"-长度必须小于"+val+"位");doc.select();return false;}else{return true;}
}
/*电话*/
function dianh(doc,text){}
/*QQ*/
function qq(doc,text){}

/*整数*/
function zhengshu(doc,text){
	var value = doc.value;
	var reg=/^[1-9]\d*$/;
	var reg1=/^\S/;
	var boo = true;
	if(reg1.test(value)){
		if(!reg.test(value)){win_alert(text+"-只能输入整数");doc.select();return false;}else{return true;}
	}
	return boo;
}
/*日期*/
function riqi(doc,text){
	var value = doc.value;
	var reg=/^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8])))/;
	if(!reg.test(value)){win_alert("请输入正确的日期格式");doc.select();return false;}else{return true;}
}


