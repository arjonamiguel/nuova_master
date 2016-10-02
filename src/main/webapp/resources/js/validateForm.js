function isValidForm(container, fieldsRequired) {
	document.getElementById(container).innerHTML = '';
	var retorno = true;
	var itemsRequired = "";
	var requireds = "";
	for (i = 0; i < fieldsRequired.length; i++) {
		var arrField = fieldsRequired[i].split(':');
		var _id = arrField[0];
		var _label = arrField[1];
		var field = document.getElementById(_id).value;
		if(field == "" || field == "-1" || field == "NONE" || (!$("#"+_id).is(':checked') && field=="on")) {
			itemsRequired = itemsRequired + "<li>" + _label + "</li>";
			if (retorno) {
				retorno = false;
			}
		} 
	}
	
	requireds = "<ul>" + itemsRequired + "</ul>";
	if (!retorno) {
		document.getElementById(container).innerHTML = createWarning(requireds);	
	}	
	
	return retorno;
}

function createWarning(requireds) {
	var warning ="<div class='alert'>" 
		+ "<button type='button' class='close' data-dismiss='alert'>&times;</button>"
		+ "<strong>Usted debe completar los Campos Requeridos (*)</strong>"
		+ requireds 
		+ "</div>";
	
	return warning;
}
