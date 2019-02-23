$(document).ready(function() {
    $('.tabs').tabs();
    //loadRoom(qs('id'));
});

function loadRoom(id) {
    var url = API_URL + '/listrooms/' + id;

    $.getJSON(url, function(result) {
        createCard(room);
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
            '    Capacity:' + obj.capacity +
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