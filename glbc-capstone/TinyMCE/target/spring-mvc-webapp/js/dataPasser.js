/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
	$("#submitter").click(function(event) {
		event.preventDefault();
		
		$.ajax({
			
			type: "POST",
			url: "new_page",
			
			data: JSON.stringify({
				textBody: tinyMCE.get("tinymce-test").getContent()
			}),
			
			contentType: "application/json; charset=utf-8",
			
			headers: {
				"Accept": "application/json",
				"Content-Type": "application/json"
			},
			
			dataType: "json"
		}).success(function(data, status) {
			var returnedData = $("#returned-data");
			
			$("#returned-data").text(data.textBody);
			$("#returned-data").html(data.textBody);
		});
	});
});