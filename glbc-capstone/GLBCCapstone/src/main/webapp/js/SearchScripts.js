$(document).ready(function () {
	$("#select-search-type").change(function() {
		
		var searchType = $(this).val();
		
		if(searchType === "Beer") {
			document.getElementById("search-criteria-box").placeholder = "Name/Style/Hop";
		}
		
		else if(searchType === "Brewery") {
			document.getElementById("search-criteria-box").placeholder = "Name/City/State/Brewmaster";
		}
		
		else if(searchType === "Articles") {
			document.getElementById("search-criteria-box").placeholder = "Name/Author";
		}
	});
	
	$("#search-button").click(function(event) {
		event.preventDefault();
		
		var searchType = $("#select-search-type").val();
		var searchCriteria = $("#search-criteria-box").val();
		
		clearSearchTables();
		
		if(searchType != "") {
			clearStaticPage();
			clearCSSFormatting();
		}
		
		if(searchType == "Articles") {
			articleSearchAjaxCall(searchCriteria);
		}
		
		else if(searchType == "Beer") {
			beerSearchAjaxCall(searchCriteria);
		}
		
		else if(searchType == "Brewery") {
			brewerySearchAjaxCall(searchCriteria);
		}
	});
});

function clearStaticPage() {
	$("#beerList").empty();
	$("#breweryList").empty();
	$("#articleList").empty();
	$("#beerDetails").empty();
	$("#breweryDetails").empty();
}

function clearSearchTables() {
	$("#searchResultArticlesTableBody").empty();
	$("#searchResultArticleTextBody").empty();
	$("#searchResultBeerTableBody").empty();
	$("#searchResultBreweryTableBody").empty();
}

function clearDetailsPanes() {
	$("#articleContent").empty();
	$("#searchResultArticle").empty();
	$("#searchResultBeer").empty();
	$("#searchResultBrewery").empty();
}

function clearCSSFormatting() {
	//clear all the css on articleArcive.jsp
	$("#articleContent").css("display", "none");
	$("#articleTitleList").css("display", "none");
	$("#articleList").css("display", "none");
	
	//clear all the css on beerList.jsp
	$("#beerList").css("display", "none");
	
	//clear all the css on breweryList.jsp
	$("#breweryList").css("display", "none");
	
	//clear all the css on searchResultFragment.jsp
	$("#searchResultArticleTable").css("display", "none");
	$("#searchResultBeerTable").css("display", "none");
	$("#searchResultBreweryTable").css("display", "none");
	
	$("#searchResultArticleContent").css("display", "none");
	$("#searchResultBeerContent").css("display", "none");
	$("#searchResultBreweryContent").css("display", "none");
        
	$('#categoryListDiv').css("display", "none");
	
	//clear all the css on viewBeer/Brewery.jsp
	$("#beerDetails").css("display", "none");
	$("#breweryDetails").css("display", "none");
}