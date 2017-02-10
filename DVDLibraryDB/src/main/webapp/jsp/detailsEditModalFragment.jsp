<div class="modal fade" id="detailsModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>                                        
                </button>
                <h4 class="modal-title" id="detailsModalLabel">DVD Details</h4>
            </div>
            <div class="modal-body">
                 <h3 id="dvd-title"></h3>
                <table class="table table-bordered">                   
                    <tr>
                        <th>Release Date:</th>
                        <td id="dvd-date"></td>
                    </tr>
                    <tr>
                        <th>Director:</th>
                        <td id="dvd-director"></td>
                    </tr>
                    <tr>
                        <th>Studio:</th>
                        <td id="dvd-studio"></td>
                    </tr>
                    <tr>
                        <th>MPAA Rating:</th>
                        <td id="dvd-rating"></td>
                    </tr>
                    <tr>
                        <th>Notes:</th>
                        <td id="dvd-notes"></td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="detailsModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title" id="detailsModalLabel">Edit DVD</h4>
            </div>
            <div class="modal-body">
                <h3 id="dvd-id"></h3>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="edit-title" class="col-sm-4 control-label">Title:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-title" placeholder="Title:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-date" class="col-sm-4 control-label">Release Date:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-date" placeholder="Date:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-director" class="col-sm-4 control-label">Director:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-director" placeholder="Director:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-studio" class="col-sm-4 control-label">Studio:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-studio" placeholder="studio:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-rating" class="col-sm-4 control-label">MPAA Rating:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-rating" placeholder="Rating:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-notes" class="col-sm-4 control-label">Notes:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="edit-notes" placeholder="Notes:"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-4 col-sm-8">
                            <button type="submit" id="edit-button" class="btn btn-default" data-dismiss="modal">Edit DVD</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                            <input type="hidden" id="edit-id">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>