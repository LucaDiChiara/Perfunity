/**
 * 
 */
function validateLogin(){
  
  var e = document.getElementById("mail").value;
  var p = document.getElementById("id-password").value;
  
  var email = validationLoginEmail(e)
  var password = validationLoginPass(p);
  
  if(email == false || password == false){
    return false;
  }
}

function validationLoginPass(password){
  if(password == ""){
    document.getElementById("id-password").style.borderColor = "red";
    return false;
  }
  else{
    document.getElementById("id-password").style.borderColor = "#ccc";
    return true;
  }
}

function validationLoginEmail(email){
  if(email == ""){
    document.getElementById("mail").style.borderColor = "red";
    return false;
  }
  else if(email.includes("@gmail.com") || email.includes("@hotmail.com") || email.includes("@libero.it") || email.includes("@yahoo.com") || email.includes("@yandex.com") || email.includes("@root")){
    document.getElementById("mail").style.borderColor = "#ccc";
    return true;
  }
  else{
    return false;
  }
}


//validazione registrazione
function validateRegistrazione(){
	var n = document.getElementById("nome").value;
	var c = document.getElementById("cognome").value;
	var e = document.getElementById("email").value;
	var p = document.getElementById("password").value;
	//var cel = document.getElementById("cellulare").value;
	var r = document.getElementById("repeat-password").value;

	var nome = validationNomeReg(n);
	var cognome = validationCognomeReg(c);
	var email = validationEmailReg(e);
	var password = validationPasswordReg(p);
	//var cellulare = validationCell(cel);
	var ripPassword = validationRipPasswordReg(r);
	var match = matchPasswordReg(p,r);

	if(nome == false || cognome == false || email == false || password == false || ripPassword == false || match == false){
		return false;
	}
	else window.alert('Registrazione effettuata con successo!');
}

function validationNomeReg(nome){

	var letters = /^[a-zA-Z\s]+$/;

	if(nome == ''){
		document.getElementById("nome").style.borderColor = "red";
		return false;
	}
	else if (!nome.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
	else{
		document.getElementById("nome").style.borderColor = "#ccc";
		document.getElementById("errore").style.display = "none";
		return true;
	}

}

function validationCell(cel){
	
	var letters = /^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$/;
	
	if(cel == ''){
		document.getElementById("cellulare").style.borderColor = "red";
		return false;
	} else if(!cel.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
	else{
		document.getElementById("cellulare").style.borderColor = "#ccc";
		return true;
	}
	
}

function validationCognomeReg(cognome){
	var letters = /^[a-zA-Z\s]+$/;

	if(cognome == ''){
		document.getElementById("cognome").style.borderColor = "red";
		return false;
	}
	else if(!cognome.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		return false;
	}
	else{
		document.getElementById("cognome").style.borderColor = "#ccc";
		document.getElementById("errore").style.display = "none";
		return true;
	}
}

function validationEmailReg(email){
	var letters = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(email == ''){
		document.getElementById("email").style.borderColor = "red";
		return false;
	}
	else if (!email.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
	else{
		document.getElementById("email").style.borderColor = "#ccc";
		document.getElementById("errore").style.display = "none";
		return true;
	}
}

function validationPasswordReg(password){
	var letters = /^[a-zA-Z0-9\_\*\-\+\!\?\,\:\;\.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{8,20}/;

	if(password == ''){
		document.getElementById("password").style.borderColor = "red";
		return false;
	}
	else if (!password.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
	else{
		document.getElementById("password").style.borderColor = "#ccc";
		document.getElementById("errore").style.display = "none";
		return true;
		
	}
}

function validationRipPasswordReg(ripPassword){

	var letters = /^[a-zA-Z0-9\_\*\-\+\!\?\,\:\;\.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{8,20}/;

	if(ripPassword == ''){
		document.getElementById("repeat-password").style.borderColor = "red";
		return false;
	}
	else if (!ripPassword.match(letters)){
		document.getElementById("errore").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
	else{
		document.getElementById("repeat-password").style.borderColor = "#ccc";
		document.getElementById("errore").style.display = "none";
		return true;
	}
}

function matchPasswordReg(p,r){
	if(p == r){
		document.getElementById("errore").style.display = "none";
		return true;
	}
	else if(p != r){
		document.getElementById("errore").innerHTML = " Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		document.getElementById("errore").style.display = "block";
		return false;
	}
}



//validazione Account
function validateModifichePassword(){
	
	var op = document.getElementById("password").value;
	var np = document.getElementById("new-password").value;
	var rp = document.getElementById("repeat-password").value;

	var password = validationVecchiaPassword(op);
	var newPassword = validationNuovaPassword(np);
	var ripPassword = validationRipPassword(rp);
	var match = matchPasswordReg(newPassword,ripPassword);


	if(password == false || newPassword == false || ripPassword == false || match == false){
		return false;
	}
	else return true;
}

function validateModificheEmail(){
	
	var oe = document.getElementById("email").value;
	var ne = document.getElementById("new-email").value;
	var re = document.getElementById("repeat-email").value;

	var email = validationVecchiaEmail(oe);
	var newEmail = validationNuovaEmail(ne);
	var ripEmail = validationRipEmail(re);
	var match = matchEmail(newEmail,ripEmail);


	if(email == false || newEmail == false || ripEmail == false || match == false){
		return false;
	}
	else return true;
}



function validationVecchiaPassword(password){
	var letters = /^[a-zA-Z0-9\_\*\-\+\!\?\,\:\;\.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{8,20}/;

	if(password == ''){
		document.getElementById("password").style.borderColor = "red";
		return false;
	}
	else if (!password.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = 'Attenzione! Errore nella compilazione dei campi.';
		return false;
	}
	else{
		document.getElementById("password").style.borderColor = "#ccc";
		return true;
	}
}

function validationNuovaPassword(nuovaPassword){
	var letters = /^[a-zA-Z0-9\_\*\-\+\!\?\,\:\;\.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{8,20}/;

	if(nuovaPassword == ''){
		document.getElementById("new-password").style.borderColor = "red";
		return false;
	}
	else if (!nuovaPassword.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = 'Attenzione! Errore nella compilazione dei campi.';
		return false;
	}
	else{
		document.getElementById("new-password").style.borderColor = "#ccc";
		return true;
	}
}

function validationRipPassword(ripPassword){
	var letters = /^[a-zA-Z0-9\_\*\-\+\!\?\,\:\;\.\xE0\xE8\xE9\xF9\xF2\xEC\x27]{8,20}/;

	if(ripPassword == ''){
		document.getElementById("repeat-password").style.borderColor = "red";
		return false;
	}
	else if (!ripPassword.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = 'Attenzione! Errore nella compilazione dei campi.';
		return false;
	}
	else{
		document.getElementById("repeat-password").style.borderColor = "#ccc";
		return true;
	}
}

function matchEmail(p,r){
	if(p == r){
		return true;
	}
	else if(p != r){
		document.getElementById("erroreModifiche").innerHTML = " Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		return false;
	}
}

function validationVecchiaEmail(email){
	var letters = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(email == ''){
		document.getElementById("email").style.borderColor = "red";
		return false;
	}
	else if (!email.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		return false;
	}
	else{
		document.getElementById("email").style.borderColor = "#ccc";

		return true;
	}
}

function validationNuovaEmail(email){
	var letters = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(email == ''){
		document.getElementById("new-email").style.borderColor = "red";
		return false;
	}
	else if (!email.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		return false;
	}
	else{
		document.getElementById("new-email").style.borderColor = "#ccc";

		return true;
	}
}

function validationRipEmail(email){
	var letters = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

	if(email == ''){
		document.getElementById("repeat-email").style.borderColor = "red";
		return false;
	}
	else if (!email.match(letters)){
		document.getElementById("erroreModifiche").innerHTML = "Attenzione! Errore nella compilazione dei campi. Controlla che i campi siano corretti e che le due password siano uguali.";
		return false;
	}
	else{
		document.getElementById("repeat-email").style.borderColor = "#ccc";

		return true;
	}
}