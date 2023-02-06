$(document).ready(function () {
    document.getElementById("buy_button").onclick = function (e) {
        init_div = document.getElementById("change")
        init_div.style.display = 'None'

        $.ajax({
                            url : "/api/ShopController",
                            dataType: 'json',
                            method : "get",
                            success: function(data) {
                                result = jQuery.parseJSON(data)
                                name.textContent = "Welcome "+ result['username'] + "!"
                                div.appendChild(name)
                            }
                        })
    }
});