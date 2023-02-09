$(document).ready(function () {
        $.ajax({
                    url : "/api/GetCharacters",
                    method : "get",
                    success: function(data) {
                        result = JSON.parse(data)
                        console.log(result)
                        if(result == null){
                            let html = '<h2> You do no have characters </h2></div>'
                            $('#swiper').append(html)
                        }
                        for(fig in result){
                            let html = '<div class="swiper-slide">' +
                                '<div class="testimonial-item" style="display: flex; flex-direction: column; align-items: center">' +
                                '<img src="../img/unkown_c.png"  style="width: 50%; height: 50%" alt="">' +
                                '<h3>' + result[fig].name +'</h3>' +
                                '<h4>'+ result[fig].anime+'</h4>' +
                                '</div>' +
                                '</div>'
                            $('#swiper').append(html)
                        }
                    }
        })
});


function search_character(){
    let fig = document.getElementById("searched_char")
    if(!fig.value){
        alert("Write the name of a character first")
        return
    }

    let box = document.getElementById("characters_info");
    box.replaceChildren()

    $.ajax({
        url : "/api/FindCharacter",
        data : {name_fig: fig.value},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            console.log(typeof(result))
            if(!result) {
                alert("You do not have this character!")
            }
            else{
                let html = '<img id="pic_anime" src='+ result.url +' alt=""> ' +
                    '<h1>'+ result.name +'</h1> ' +
                    '<h2>'+ result.anime +'</h2>' + '';
                $('#characters_info').append(html)
            }
        }
    })


}