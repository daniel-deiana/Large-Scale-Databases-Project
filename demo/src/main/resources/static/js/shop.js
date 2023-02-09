$(document).ready(function () {
    document.getElementById("buy_button").onclick = function (e) {
        init_div = document.getElementById("change")
        paste_button = document.getElementById("paste_button")
        init_div.style.display = 'None'
        $.ajax({
                            url : "/api/LoadPack",
                            method : "get",
                            success: function(data) {
                                result = jQuery.parseJSON(data)
                                for(fig in result){
                                    let html = '<div style= "display: flex; flex-direction: column; align-items: center; justify-content: center;"><img src="img/unkown_c.png" class="img-fluid animated" alt="" style="width = 80%; height= 80%;"><h2>' + result[fig].name +'</h2></div>'
                                    $('#pack').append(html)
                                }
                                paste_button.style.display=""
                            }
                        })
    }
});
