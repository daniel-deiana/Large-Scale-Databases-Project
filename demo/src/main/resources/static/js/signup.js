$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        let username_  = document.getElementById("username").value
        let date_  = document.getElementById("date").value
        let gender_  = document.getElementById("gender").value
        let password_  = document.getElementById("password").value
        console.log("ciao")
        $.ajax({
            url : "/api/signup",
            data : {username : username_},
            dataType : 'json',
            method : "get",
            success: function(data) {
                console.log("ciao")
                console.log(data)
                result = JSON.parse(data)
                console.log(result)
            },
            error: function (){
                console.log("Error!")
            }
        })
    }
});