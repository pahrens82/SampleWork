function beerSearchAjaxCall(searchCriteria) {
	$.ajax({
		method: "POST",
		url: "searchBeer",
		data: JSON.stringify({beerSearchCriteria: searchCriteria}),
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).success(function (data) {
		fillBeerSearchTable(data, status);
	});
}

function fillBeerSearchTable(searchResults, status) {
	
	clearSearchTables();
		
	$('#searchResultBeerTable').css('display', 'block');
	$('#searchResultBeerTable').css('color', 'black');
	$('#searchResultBeerTable').css('background-color', 'whitesmoke');
	$('#searchResultBeerTable').css('border-radius', '25px');
	$('#searchResultBeerTable').css('padding', '50px');
	$('#searchResultBeerTable').css('font-family', 'sans-serif');
	
	var beerSearchResultsTable = $("#searchResultBeerTableBody");
	
	$.each(searchResults, function (index, beer) {
		beerSearchResultsTable.append($("<tr>")
				.append($("<td>").append($("<a>").attr({'onClick': 'loadBeer(' + beer.beerId + ')'}).text(beer.beerName)))
				.append($("<td>").text(beer.summary)));
	});
}

function loadBeer(beerId) {
    $.ajax({
        type: 'GET',
        url: 'beer/' + beerId,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).success(function (data) {
        displaySearchedBeer(data);
    }).error(function () {
        alert("This didn't work");
    });
}

function displaySearchedBeer(beer) {
	
	clearDetailsPanes();
	
    $('#searchResultBeerContent').css('display', 'block');
    $('#searchResultBeerContent').css('color', 'black');
    $('#searchResultBeerContent').css('background-color', 'whitesmoke');
    $('#searchResultBeerContent').css('border-radius', '25px');
    $('#searchResultBeerContent').css('padding', '50px');
    $('#searchResultBeerContent').css('font-family', 'sans-serif');
	
    var recentArticle = $("#searchResultBeer");
	
    recentArticle.append($("<div.searchResultBeerName>").addClass('searchResultBeerName').html(beer.beerName))
            .append($("<div.searchResultBeerStyle>").addClass('searchResultBeerStyle').html('Style: ' + beer.style))
            .append($("<div.searchResultBeerHop>").addClass('searchResultBeerHop').html('Hops: ' + beer.hop))
            .append($("<div.searchResultBeerAbv>").addClass('searchResultBeerAbv').html('ABV: ' + beer.abv))
            .append($("<div.searchResultBeerIbu>").addClass('searchResultBeerIbu').html('IBU: ' + beer.ibu))
            .append($("<div.searchResultBeerTextBody>").html(beer.textBody));
}