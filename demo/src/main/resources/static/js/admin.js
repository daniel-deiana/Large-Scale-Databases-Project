$(document).ready(function () {
    document.getElementById("addAnime").onclick = function (e) {
    title = document.getElementById("title").value
    synopsis = document.getElementById("synopsis").value
    episodes = document.getElementById("episodes").value
    image = document.getElementById("image").value
        $.ajax({
                url : "/api/AddAnime",
                method : "post",
                dataType: "json",
                data : {title: title, synopsis: synopsis, episodes: episodes, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']===1)
                        alert("This anime already exists!")
                    if(result['type']===0)
                        alert("Correctly added!")
                }
            })
        }
});

$(document).ready(function () {
    document.getElementById("addCharacter").onclick = function (e) {
    name = document.getElementById("name").value
    anime = document.getElementById("anime").value
    image = document.getElementById("image").value
        $.ajax({
                url : "/api/AddCharacter",
                method : "post",
                dataType: "json",
                data : {name: name, anime: anime, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']===1)
                        alert("This anime already exists!")
                    if(result['type']===0)
                        alert("Correctly added!")
                }
            })
        }
});

function getMostReviewedAnime_btn(){
    let title_button = document.getElementById("getMostReviewedAnime_btn")
    document.getElementById('title_query').textContent = title_button.textContent;
    document.getElementById("recalculate_review").setAttribute("onclick","getMostReviewedAnime()")
    document.getElementById("recalculate_review").value = null;
    not_view_filters();
    view_filters();
    orderby = change_button(title_button, "Reviewed Anime")
    getMostReviewedAnime()
}

function getMostReviewedAnime(){
    let year = document.getElementById("year").value;
    $.ajax({
        url : "/api/getMostReviewedAnime",
        data : {how_order: orderby, year: year},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Reviews")
        }
    })
}

function getTopReviewedAnime_btn(){
    let title_button = document.getElementById("getTopReviewedAnime")
    document.getElementById('title_query').textContent = title_button.textContent;
    document.getElementById("recalculate_review").setAttribute("onclick","getTopReviewedAnime()")
    document.getElementById("recalculate_review").value = null;
    not_view_filters();
    view_filters();
    change_button(title_button, "Best Rated Anime")
    getTopReviewedAnime()
}

function getTopReviewedAnime(){
    let year = document.getElementById("year").value;
    $.ajax({
        url : "/api/getTopReviewedAnime",
        data : {how_order: orderby, year: year},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; margin:auto; " >#Reviews: '+ result[fig].field2 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; margin:auto;" >Score: '+ (parseFloat(result[fig].field3).toFixed(2))  +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}


function getTopReviewers_btn(){
    let title_button = document.getElementById("getTopReviewers")
    document.getElementById('title_query').textContent = title_button.textContent;
    document.getElementById("recalculate_review").setAttribute("onclick","getTopReviewers()")
    document.getElementById("recalculate_review").value = null;
    not_view_filters();
    view_filters();
    change_button(title_button, "Active Users")
    getTopReviewers()
}
function getTopReviewers(){
    let year = document.getElementById("year").value;
    $.ajax({
        url : "/api/getTopReviewers",
        data : {how_order: orderby, year: year},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Reviews")
        }
    })
}

function getMostPopularUsers(){
    let title_button = document.getElementById("getMostPopularUsers")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    orderby = change_button(title_button, "Popular Users")
    $.ajax({
        url : "/api/getMostPopularUsers",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Followers")
        }
    })
}

function getCountryView(){
    let title_button = document.getElementById("getCountryView")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    orderby = change_button(title_button, "Frequent Countries")
    $.ajax({
        url : "/api/getCountryView",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Users")
        }
    })
}


function getMostLovedCharacter(){
    let title_button = document.getElementById("getMostLovedCharacter")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    orderby = change_button(title_button, "Loved Character")
    $.ajax({
        url : "/api/getMostLovedCharacter",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Added to Top10")
        }
    })
}

function getMostRareCharacter(){
    let title_button = document.getElementById("getMostRareCharacter")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    if(title_button.textContent === "Most Rare Character"){
        orderby = "ASC";
        title_button.textContent = "Least Rare Character";
    }
    else{
        orderby = "DESC";
        title_button.textContent = "Most Rare Character";
    }
    $.ajax({
        url : "/api/getMostRareCharacter",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Added to Top10")
        }
    })
}

function view_filters() {
    document.getElementById("filtri_review").style.display = "flex";
}
function getMostUnusedCharacter(){
    let title_button = document.getElementById("getMostUnusedCharacter")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    change_button(title_button,"Unused Character")
    $.ajax({
        url : "/api/getMostUnusedCharacter",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            print_result_set(result, this.textContent, "Added to Top10")
        }
    })
}


function not_view_filters(){
    document.getElementById("filtri_review").style.display = "none";
}

function change_button(title_button, what){
    if(title_button.textContent === "Most "+ what +""){
        orderby = "DESC";
        title_button.textContent = "Least "+ what +""
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Most "+ what +"";
    }
    return orderby;
}

function print_result_set(result, anime_name, name_of_count){
    document.getElementById("result_query").replaceChildren()
    for (fig in result){
        let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
            '<a class="btn-get-started scrollto" onclick="anime_page('+anime_name+')" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
            '<a class="btn-get-started scrollto" onclick="anime_page('+anime_name+')" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
            '<a class="btn-get-started scrollto" onclick="anime_page('+anime_name+')" style=" width:200px; text-align: center; " >#'+name_of_count+': '+ result[fig].field2 +'</a>' +
            '</div>'
        $('#result_query').append(html)
    }
}