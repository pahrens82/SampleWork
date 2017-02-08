$(document).on('click', '.tag', function () {
	var string = $(this).data("tagname");
	
	clearStaticPage();
	clearCSSFormatting();
	clearSearchTables();
	clearDetailsPanes();

	loadArticlesByTagName(string);
});

function loadArticlesByTagName(string) {
	var tagName = string;
	//df
	$('#thead').empty();
	$('#thead').append($("<th>").css('display', 'block').text("Tag: " + tagName));
	$.ajax({
		type: 'GET',
		url: 'tag/' + tagName,
		headers: {
			"Accept": "application/json",
			"Content-Type": "application/json"
		},
		contentType: "application/json; charset=utf-8",
		dataType: "json"
	}).success(function (articleList, status) {
		$("#searchResultArticleTableHead").text("Tag: " + tagName);
		fillArticleTableByTagName(articleList);
	}).error(function () {
		alert("loadArticlesByTagName didn't work");
	});
}

function fillArticleTableByTagName(articleList) {

	//for ArticleArchive
	var location = window.location.pathname;
//	if (location.includes("articleArchive")) {
		var articleTable = $("#searchResultArticleList");

		$("#searchResultArticlesTableBody").empty();
		
		$('#searchResultArticleTable').css('display', 'block');
		$('#searchResultArticleTable').css('color', 'black');
		$('#searchResultArticleTable').css('background-color', 'whitesmoke');
		$('#searchResultArticleTable').css('border-radius', '25px');
		$('#searchResultArticleTable').css('padding', '50px');
		$('#searchResultArticleTable').css('font-family', 'sans-serif');

		var articleSearchResultsTable = $("#searchResultArticlesTableBody");

		$.each(articleList, function (index, article) {
			articleSearchResultsTable.append($("<tr>")
					.append($("<td>").text(article.userName))
					.append($("<td>").append($("<a>").attr({'onClick': 'loadArticleFromSearch(' + article.articleId + ')'}).text(article.articleName)))
					.append($("<td>").text(article.summary))
					);
		});
//	} else {
//		var articleTable = $("#articlesList");
//		$('#articleContent').css('display', 'none');
//		$('#articlesList').empty();
//		$('#recentArticle').empty().css('display', 'none');
//		$('#categoryListDiv').css('display', 'block');
//		$('#categoryListDiv').css('color', 'black');
//		$('#categoryListDiv').css('background-color', 'whitesmoke');
//		$('#categoryListDiv').css('border-radius', '25px');
//		$('#categoryListDiv').css('padding', '30px');
//		$('#categoryListDiv').css('font-family', 'sans-serif');
//
//		$.each(articleList, function (status, article) {
//			articleTable.append($("<tr>")
//					.append($("<td>").append($("<a>").attr({'onClick': 'loadArticleByArticleId(' + article.articleId + ')'}).text(article.articleName)))
//					.append($("<td>").css('display', 'block').text(article.summary)));
//		});
//	}
//



}