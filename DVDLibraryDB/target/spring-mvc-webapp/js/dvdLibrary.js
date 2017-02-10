$(document).ready(function () {
    loadDvds();


    $("#add-button").click(function (event) {
        event.preventDefault();
        $.ajax({
            type: "POST",
            url: "createDvd",
            "dataType": "json",
            data: JSON.stringify({
                title: $("#add-title").val(),
                date: $("#add-date").val(),
                director: $("#add-director").val(),
                studio: $("#add-studio").val(),
                rating: $("#add-rating").val(),
                notes: $("#add-notes").val()
            }),
            contentType: 'application/json; charset=utf-8',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }
        }).success(function (data, status) {
            $("#add-title").val("");
            $("#add-date").val("");
            $("#add-director").val("");
            $("#add-studio").val("");
            $("#add-rating").val("");
            $("#add-notes").val("");
            $('#validationErrors').empty();
            loadDvds();
        });


        $("#edit-button").click(function (event) {
            event.preventDefault();
            $.ajax({
                type: "PUT",
                url: "dvd/" + $("#edit-id").val(),
                "data-type": "json",
                data: JSON.stringify({
                    dvdId: $("#edit-id").val(),
                    title: $("#edit-title").val(),
                    date: $("#edit-date").val(),
                    director: $("#edit-director").val(),
                    studio: $("#edit-studio").val(),
                    rating: $("#edit-rating").val(),
                    notes: $("#edit-notes").val()
                }),
                headers: {
                    "Accept": "application/json",
                    "Content-Type": "application/json"
                }
            }).success(function (data, status) {
                loadDvds();
            });
        });


        $("#search-button").click(function (event) {
            alert("Search button works");
            event.preventDefault();
            $.ajax({
                type: "GET",
                url: "search/dvd",
                dataType: "json:",
                data: JSON.stringify({
                    title: $("#search-title").val(),
                    date: $("#search-date").val(),
                    director: $("#search-director").val(),
                    studio: $("#search-studio").val(),
                    rating: $("#search-rating").val()
                }),
                headers: {
                    "Accept": "applcation/json",
                    "Content-Type": "application/json"
                }
            }).success(function (data, status) {
                $("#search-title").val("");
                $("#search-date").val("");
                $("#search-director").val("");
                $("#search-studio").val("");
                $("#search-rating").val("");
                fillDvdTable(data, status);

            });

        });

    });

});
function loadDvds() {
    clearDvdTable();
    $.ajax({
        url: "loadDvds"
    }).success(function (data, status) {
        fillDvdTable(data, status);
    });
}

function fillDvdTable(dvdList, status) {
    var dvdTable = $("#dvdContent");
    $.each(dvdList, function (index, dvd) {
        dvdTable.append($("<tr>")
                .append($("<td>")
                        .append($("<a>")
                                .attr({
                                    "data-dvd-id": dvd.dvdId,
                                    "data-toggle": "modal",
                                    "data-target": "#detailsModal"
                                })
                                .text(dvd.title)
                                )
                        )
                .append($("<td>").text(dvd.director))
                .append($("<td>")
                        .append($("<a>")
                                .attr({
                                    "data-dvd-id": dvd.dvdId,
                                    "data-toggle": "modal",
                                    "data-target": "#editModal"
                                })
                                .text("Edit")
                                )
                        )
                .append($("<td>")
                        .append($('<a>')
                                .attr({'onCLick': 'deleteDvd(' + dvd.dvdId + ')'}).text("Delete")))
                );
    });
}

function deleteDvd(id) {
    var answer = confirm("Are you sure you want to remove this DVD?");
    if (answer === true) {
        $.ajax({
            type: "DELETE",
            url: "dvd/" + id
        }).success(function () {
            loadDvds();
        });
    }
}

function clearDvdTable() {
    $("#dvdContent").empty();
}

$("#detailsModal").on("show.bs.modal", function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data("dvd-id");
    var modal = $(this);
    $.ajax({
        type: "GET",
        url: "getDvd/" + dvdId
    }).success(function (dvd) {
        modal.find("#dvd-title").text(dvd.title);
        modal.find("#dvd-date").text(dvd.date);
        modal.find("#dvd-director").text(dvd.director);
        modal.find("#dvd-studio").text(dvd.studio);
        modal.find("#dvd-rating").text(dvd.rating);
        modal.find("#dvd-notes").text(dvd.notes);
    });
});


$("#editModal").on("show.bs.modal", function (event) {
    var element = $(event.relatedTarget);
    var dvdId = element.data("dvd-id");
    var modal = $(this);
    $.ajax({
        type: "GET",
        url: "updateDvd/" + dvdId
    }).success(function (dvd) {
        modal.find("#dvd-id").text(dvd.dvdId);
        modal.find("#edit-title").text(dvd.title);
        modal.find("#edit-date").text(dvd.date);
        modal.find("#edit-director").text(dvd.director);
        modal.find("#edit-studio").text(dvd.studio);
        modal.find("#edit-rating").text(dvd.rating);
        modal.find("#edit-notes").text(dvd.notes);
    });
});
