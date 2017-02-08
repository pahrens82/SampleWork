function articleSearchAjaxCall(searchCriteria) {
	$.ajax({
		method: "POST",
		url: "searchArticle",
		data: JSON.stringify({articleSearchCriteria: searchCriteria}),
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).success(function (data) {
		fillArticleSearchTable(data, status);
	});
}

function fillArticleSearchTable(searchResults, status) {
	
	clearSearchTables();
	
    $('#searchResultArticleTable').css('display', 'block');
    $('#searchResultArticleTable').css('color', 'black');
    $('#searchResultArticleTable').css('background-color', 'whitesmoke');
    $('#searchResultArticleTable').css('border-radius', '25px');
    $('#searchResultArticleTable').css('padding', '50px');
    $('#searchResultArticleTable').css('font-family', 'sans-serif');
	
    var articleSearchResultsTable = $("#searchResultArticlesTableBody");
	
    $.each(searchResults, function (index, article) {
        articleSearchResultsTable.append($("<tr>")
                .append($("<td>").text(article.userName))
                .append($("<td>").append($("<a>").attr({'onClick': 'loadArticleFromSearch(' + article.articleId + ')'}).text(article.articleName)))
                .append($("<td>").text(article.summary))
                );
    });
}

function loadArticleFromSearch(articleId) {
    $.ajax({
        type: 'GET',
        url: 'article/' + articleId,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).success(function (data) {
        displaySearchedArticleFromSearch(data);
    }).error(function () {
        alert("This didn't work");
    });
}

function displaySearchedArticleFromSearch(article, status) {
	
	clearDetailsPanes();
	
    $('#searchResultArticleContent').css('display', 'block');
    $('#searchResultArticleContent').css('color', 'black');
    $('#searchResultArticleContent').css('background-color', 'whitesmoke');
    $('#searchResultArticleContent').css('border-radius', '25px');
    $('#searchResultArticleContent').css('padding', '50px');
    $('#searchResultArticleContent').css('font-family', 'sans-serif');
	
    var recentArticle = $("#searchResultArticle");
	
    recentArticle.append($("<div.searchResultArticleTitle>").addClass('searchResultArticleTitle').html(article.articleName))
			.append($("<a>").attr({'onClick': 'loadArticlesByCategoryId(' + article.categoryId + ')'}).append($("<div.searchResultArticleCategory>").html("Category: " + article.categoryName)))
            .append($("<div.searchResultArticleUserName>").addClass('searchResultArticleUserName').html(article.userName))
            .append($("<div.searchResultArticlePublicationDate>").addClass('searchResultArticlePublicationDate').html(article.publishDate))
            .append($("<div.searchResultArticleTextBody>").html(article.textBody));
			
}