$(document).ready(function() {
    $('.tabs').tabs();
    loadRoom(qs('id'));
    checkLoggedIn();
});

function loadRoom(id) {
    var url = API_URL + '/listrooms/get-room?roomId=' + id + '&callback=?';

    $.getJSON(url, function(result) {
        createCard(result);
    });
}

function createCard(obj) {
    var rnd = Math.floor(Math.random() * 6);

    $('#roomCard').append(
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
            '    <br><i class="small material-icons">group</i> ' + obj.roomCapacity +
            '    <br>Cehck-in ' + qs('start') +
            '    <br>Cehck-out ' + qs('end') +
            '</div>' +
            '</div>' +
        '</div>'
    );
}

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

function ConfirmReservation() {
    user = JSON.parse(localStorage.getItem('user'));

    var url = API_URL + '/booking/make-reservation?roomNumber=' + qs('id') +
                '&customerId=' + user.id +
                '&startDate=' + new Date(qs('start')).toISOString() +
                '&endDate=' + new Date(qs('end')).toISOString() + '&callback=?';

    $.getJSON(url, function(result) {
        M.toast({html: result.message});
        setTimeOut(window.location='index.html', 5000);
    });
}

function createUserInfo(obj) {
    localStorage.setItem('user', JSON.stringify(obj));

    $('#user').append(
        '<div class="card">' +
            '<div class="card-content">' +
            '    <span class="card-title">' + obj.firstName + ' ' + obj.lastName + '</span>' +
            '    <p><i class="material-icons prefix">email</i>' + obj.email + '</p>' +
            '    <p><i class="material-icons prefix">phone</i>' + obj.phone + '</p>' +
            '</div>' +
            '<div class="card-action">' +
            '    <a class="waves-effect waves-light btn" id="btnConfirmReservation" onClick="ConfirmReservation();">Confirm Reservation</a>' +
            '</div>' +
        '</div>'
    );
}

// Parse URL to get parameters
// reference: http://jsfiddle.net/gilly3/sgxcL/
function qs(key) {
    key = key.replace(/[*+?^$.\[\]{}()|\\\/]/g, "\\$&"); // escape RegEx control chars
    var match = location.search.match(new RegExp("[?&]" + key + "=([^&]+)(&|$)"));
    return match && decodeURIComponent(match[1].replace(/\+/g, " "));
}