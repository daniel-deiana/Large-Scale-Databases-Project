$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    console.log('ao')
    let username = document.getElementById('username')
    let country = document.getElementById('country')
    let gender = document.getElementById('gender')
    let birthday = document.getElementById('birthday')

    $.ajax({
        url: "/api/LoadMe",
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
            console.log(data)
            username.textContent = data['username']
            gender.textContent = "Gender: " + data['gender']
            country.textContent = "Country: " + data['country']
            birthday.textContent = "Birthday: " + data['birthday']
        }
    })
});