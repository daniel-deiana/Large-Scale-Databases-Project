$(document).ready(function () {
    document.getElementById("buy_button").onclick = function (e) {
        init_div = document.getElementById("change")
        token = document.getElementById("token")
        paste_button = document.getElementById("paste_button")


        $.ajax({
                url : "/api/LoadPack",
                method : "get",
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    if(result == null){
                        token.textContent = "Wait until tomorrow to open a pack!"
                        return;
                    }
                    init_div.style.display = 'None'
                    for(fig in result){
                        let html = '<div style= "margin:10px; display: flex; flex-direction: column; align-items: center; justify-content: center;"><img style="height:200px; width: 160px;"referrerpolicy="no-referrer" src='+result[fig].image_url+' alt=""><h2>' + result[fig].name +'</h2></div>'
                        $('#pack').append(html)
                    }
                    paste_button.style.display=""
                }
            })
        }

    $.ajax({
            url : "/api/PossibleCards",
            method : "get",
            success: function(data) {
                result = jQuery.parseJSON(data)
                console.log(result)
                for(fig in result){
                    let html = '<div class="swiper-slide">' +
                        '<div class="testimonial-item" style="display: flex; flex-direction: column; align-items: center">' +
                        '<h3 style=" cursor: pointer; text-align: center; margin: auto;">'+ result[fig].name +'</h3>\n' +
                        '<img style="height:200px; width: 160px;"referrerpolicy="no-referrer" src='+result[fig].image_url+' alt="">' +
                        '</div>' +
                        '</div>'
                    $('#swiper').append(html)
                }
            }
        })
});
