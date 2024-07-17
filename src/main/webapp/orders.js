let form= document.getElemntById("filterForm");

function DoSubmit() {
    /*
    *   action: "DateFilter" or "UserFilter" or "User-DateFilter"
     */
		
		const user = form.user.value;
		const dateMax = form.dateMax.value;
		const dateMin = form.datMin.value;
		
		document.querySelector("input.action").value= "UserFilter";
		
		alert("alert box: " + user + dateMax + dateMin + action);
		
		if(user!=="" && (dateMin!== "" || dateMax !== "")) {
			form.action.value= "User-DateFilter";
		}else if(user !== "" && dateMin=== "" && dateMax === "") {
		   form.action.value= "UserFilter";
		}else if(user==="" && (dateMin!== "" || dateMax !== "")) {
			form.action.value= "DateFilter";
		} 
		
	return true;
	
}
