$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        username_ = document.getElementById("username").value
        gender_ = document.getElementById("gender").value
        date_ = document.getElementById("date").value
        console.log("start")
        let username_  = document.getElementById("username").value
        let date_     = document.getElementById("date").value
        let gender_    = document.getElementById("gender").value
        let password_  = document.getElementById("floatingPassword").value
        $.ajax({
                    url : "/api/signup",
                    data : {username: username_, date: date_, gender:gender_, password: password_},
                    dataType : 'json',
                    data : {username: username_, gender: gender_, date: date_},
                    method : "post",
                    success: function(data) {
                        jsonObject = JSON.parse(data)
                        console.log(jsonObject)
                        console.log(typeof(jsonObject))
                    }
                })
    }
});