$(document).ready(function () {
    console.log('ao')
        $.ajax({
                    url : "/api/currentUser",
                    method : "get",
                    success: function(data) {
                        data = JSON.parse(data)
                        console.log(data)
                        console.log(data["username"])
                        name = document.createElement('h1')
                        name.textContent = data['username']
                    }
                })
});