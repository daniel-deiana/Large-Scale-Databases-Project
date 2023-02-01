$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        username_ = document.getElementById("username").value
        gender_ = document.getElementById("gender").value
        date_ = document.getElementById("date").value
        password_ = document.getElementById("floatingPassword").value
        $.ajax({
                    url : "/api/signup",
                    dataType : 'json',
                    data : {username: username_, gender: gender_, date: date_, password : password_ },
                    method : "post",
                    success: function(data) {
                        jsonObject = JSON.parse(data)
                        console.log(jsonObject)
                        console.log(typeof(jsonObject))
                    }
                })
    }
});