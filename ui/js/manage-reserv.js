$(document).ready(function() {
    $('.tabs').tabs();
    $('.modal').modal();
    $('.datepicker').datepicker();

    if (localStorage.getItem('user') != '') {
        user = JSON.parse(localStorage.getItem('user'));
        createUserInfo(user);
        $('#auth').hide();
        $('#user').show();
        listReservation();
    }
    checkLoggedIn();
});

$('#btnLogin').click(function() {
    var url = API_URL + '/customers/sign-in?email=' + $('#username').val() +
                '&vendorId=' + VENDOR_ID + '&callback=?';

    $.getJSON(url, function(result) {
        if (result && result.id > 0) {
            // Hide authentication
            $('#auth').hide();
            
            createUserInfo(result);

            // Show user Info and Make reservation button
            $('#user').show();
            checkLoggedIn();
            listReservation();
        } else {
            M.toast({html: 'Customer not found in the system. Please, try again.'});
        }
    });    
});

$('#btnSignup').click(function() {
    var url = API_URL + '/customers/sign-up?firstName=' + $('#firstName').val() +
                '&lastName=' + $('#lastName').val() +
                '&email=' + $('#email').val() +
                '&phone=' + $('#phone').val() +
                '&vendorID=' + VENDOR_ID + '&callback=?';

    $.getJSON(url, function(result) {
        if (result.id > 0) {
            // Hide authentication
            $('#auth').hide();
            
            createUserInfo(result);

            // Show user Info and Make reservation button
            $('#user').show();
            checkLoggedIn();
        }
    });
});

function createUserInfo(obj) {
    localStorage.setItem('user', JSON.stringify(obj));

    $('#user').append(
        '<div class="card">' +
            '<div class="card-content">' +
            '    <span class="card-title">' + obj.firstName + ' ' + obj.lastName + '</span>' +
            '    <p><i class="material-icons prefix">email</i>' + obj.email + '</p>' +
            '    <p><i class="material-icons prefix">phone</i>' + obj.phone + '</p>' +
            '</div>' +
        '</div>'
    );
}

function listReservation() {
    var user = JSON.parse(localStorage.getItem('user'));
    var url = API_URL + '/booking?customerID=' + user.id + '&callback=?';

    $.getJSON(url, function(result) {
        if (result) {
            $('#myBookings .collection-item').empty();
            
            result.forEach(booking => {
                $('#myBookings').append(
                    '<li class="collection-item">' +
                    '   <div>' +
                    '       <b>Room #' + booking.room.roomNumber +
                    '       </b>Check-in ' + new Date(booking.startDate).toLocaleDateString() +
                    '       Check-out ' + new Date(booking.endDate).toLocaleDateString() +
                    '       <a href="#!" class="secondary-content" onClick="cancelReservation(' + booking.bookingID + ')"><i class="material-icons small">cancel</i></a>' +
                    '       <a href="#!" class="secondary-content" onClick="updateReservation(' + booking.bookingID + ',' + booking.room.roomNumber + ',' + booking.customer.id + ')"><i class="material-icons small">update</i></a>' +
                    '   </div>' +
                    '</li>'
                );
            });
        }
    });
}

function cancelReservation(bookingId) {
    var url = API_URL + '/booking/cancel-reservation?bookingID=' + bookingId + '&callback=?';

    $.getJSON(url, function(result) {
        M.toast({html: result.message});
        setTimeout(window.location.reload(), 4000);
    });
}

function updateReservation(bookingId, roomNumber, customerId) {
    $('#bookingId').val(bookingId);
    $('#roomNumber').val(roomNumber);
    $('#customerId').val(customerId);
    $('.modal').modal('open');
}

$('#btnUpdate').click(function() {
    var url = API_URL + '/booking/update-reservation?bookingID=' + $('#bookingId').val() + 
    '&roomNumber=' + $('#roomNumber').val() +
    '&customerId=' + $('#cutomerId').val() +
    '&startDate=' + new Date($('#checkIn').val()).toISOString() +
    '&endDate=' + new Date($('#checkOut').val()).toISOString() +'&callback=?';

    $.getJSON(url, function(result) {
        M.toast({html: result.message});
        setTimeout(window.location.reload(), 4000);
    });

    $('#modal').modal('close'); 
});