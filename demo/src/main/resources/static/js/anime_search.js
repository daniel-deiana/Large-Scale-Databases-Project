$(document).ready(function () {
    document.getElementById('search_button').onclick = function (e) {
        let title_ = document.getElementById("title").value
        if(!title_){
                    alert("Fill the fields")
                    return
        }
        $.ajax({
                    url : "/api/anime_search",
                    data : {title: title_},
                    dataType : 'json',
                    method : "post",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        console.log(result)
                        if(result["type"] == 1) {
                            alert("The anime does not exist")
                            $("#title").val("")
                        }
                        else
                            window.location.href= "http://localhost:8080/anime"
                    }
        })
    }
});