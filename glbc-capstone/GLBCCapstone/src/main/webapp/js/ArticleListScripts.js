$(document).ready(function () {

    loadArticles();

});

function fillArticleTable(articleList, status) {
        $('#articleTitles').empty();
        $('#articleList').css('display', 'block');
        $('#articleList').css('color', 'black');
        $('#articleList').css('background-color', 'whitesmoke');
        $('#articleList').css('border-radius', '25px');
        $('#articleList').css('padding', '50px');
        $('#articleList').css('font-family', 'sans-serif');
        
        //set title
        $('#thead').append($("<tr>").append($("<th>").attr({'colspan': '3'}).text("Old Articles")));
        
        //set row head
        $('#thead').append($("<tr>").append($("<th>").text("Author"))
                .append($("<th>").text("Article"))
                .append($("<th>").text("Summary")));
        
        //Fill rows
        var articleTable = $("#articleArchiveList");
        $.each(articleList, function (index, article) {
            articleTable.append($("<tr>")
                    .append($("<td>").text(article.userName))
                    .append($("<td>").append($("<a>").attr({'onClick': 'loadArticle(' + article.articleId + ')'}).text(article.articleName)))
                    .append($("<td>").text(article.summary))
//                    .append($("<td>").text(article.textBody))
//                    .append($("<td>").text(article.createDate))
//                    .append($("<td>").text(article.editDate))
//                    .append($("<td>").text(article.approveDate))
//                    .append($("<td>").text(article.publishDate))
                    );
        });
    }

function loadArticles() {
        $.ajax({
            url: 'articlesByApproval'
        }).success(function (data, status) {
            fillArticleTable(data, status);
        });
    }

function loadArticle(articleId) {
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
        displaySearchedArticle(data);

    }).error(function () {
        alert("This didn't work");
    });
}

function displaySearchedArticle(article, status) {
    $('#searchedArticle').empty();
    $('#searchedArticle').css('display', 'block');
    $('#articleContent').css('display', 'block');
    $('#articleContent').css('color', 'black');
    $('#articleContent').css('background-color', 'whitesmoke');
    $('#articleContent').css('border-radius', '25px');
    $('#articleContent').css('padding', '50px');
    $('#articleContent').css('font-family', 'sans-serif');
    var recentArticle = $("#searchedArticle");
    recentArticle.append($("<div.articleTitle>").addClass('articleTitle').html(article.articleName))
			.append($("<a>").attr({'onClick': 'loadArticlesByCategoryId(' + article.categoryId + ')'}).append($("<div.articleCategory>").html("Category: " + article.categoryName)))
            .append($("<div.userName>").addClass('articleUserName').html(article.userName))
            .append($("<div.publicationDate>").addClass('articlePublicationDate').html(article.publishDate))
            .append($("<div.textBody>").html(article.textBody));

}

function loadArticlesByCategoryId(categoryId, status) {
	
	clearStaticPage();
	clearCSSFormatting();
	clearSearchTables();
	clearDetailsPanes();
	
    $.ajax({
        type: 'GET',
        url: 'articles/' + categoryId,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).success(function (articleList, status) {
		$("#searchResultArticleTableHead").text("Category: " + articleList[0].categoryName);
        fillArticleTableByCategory(articleList);
    }).error(function () {
        alert("loadArticlesByCacategorytegoryId didn't work");
    });
}

function fillArticleTableByCategory(articleList) {
	
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
}