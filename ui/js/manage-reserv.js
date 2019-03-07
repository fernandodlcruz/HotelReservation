$(document).ready(function() {
    $('.tabs').tabs();

    if (localStorage.getItem('user') != '') {
        user = JSON.parse(localStorage.getItem('user'));
        createUserInfo(user);
        $('#auth').hide();
        $('#user').show();
    }    
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