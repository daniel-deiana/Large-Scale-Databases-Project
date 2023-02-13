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
    not_view_filters();
    view_filters("review");
    if(title_button.textContent === "Most Reviewed Anime"){
        orderby = "DESC";
        title_button.textContent = "Least Reviewed Anime"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Most Reviewed Anime";
    }
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
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; " >#Reviews: '+ result[fig].field2 +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}



function getTopReviewedAnime(){
    let title_button = document.getElementById("getTopReviewedAnime")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    view_filters("review");
    if(title_button.textContent === "Best Reviewed Anime"){
        orderby = "DESC";
        title_button.textContent = "Worst Reviewed Anime"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Best Reviewed Anime";
    }
    $.ajax({
        url : "/api/getTopReviewedAnime",

        data : {how_order: orderby},
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
function getTopReviewers(){
    let title_button = document.getElementById("getTopReviewers")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    view_filters("user");
    if(title_button.textContent === "Top Reviewers"){
        orderby = "DESC";
        title_button.textContent = "Worst Reviewers"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Top Reviewers";
    }
    $.ajax({
        url : "/api/getTopReviewers",

        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; " >#Reviews: '+ result[fig].field2 +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}

function getMostPopularUsers(){
    let title_button = document.getElementById("getMostPopularUsers")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    if(title_button.textContent === "Most Popular Users"){
        orderby = "DESC";
        title_button.textContent = "Least Popular Users"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Most Popular Users";
    }
    $.ajax({
        url : "/api/getMostPopularUsers",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; " >#Followers: '+ result[fig].field2 +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}

function getMostLovedCharacter(){
    let title_button = document.getElementById("getMostLovedCharacter")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    if(title_button.textContent === "Most Loved Character"){
        orderby = "DESC";
        title_button.textContent = "Least Loved Character"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Most Loved Character";
    }
    $.ajax({
        url : "/api/getMostLovedCharacter",
        data : {how_order: orderby},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; " >Added to Top10: '+ result[fig].field2 +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}

function getMostRareCharacter(){
    let title_button = document.getElementById("getMostLovedCharacter")
    document.getElementById('title_query').textContent = title_button.textContent;
    not_view_filters();
    if(title_button.textContent === "Most Rare Character"){
        orderby = "ASC";
        title_button.textContent = "Least Rare Character"
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
            console.log(result)
            document.getElementById("result_query").replaceChildren()
            for (fig in result){
                let html = '<div style="display: flex;  justify-content: center; font-size: 20px">' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" cursor: pointer; width:fit-content; text-align: center;" >'+ (parseInt(fig)+1) +') </a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:500px;" >'+ result[fig].field1 +'</a>' +
                    '<a class="btn-get-started scrollto" onclick="anime_page(this.textContent)" style=" width:200px; text-align: center; " >Added to Top10: '+ result[fig].field2 +'</a>' +
                    '</div>'
                $('#result_query').append(html)
            }
        }
    })
}

function view_filters(ofWhat){
    let name = "filtri_" + ofWhat;
    document.getElementById(name).style.display = "flex";
}

function not_view_filters(){
    document.getElementById("filtri_user").style.display = "none";
    document.getElementById("filtri_review").style.display = "none";
}