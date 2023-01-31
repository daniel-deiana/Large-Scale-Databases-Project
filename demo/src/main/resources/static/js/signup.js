$(document).ready(function () {
    document.getElementById('signup_button').onclick = function (e) {
        console.log("start")
        $.ajax({
                    url : "/api/signup",
                    dataType : 'json',
                    method : "get",
                    success: function(data) {
                        console.log(data)
                    }
                })
    }
});