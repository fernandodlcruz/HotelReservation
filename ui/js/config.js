const API_URL = "http://localhost:8080/HotelReservation";
const API_FORMAT = "";
const API_KEY = "&appid=14882ec94c410de6b463d915503d4e16";
const VENDOR_ID = 1;

function checkLoggedIn() {
    if (localStorage.getItem('user') != '') {
        user = JSON.parse(localStorage.getItem('user'));        
        $('#topMenu').append('<li id="logout"><a href="#" onCLick="logout();">Logout ' + user.firstName + '</a></li>');
    } else {
        $('#logout').remove();
    }
    
}

function logout() {
    localStorage.setItem('user', '');
    window.location.reload();
}