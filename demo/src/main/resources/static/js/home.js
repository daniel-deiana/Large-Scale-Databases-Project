$(document).ready(function () {
        const div = document.getElementById("NameUser")
        const name = document.createElement('h1')
        $.ajax({
                    url : "/api/currentUser",
                    dataType: 'json',
                    method : "get",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        name.textContent = "Welcome "+ result['username'] + "!"
                        div.appendChild(name)
                    }
                })
});