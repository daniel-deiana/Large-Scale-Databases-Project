$(document).ready(function () {
    document.getElementById("buy_button").onclick = function (e) {
        init_div = document.getElementById("change")
        init_div.style.display = 'None'
        console.log('ao')
        $.ajax({
                            url : "/api/LoadPack",
                            method : "get",
                            success: function(data) {
                                console.log('dati ricevuti')
                                result = jQuery.parseJSON(data)
                                console.log(result)
                                for(fig in result){
                                console.log(result[fig].name)
                                }

                            }
                        })
    }
});