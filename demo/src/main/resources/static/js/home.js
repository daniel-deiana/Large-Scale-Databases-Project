$(document).ready(function () {
    console.log('ao')
        $.ajax({
                    url : "/api/currentUser",
                    method : "get",
                    success: function(data) {
                        data = JSON.parse(data)
                        //let h1 = document.getElementById("NameUser").value
                        //console.log(h1)
                        console.log(data)
                    }
                })
});