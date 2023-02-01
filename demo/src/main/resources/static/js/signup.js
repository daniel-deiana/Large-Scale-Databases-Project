$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        console.log("start")
        let username_  = document.getElementById("username").value
        let date_     = document.getElementById("date").value
        let gender_    = document.getElementById("gender").value
        let password_  = document.getElementById("floatingPassword").value
        $.ajax({
                    url : "/api/signup",
                    data : {username: username_, date: date_, gender:gender_, password: password_},
                    dataType : 'json',
                    method : "get",
                    success: function(data) {
                        console.log(data)
                    }
                })
    }
});