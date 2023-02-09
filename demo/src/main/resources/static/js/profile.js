$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    let username = document.getElementById('username')
    let country = document.getElementById('country')
    let gender = document.getElementById('gender')
    let birthday = document.getElementById('birthday')
    let followers = document.getElementById('followers')
    let followed = document.getElementById('followed')
    let cardnum = document.getElementById('cardnum')

    let div = document.getElementById["swiper"]

    $.ajax({
        url: "/api/LoadMe",
        method: "get",
        success: function (data) {
            data = JSON.parse(data)
            console.log(data)
            username.textContent = data['username']
            gender.textContent = "Gender: " + data['gender']
            country.textContent = "Country: " + data['country']
            birthday.textContent = "Birthday: " + data['birthday']
            followers.dataset.purecounterEnd = data['numFollowers']
            followed.dataset.purecounterEnd = data['numFollowed']
            cardnum.dataset.purecounterEnd = data['CardOwned']
            reviews = data['mostRecentReviews']
            console.log(reviews)
            for(rev in reviews){
                let html = '<div class="swiper-slide"><div style="overflow-y: scroll; height: 50px;"class="testimonial-item"><p id="rev1"><i class="bx bxs-quote-alt-left quote-icon-left"></i>' + reviews[rev].text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="../img/unkown_c.png" class="testimonial-img" alt=""><h3>' + reviews[rev].anime_uid +' - '+ reviews[rev].score+'</h3><h4>'+data['username']+'</h4></div></div>'
                $('#swiper').append(html)
            }
        }
    })
});
