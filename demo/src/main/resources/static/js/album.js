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
                                '<img style="height:200px; width: 160px;"referrerpolicy="no-referrer" src='+result[fig].image_url+' alt="">' +
                                '<h3>'+ result[fig].name +'</h3>' +
                                '<h4>'+ result[fig].anime+'</h4>' +
                                '<a class="form-control" style=" text-align: center; margin: auto; cursor: pointer;" onclick="addToTop10(this.parentElement.childNodes[1].textContent)">Add to Top10</a>' +
                                '<a class="form-control" style=" text-align: center; margin: auto; cursor: pointer;" onclick="removeFromTop10(this.parentElement.childNodes[1].textContent)">Remove from Top10</a>'+
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
                let html = '<img style="height:200px; width: 160px;"referrerpolicy="no-referrer" src='+result.image_url+' alt="">' +
                    '<h1>'+ result.name +'</h1> ' +
                    '<h2>'+ result.anime +'</h2><br>' +
                    '<a class="btn-get-started scrollto" style=" text-align: center; margin: auto; cursor: pointer;" onclick="addToTop10(this.offsetParent.childNodes[2].textContent)">Add to Top10</a><br>' +
                    '<a class="btn-get-started scrollto" style=" text-align: center; margin: auto; cursor: pointer;" onclick="removeFromTop10(this.offsetParent.childNodes[2].textContent)">Remove from Top10</a>';
                $('#characters_info').append(html)
            }
        }
    })
}

function addToTop10(name){
    console.log(name)
    $.ajax({
        url : "/api/AddToTop10",
        data : {name: name},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            console.log(typeof(result))
            if(result === 1) alert("This character is already in your Top10!")
            if(result === -1) alert("Full Top10, you have to remove some character first")
            if (result === 0) alert("Character correctly added in your Top10")
        }
    })
}

function removeFromTop10(name){
    $.ajax({
        url : "/api/removeFromTop10",
        data : {name: name},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            console.log(typeof(result))
            if(result === 1) alert("This character is not in your Top10!")
            if (result === 0) alert("Character correctly removed from your Top10")
        }
    })
}