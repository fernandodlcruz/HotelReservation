const API_URL = "http://api.openweathermap.org/data/2.5/weather?";
const API_FORMAT = "";
const API_KEY = "&appid=14882ec94c410de6b463d915503d4e16";

$(document).ready(function(){
    $('.datepicker').datepicker();
});

$('#btnSearch').click(function(e) {
    var checkIn = $('#checkIn');
    var checkOut = $('#checkOut');
    var guests = $('#numberGuests');

    // Validate all input fields are filled
    if (checkIn.val() == '' || checkOut.val() == '' || guests.val() == '') {
        M.toast({html: 'Please, inform all search fields'});
        return;
    }

    // Call the API
    $.ajax({
        url: API_URL,
        type: "POST",
        dataType: "jsonp",
        data: {
            "roomNumber": 0, 
            "checkInDate": new Date(checkIn), 
            "checkOutDate": new Date(checkOut), 
            "roomCapacity": guests
        },
        crossDomain: true,
        success: function(data) {
            console.log(JSON.stringify(data));
            // TODO: Loop through the returned data
            // TODO: Create function to create card
        },
        error: function(xhr, ajaxOptions, thrownError) {
            console.log(xhr.status);
            console.log(thrownError);
        }
    });    
});