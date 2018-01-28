
function controlLength(obj, length) {
			if(obj.value.length != length) 
				obj.style.border = "1px solid red";
			else
				obj.style.border = "1px solid rgb(169,169,169)";
}


function validateLoginForm(form) {
	var input = form.getElementsByTagName('input');
	
	if(controlParLogin(input[0]) && controlParLogin(input[1]))
		return true;
	
	return false;
}



function controlParLogin(input) {
	if(input.name == "email") {
		if(!validateEmail(input.value)) {
			document.getElementsByName(input.name)[0].style.border = "1px solid red";
			document.getElementsByName(input.name)[0].style.outline = "none";
			return false;
		}else
			document.getElementsByName(input.name)[0].style.border = "1px solid rgb(169,169,169)";
	}
	
	if(input.name == "password") {
		var controlPass = /[a-zA-Z0-9]{8,30}/;
		if(!controlPass.test(input.value)) {
			document.getElementsByName(input.name)[0].style.border = "1px solid red";
			document.getElementsByName(input.name)[0].style.outline = "none";
			return false;
		}else
			document.getElementsByName(input.name)[0].style.border = "1px solid rgb(169,169,169)";
	}
	return true;
}








function showContent(idContent, idButton){
	$('#'+idContent).show();
	$('#'+idButton).hide();
}

function hideContent(idContent, idButton){
	$('#'+idContent).hide();
	$('#'+idButton).show();
}

function controlPasswordLength(password, error){
	var pwd= $("#"+password);
	if(pwd.val().length > 0){
		if(pwd.val().length < 8 ) {
			pwd.css({
				'border-color' : 'red',
				'border-width' : '2px'
			});
			$('#errorMessage').html("Minimum lenght: 8 characters");
		}else{
			pwd.css({
				'border-color' : 'green',
				'border-width' : '2px'
			});
			console.log(pwd.val());
			$('#errorMessage').html("");
		}
	}else{
		pwd.css({
			'border-color' : 'red',
			'border-width' : '2px'
		});
		$('#errorMessage').html("Minimum lenght: 8 characters");
	}
}

function controlMatchingPassword(againPassword, newPassword){
	var againPwd = $('#'+againPassword);
	var newPwd = $('#'+newPassword);

	console.log("again: "+againPwd.val());
	console.log("new: "+newPwd.val());
	
	if(againPwd.val() != newPwd.val()){
		$('#errorMessage').html("Password Mismatching");
		return false;
	}else{
		$('#errorMessage').html("");
		return true;
	}
}

function validateEmail(email) { 
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    return re.test(email);
} 


function validateForm(form) {
	var inputs = form.getElementsByTagName('input');
	var popups = form.getElementsByClassName("popuptext");
	var flag = false;
	for(i = 0 ; i < 5; i++) {
		console.log(inputs[i]);
		console.log(popups[i]);
		if(controlParameter(inputs[i],popups[i]))
			flag = true;
		else
			return false;
		if(i == 4) {
			if(controlParameter(inputs[i],popups[i],inputs[3]))
				flag = true;
			else
				return false;
		}
	}

	return flag;
}

function controlParameter(input,popup, password) {
	var popup= document.getElementById(popup);
	
	//NOME
	if(input.name == "nome"   && input.value != 0 && input.value.length < 2 ) {
		popup.style.visibility="visible";
		popup.innerHTML= "Name length must be over 2 char";
		return false;
	}else {
		popup.style.visibility="hidden";
	}
	//COGNOME
	if(input.name == "cognome"   && input.value != 0 && input.value.length < 2 ) {
		popup.style.visibility="visible";
		popup.innerHTML= "Surname length must be over 2 char";
		return false;
	}else {
		popup.style.visibility="hidden";
	}
	//EMAIL
	if(input.name == "email"   && !validateEmail(input.value)) {
		popup.style.visibility="visible";
		popup.innerHTML= "Incorrect email format";
		return false;
	}else {
		popup.style.visibility="hidden";
	}
	//PASSWORD
	if(input.name == "password"   && input.value != 0 && input.value.length < 8 ) {
		popup.style.visibility="visible";
		popup.innerHTML= "Password length must be over 8 char";
		return false;
	}else {
		popup.style.visibility="hidden";
	}
	//REPETE PASSWORD
	if(input.name == "againpassword"  && input.value != password.value ) {
		popup.style.visibility="visible";
		popup.innerHTML= "Password mismatching";
		return false;
	}else {
		popup.style.visibility="hidden";
	}
	
	return true;
	
}