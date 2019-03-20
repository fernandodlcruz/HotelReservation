$(document).ready(function() {
    $('.tabs').tabs();

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
        console.log(JSON.stringify(result));
        if (result) {
            $('#myBookings .collection-item').empty();
            
            result.forEach(booking => {
                $('#myBookings').append(
                    '<li class="collection-item">' +
                    '   <div>' +
                    '       <b>Room #' + booking.room.roomNumber +
                    '       </b>Check-in ' + new Date(booking.startDate).toLocaleDateString() +
                    '       Check-out ' + new Date(booking.endDate).toLocaleDateString() +
                    '       <a href="#!" class="secondary-content"><i class="material-icons small">cancel</i></a>' +
                    '       <a href="#!" class="secondary-content"><i class="material-icons small">update</i></a>' +
                    '   </div>' +
                    '</li>'
                );
            });
        }
    });
}