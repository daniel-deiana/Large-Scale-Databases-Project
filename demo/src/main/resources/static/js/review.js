$(document).ready(succPage());

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}
function succPage(){
    $.ajax({
        url : "/api/getReviews",
        method : "get",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            if(result === "1")alert("No more reviews")
            else{
                removeAllChildNodes(document.getElementById("swiper"))
                view_review(result.content)
            }
        }
    })
}
function precPage(){
    $.ajax({
        url : "/api/getPrecReviews",
        method: "get",
        dataType : 'json',
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            if(result["type"] !== 1) succPage();
        }

    })
}


function make_review(){
    let text = document.getElementById("text_review").value
    let score = document.getElementById("score_review").value
    if(!text || !score){
        alert("Write a review first")
        return
    }
    score = parseInt(score)
    $.ajax({
        url : "/api/MakeReview",
        dataType : 'json',
        data : {text_review: text, score_review: score},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            console.log(typeof(result))
            if(result["type"] === 1) {
                alert("You have already reviewed this anime!")
            }
            else
                alert("Your review has been correctly registered!")

        }
    })


}

function view_review(reviews){
    for(rev in reviews){
        let html = '<div class="swiper-slide">' +
            '<div style="width: fit-content; height: fit-content;" class="testimonial-item"><p id="rev1">' +
            '<i class="bx bxs-quote-alt-left quote-icon-left"></i>' + reviews[rev].text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="../img/unkown_c.png" class="testimonial-img" alt=""><h3>' + reviews[rev].profile +' - '+ reviews[rev].score+'</h3></div></div>'
        $('#swiper').append(html)
    }
}