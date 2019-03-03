$(document).ready(function() {
    $('.tabs').tabs();
    loadRoom(qs('id'));
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
        console.log(JSON.stringify(result));
        if (result && result.id > 0) {
            // Hide authentication
            $('#auth').hide();
            
            createUserInfo(result);

            // Show user Info and Make reservation button
            $('#user').show();
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
        }
    });
});

function createUserInfo(obj) {
    var rnd = Math.floor(Math.random() * 6);

    $('#user').append(
        '<div class="card">' +
            '<div class="card-content">' +
            '    <span class="card-title">' + obj.firstName + ' ' + obj.lastName + '</span>' +
            '    <p>' + obj.email + '</p>' +
            '    <p>' + obj.phone + '</p>' +
            '</div>' +
            '<div class="card-action">' +
            '    <a class="waves-effect waves-light btn" id="' + obj.id + '">Confirm Reservation</a>' +
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