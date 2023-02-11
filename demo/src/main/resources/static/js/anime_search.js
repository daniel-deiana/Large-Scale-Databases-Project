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

$(document).ready(function () {
    $.ajax({
        url : "/api/GetSuggestedAnime",
        method : "get",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            if(result == null){
                let html = '<h2> You have seen all the anime seen by your friend!!! </h2></div>'
                $('#swiper').append(html)
            }
            for(r in result){
                let html = '<div class="swiper-slide">' +
                    '<div class="testimonial-item" style="display: flex; flex-direction: column; align-items: center">' +
                    '<h3 onclick="anime_page(this.textContent)" style=" cursor: pointer; text-align: center; margin: auto;">'+ result[r] +'</h3>\n' +
                    '</div>' +
                    '</div>'
                $('#swiper').append(html)
            }
        }
    })
});

function anime_page(name_anime){
    $.ajax({
        url : "/api/anime_search",
        data : {title: name_anime},
        dataType : 'json',
        method : "post",
        success: function() {
            window.location.href= "http://localhost:8080/anime"
        }
    })
}

function appreciated(e){
    let orderby = "DESC";
    if (e === "ondblclick") orderby = "ASC";
    $.ajax({
        url : "/api/GetAppreciatedAnime",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            let html = '<h1 style="font-size: 15px; text-align: center">Most Appreciated Anime ' + orderby + '</h1> '
            $('#result_query').append(html)
            for (fig in result){
                let html = '<div style="display: flex; gap: 5px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px; text-align: center; margin: auto;" >'+ result[fig].field1 +' </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; margin: auto;" >Score: '+ result[fig].field3 +' </a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })


}