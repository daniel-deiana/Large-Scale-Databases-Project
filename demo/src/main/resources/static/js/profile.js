$(document).ready(function() {
    let user = window.location.href.slice(39,window.location.href.length)
    let username = document.getElementById('username')
    let country = document.getElementById('country')
    let gender = document.getElementById('gender')
    let birthday = document.getElementById('birthday')
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
            reviews = data['mostRecentReviews']
            for(rev in reviews){
                let html = '<div class="swiper-slide"><div class="testimonial-item"><p id="rev1"><i class="bx bxs-quote-alt-left quote-icon-left"></i>' + reviews[rev].text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="img/unknown_c.jpg" class="testimonial-img" alt=""><h3>' + reviews[rev].anime +' - '+ reviews[rev].score+'</h3><h4>'+data['username']+'</h4></div></div>'
                $('#swiper').append(html)
            }
        }
    })
});
// 2c1409704cb5e351d73dc93a8657da59db4da8fc58f16d49126731f21d82980f
/*<div class="swiper-slide">
                <div class="testimonial-item">
                  <p id="rev1">
                    <i class="bx bxs-quote-alt-left quote-icon-left"></i>
                    Proin iaculis purus consequat sem cure digni ssim donec porttitora entum suscipit rhoncus. Accusantium quam, ultricies eget id, aliquam eget nibh et. Maecen aliquam, risus at semper.
                    <i class="bx bxs-quote-alt-right quote-icon-right"></i>
                  </p>
                  <img src="img/testimonials/testimonials-1.jpg" class="testimonial-img" alt="">
                  <h3>Saul Goodman</h3>
                  <h4>Ceo &amp; Founder</h4>
                </div>
              </div><!-- End testimonial item -->*/