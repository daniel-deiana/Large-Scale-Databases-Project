$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        username_ = document.getElementById("username").value
        gender_ = document.getElementById("gender").value
        date_ = document.getElementById("date").value
        password_ = document.getElementById("floatingPassword").value
        country_ = document.getElementById("country").value
        if(!username_ || !gender_ || !date_ || !password_ || !country_){
            alert("Fill the fields")
            return
        }
        $.ajax({
                    url : "/api/signup",
                    dataType : 'json',
                    data : {username: username_, gender: gender_, date: date_, password : password_, country : country_ },
                    method : "post",
                    success: function(data) {
                        jsonObject = JSON.parse(data)
                        console.log(jsonObject)
                        console.log(typeof(jsonObject))
                        window.location.href= "http://localhost:8080/home"
                    }
                })
    }
});