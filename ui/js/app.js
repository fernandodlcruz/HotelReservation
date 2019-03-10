$(document).ready(function() {
    $('.datepicker').datepicker();

    checkLoggedIn();
});

$('#btnSearch').click(function() {
    var checkIn = $('#checkIn');
    var checkOut = $('#checkOut');
    var guests = $('#numberGuests');

    // Validate all input fields are filled
    if (checkIn.val() == '' || checkOut.val() == '' || guests.val() == '') {
        M.toast({html: 'Please, inform all search fields'});
        return;
    }

    // Call the API
    var url = API_URL + '/listrooms?roomNumber=0' + '&checkInDate=' + new Date(checkIn.val()).toISOString() + 
                '&checkOutDate=' + new Date(checkOut.val()).toISOString() + '&roomCapacity=' + guests.val() + '&callback=?';

    $.getJSON(url, function(result) {
        result.forEach(room => {
            $('#listRooms').empty();
            createCard(room);
        });
    });

    // $.ajax({
    //     url: API_URL + '/listrooms?roomNumber=0' + 
    //     '&checkInDate=' + new Date(checkIn.val()).toISOString() + 
    //     '&checkOutDate=' + new Date(checkOut.val()).toISOString() + 
    //     '&roomCapacity=' + guests.val(),
    //     type: "GET",
    //     dataType: "json",
    //     //jsonp: "callback",
    //     //contentType: "application/json; charset=utf-8",
    //     /*data: {
    //         "roomNumber": 0, 
    //         "checkInDate": new Date(checkIn), 
    //         "checkOutDate": new Date(checkOut), 
    //         "roomCapacity": guests
    //     },
    //     crossDomain: true,*/
    //     success: function(data) {
    //         console.log(JSON.stringify(data));
    //         // TODO: Loop through the returned data
    //         // TODO: Create function to create card
    //     },
    //     error: function(xhr, ajaxOptions, thrownError) {
    //         console.log(xhr.status);
    //         console.log(thrownError);
    //     }
    // });
});

function createCard(obj) {
    var rnd = Math.floor(Math.random() * 6);

    $('#listRooms').append(
        '<div class="card horizontal">' +
            '<div class="card-image">' +
            '<img src="images/room-' + rnd + '.jpg">' +
            '</div>' +
            '<div class="card-stacked">' +
            '<div class="card-content">' +
            '    <p>' + obj.description + '</p>' +
            '</div>' +
            '<div class="card-action">$' +
            obj.price + '/night ' +
            '    <a href="#" onClick="window.location=\'room.html?id=' + obj.roomNumber + 
            '&start=' + $('#checkIn').val() + '&end=' + $('#checkOut').val() + '\'">Make Reservation</a>' +
            '</div>' +
            '</div>' +
        '</div>'
    );
}