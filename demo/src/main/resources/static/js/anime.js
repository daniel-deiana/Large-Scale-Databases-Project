$(document).ready(function () {
        let obj = document.getElementById("title_anime")
        let desc = document.getElementById("desc_anime")
        let img = document.getElementById("pic_anime")

        $.ajax({
                    url : "/api/currentAnime",
                    method : "get",
                    success: function(data) {
                        result = JSON.parse(data)
                        console.log(result)
                        obj.textContent = result['title']
                        desc.textContent = result['synopsis']
                        img.setAttribute('src', result['img_url'])
                        let characters = result['figures']

                        for (fig in characters){
                            let html = '' +
                                '<div">\n' +
                                '            <div>\n' +
                                '              <img style="height: 200px; width: 160px;" referrerpolicy="no-referrer" src='+ characters[fig].image_url +' class="img-fluid" alt="">\n' +
                                '              <div>\n' +
                                '                <h4>'+ characters[fig].name + '</h4>\n' +
                                '              </div>\n' +
                                '              <div>\n' +
                                '                <a title="More Details"><i class="bx bx-link"></i></a>\n' +
                                '              </div>\n' +
                                '            </div>\n' +
                                '          </div>'
                            $('#figures').append(html)

                        }
                        let reviews = result['reviews']
                        for(rev in reviews){
                            let html = '<div class="swiper-slide">' +
                                '<div style="overflow-y: scroll; height: 50px;" class="testimonial-item"><p id="rev1">' +
                                '<i class="bx bxs-quote-alt-left quote-icon-left"></i>' + reviews[rev].text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="../img/unkown_c.png" class="testimonial-img" alt=""><h3>' + reviews[rev].profile +' - '+ reviews[rev].score+'</h3></div></div>'
                            $('#swiper').append(html)
                        }
                    }
        })

        let your = document.getElementById("your_rev")
        let rev = document.getElementById("rev0")
        $.ajax({
                url : "/api/yourReview",
                method : "get",
                success: function(data) {
                    result = JSON.parse(data)
                    console.log(result)
                    if(result != null){
                        let html = '<div class="swiper-slide">' +
                                    '<div style="width: fit-content; height: fit-content;" class="testimonial-item"><p id="rev1">' +
                                    '<i class="bx bxs-quote-alt-left quote-icon-left"></i>' + result.text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="../img/unkown_c.png" class="testimonial-img" alt=""><h3>' + result.profile +' - '+ result.score+'</h3></div></div>'
                        $('#your_rev').append(html)
                    } else
                    {
                        let html = '<a class="btn-get-started scrollto" style="text-align: center; margin: auto;" type="submit" onclick="make_review()">Make a review &#9997</a>'+
                                    '<textarea class="form-control" id = "text_review" style="width: 500px; height: 150px;" type="text"></textarea>'+
                                    '<select id = "score_review" class="form-control" name="option" style=" width:150px;  text-align: center; color: grey;">'+
                                      '<option style="text-align: center; color: grey;" value="0">0</option>'+
                                      '<option style="text-align: center;color: grey;" value="1">1</option>'+
                                      '<option style="text-align: center;color: grey;" value="2">2</option>'+
                                      '<option style="text-align: center;color: grey;" value="3">3</option>'+
                                      '<option style="text-align: center;color: grey;" value="4">4</option>'+
                                      '<option style="text-align: center;color: grey;" value="5">5</option>'+
                                      '<option style="text-align: center;color: grey;" value="6">6</option>'+
                                      '<option style="text-align: center;color: grey;" value="7">7</option>'+
                                      '<option style="text-align: center;color: grey;" value="8">8</option>'+
                                      '<option style="text-align: center;color: grey;" value="9">9</option>'+
                                      '<option style="text-align: center;color: grey;" value="10">10</option>'+
                                    '/select>'
                        $('#your_rev').append(html)
                    }
                }})
});


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