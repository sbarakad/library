
function disableOrEnableDetailCheckboxes(selectionQuery, isToDisable) {
	var elements = document.querySelectorAll(selectionQuery);
	  for (let i = 0; i < elements.length; i++) {
	      elements[i].disabled = isToDisable;
	      console.log(elements[i] + ":::::" + elements[i].disabled);
	  }
}

function makeDetailsCheckboxesDesableEnableDependingOnSelectedCategory() {
	var categories = ["searchInBooks", "searchInMovies", "searchInMusic"]

	for (let i = 0; i < categories.length; ++i) {
		document.getElementById(categories[i]).onchange = function() {
		    disableOrEnableDetailCheckboxes("#" + categories[i] + "Details" + " > div > input", 
		    		!document.getElementById(categories[i]).checked);
		}
	}
}

function enableDetailsCheckboxesIfCategorySelected() {
	var categories = ["searchInBooks", "searchInMovies", "searchInMusic"]
	for (let i = 0; i < categories.length; ++i) {
		if(document.getElementById(categories[i]).checked) {
			disableOrEnableDetailCheckboxes("#" + categories[i] + "Details > div > input", false);
		}
	}	
}

disableOrEnableDetailCheckboxes("#allDetailCheckboxes > td > div > div > input", true);
disableOrEnableDetailCheckboxes("#serchCategories > div > input", false);
makeDetailsCheckboxesDesableEnableDependingOnSelectedCategory();
enableDetailsCheckboxesIfCategorySelected();


document.getElementById("pageBody").style.display="block";

