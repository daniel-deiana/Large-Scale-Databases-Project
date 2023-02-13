function getMostReviewedAnime(title_button){
    document.getElementById('title_query').textContent = title_button.textContent;
    if(title_button.textContent === "Most Reviewed Anime"){
        orderby = "DESC";
        title_button.textContent = "Least Reviewed Anime"
    }
    else{
        orderby = "ASC";
        title_button.textContent = "Most Reviewed Anime";
    }
    $.ajax({
        url : "/api/getMostReviewedAnime",

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
function getTopReviewedAnime(title_button){
    document.getElementById('title_query').textContent = title_button.textContent;
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
function getTopReviewers(title_button){
    document.getElementById('title_query').textContent = title_button.textContent;
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

function getMostPopularUsers(title_button){
    document.getElementById('title_query').textContent = title_button.textContent;
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