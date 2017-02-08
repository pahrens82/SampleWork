$(document).ready(function () {

    showRecentArticle();

});

function showRecentArticle() {
    $.ajax({
        url: 'mostRecentArticle'
    }).success(function (data, status) {
        displayMostRecentArticle(data, status);
    });
}

function displayMostRecentArticle(mostRecentArticle) {
    $('#searchedArticle').empty();
    $('#articleContent').css('display', 'block');
    $('#articleContent').css('color', 'black');
    $('#articleContent').css('background-color', 'whitesmoke');
    $('#articleContent').css('border-radius', '25px');
    $('#articleContent').css('padding', '50px');
    $('#articleContent').css('font-family', 'sans-serif');
    var recentArticle = $("#searchedArticle");
    $.each(mostRecentArticle, function (status, article) {
        recentArticle.append($('<div class="container-fluid">')
                .append($('<div class="articleTitle">').html(article.articleName))
                .append($('<tr>')
                        .append($('<td>')
                                .append($('<a>')
                                        .attr({'onClick': 'loadArticlesByCategoryId(' + article.categoryId + ')'})
                                        .append($('<div class="articleCategory">')).text("Category: " + article.categoryName)))
                        )
                .append($('<tr>')
                        .append($('<td>')
                                .html("Author: " + article.userName))
                        )
                .append($('<tr>')
                        .append($('<td class="publicationDate">')
                                .html(article.publishDate))
                        )
                .append($('<tr>')
                        .append($('<td>')
                                .append($('<div class="textBody">')).html(article.textBody))
                        )
                );

    });
}

//function fillArticleTableByCategory(articleList) {
//    var articleTable = $("#articlesList");
//    $('#thead').empty();
//    $('#articlesList').empty();
//    $('#categoryListDiv').css('display', 'block');
//    $('#categoryListDiv').css('color', 'black');
//    $('#categoryListDiv').css('background-color', 'whitesmoke');
//    $('#categoryListDiv').css('border-radius', '25px');
//    $('#categoryListDiv').css('padding', '30px');
//    $('#categoryListDiv').css('font-family', 'sans-serif');
//    $('#thead').append($("<th>").css('display', 'block').text("Catagory: " + articleList[0].categoryName));
//    $.each(articleList, function (status, article) {
//        articleTable.append($("<tr>")
//                .append($("<td>").append($("<a>").attr({'onClick': 'loadArticleByArticleId(' + article.articleId + ')'}).text(article.articleName)))
//                .append($("<td>").css('display', 'block').text(article.summary)));
//    });
//
//}

function loadArticleByArticleId(articleId, status) {
    $.ajax({
        type: 'GET',
        url: 'article/' + articleId,
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        contentType: "application/json; charset=utf-8",
        dataType: "json"
    }).success(function (article) {
        displayArticleByArticleId(article, status);
    }).error(function () {
        alert("Unable to find article");
    });
}

function displayArticleByArticleId(article) {
    $('#recentArticle').empty();
    $('#articleContent').css('display', 'block');
    $('#articleContent').css('color', 'black');
    $('#articleContent').css('background-color', 'whitesmoke');
    $('#articleContent').css('border-radius', '25px');
    $('#articleContent').css('padding', '50px');
    $('#articleContent').css('font-family', 'sans-serif');
    var recentArticle = $("#recentArticle");
    recentArticle.css('display', 'block');
    recentArticle.append($('<div class="container-fluid">')
            .append($('<div class="articleTitle">').html(article.articleName))
            .append($('<tr>')
                    .append($('<td>')
                            .append($('<a>')
                                    .attr({'onClick': 'loadArticlesByCategoryId(' + article.categoryId + ')'})
                                    .append($('<div class="articleCategory">')).text("Category: " + article.categoryName)))
                    )
            .append($('<tr>')
                    .append($('<td>')
                            .html("Author: " + article.userName))
                    )
            .append($('<tr>')
                    .append($('<td class="publicationDate">')
                            .html(article.publishDate))
                    )
            .append($('<tr>')
                    .append($('<td>')
                            .append($('<div class="textBody">')).html(article.textBody))
                    )
            );

}