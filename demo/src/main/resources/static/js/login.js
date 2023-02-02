$(document).ready(function () {
    document.getElementById('login_button').onclick = function (e) {
        console.log("start")
        $.ajax({
                    url : "/api/login",
                    dataType : 'json',
                    method : "get",
                    success: function(data) {
                        console.log(data)
                    }
                })
    }
});