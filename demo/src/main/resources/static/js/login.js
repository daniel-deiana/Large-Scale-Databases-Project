$(document).ready(function () {
    document.getElementById('login_button').onclick = function (e) {
        username_ = document.getElementById("username").value
        password_ = document.getElementById("floatingPassword").value
        if(!username_ || !password_ ){
                    alert("Fill the fields")
                    return
                }
        $.ajax({
                    url : "/api/login",
                    data : {username: username_, password: password_},
                    dataType : 'json',
                    method : "post",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        console.log(result)
                        if(result["type"] == 1) {
                            alert("Wrong Username")
                            $("#username").val("")
                            $("#password").val("")
                        } else if(result["type"] == 2) {
                            alert("Wrong Password")
                            $("#username").val("")
                            $("#password").val("")
                        } else if(result['admin']==false)
                            window.location.href= "http://localhost:8080/home"
                        else{
                            window.location.href= "http://localhost:8080/admin"
                        }
                    }
                })
    }
});