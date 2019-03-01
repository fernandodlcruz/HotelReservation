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
            '</div>' +
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