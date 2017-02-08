function brewerySearchAjaxCall(searchCriteria) {
	$.ajax({
		method: "POST",
		url: "searchBrewery",
		data: JSON.stringify({brewerySearchCriteria: searchCriteria}),
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).success(function (data) {
		fillBrewerySearchTable(data, status);
	});
}

function fillBrewerySearchTable(searchResults, status) {
	
	clearSearchTables();
		
	$('#searchResultBreweryTable').css('display', 'block');
	$('#searchResultBreweryTable').css('color', 'black');
	$('#searchResultBreweryTable').css('background-color', 'whitesmoke');
	$('#searchResultBreweryTable').css('border-radius', '25px');
	$('#searchResultBreweryTable').css('padding', '50px');
	$('#searchResultBreweryTable').css('font-family', 'sans-serif');
	
	var brewerySearchResultsTable = $("#searchResultBreweryTableBody");
	
	$.each(searchResults, function (index, brewery) {
		brewerySearchResultsTable.append($("<tr>")
				.append($("<td>").append($("<a>").attr({'onClick': 'loadBrewery(' + brewery.breweryId + ')'}).text(brewery.breweryName)))
				.append($("<td>").text(brewery.city + ", " + brewery.state))
				.append($("<td>").text(brewery.summary)));
	});
}

function loadBrewery(breweryId) {
    $.ajax({
        type: 'GET',
        url: 'brewery/' + breweryId,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).success(function (data) {
        displaySearchedBrewery(data);
    }).error(function () {
        alert("This didn't work");
    });
}

function displaySearchedBrewery(brewery) {
	
	clearDetailsPanes();
	
    $('#searchResultBreweryContent').css('display', 'block');
    $('#searchResultBreweryContent').css('color', 'black');
    $('#searchResultBreweryContent').css('background-color', 'whitesmoke');
    $('#searchResultBreweryContent').css('border-radius', '25px');
    $('#searchResultBreweryContent').css('padding', '50px');
    $('#searchResultBreweryContent').css('font-family', 'sans-serif');
	
    var searchedBrewery = $("#searchResultBrewery");
	
    searchedBrewery.append($("<div.searchResultBreweryName>").addClass('searchResultBreweryName').html(brewery.breweryName))
			.append($("<div.searchResultBreweryLocation>").addClass('searchResultBreweryLocation').html(brewery.city + ", " + brewery.state))
            .append($("<div.searchResultBreweryTextBody>").addClass('searchResultBreweryTextBody').html(brewery.textBody));
}