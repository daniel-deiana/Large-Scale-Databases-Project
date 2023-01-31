$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        let username_  = document.getElementById("username").value
        $.ajax({
            url : "/api/signup",
            data : {username : username_},
            dataType : 'json',
            method : "post",
            success: function(data) {
                console.log(data)
                result = JSON.parse(data)
            }
        })
    }
});