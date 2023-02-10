$(document).ready(function () {
        $.ajax({
                    url : "/api/suggestedUser",
                    method : "get",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        console.log(result)
                         for(user in result){
                            let html = '<div class="swiper-slide">' +
                                '<div class="testimonial-item" style="display: flex; flex-direction: column; align-items: center">' +
                                '<h1 onclick="user_profile(this.textContent)" style=" cursor: pointer; text-align: center; margin: auto;">'+ result[user].username +'</h1>\n' +
                                '<img src="../img/unkown_c.png" class="testimonial-img" alt="">' +
                                '</div>' +
                                '</div>'
                            $('#swiper_user').append(html)
                        }
                    }
                })

})

function user_profile(username){
    let user = document.getElementById("searched_user")
    user.value = username
    search_user()
}


function search_user(){
    let user = document.getElementById("searched_user")
    let user_rev = document.getElementById("user_rev")
    let user_top = document.getElementById("user_top")
    let top10 = document.getElementById("top10")
    let reviews = document.getElementById("reviews")



    if(!user.value){
        alert("Write the name of a user first")
        return
    }
    let box = document.getElementById("user_info")
    box.replaceChildren()
    let box2 = document.getElementById("swiper_review")
    box2.replaceChildren()
    let box3 = document.getElementById("first5")
    box3.replaceChildren()
    let box4 = document.getElementById("second5")
    box4.replaceChildren()
    reviews.style.display=""

    $.ajax({
        url : "/api/FindUser",
        data : {user: user.value},
        method : "post",
        success: function(data) {
            result = JSON.parse(data)
            console.log(result)
            console.log(result.isfollowed)
            if(!result){
                alert("This user does not exist!")
                return
            }
            else{
                let html = '<img src="../img/unkown_c.png" alt=""> ' +
                                '<div><h1 id="name_user" style="font-size: 30px;">'+ result.username +'</h1> ' + '';
                if(result.isfollowed){
                    top10.style.display = "flex"
                    user_top.textContent= user.value + " TOP 10"
                    html += '<a id="fol" class="btn-get-started scrollto" style=" cursor:pointer; text-align: center; margin: auto;" onclick="Unfollow()">Unfollow</a></div>'
                }
                else{
                    top10.style.display = "none"
                    user_top.textContent= "You can't see the Top10 if you don't follow the user"
                    html += '<a id="fol" class="btn-get-started scrollto" style=" cursor:pointer; text-align: center; margin: auto;" onclick="Follow()">Follow</a></div>'
                }
                html +=         '<h2>Gender - '+ result.gender +'</h2>' +
                                '<h2>Country - '+ result.country +'</h2>' +
                                '<h2>Birthday - '+ result.birthday +'</h2>' +
                                '<h2>Gender - '+ result.gender +'</h2>'+'';

                $('#user_info').append(html)
                reviews = result['mostRecentReviews']
                console.log(reviews)

                for(rev in reviews){
                                let html = '<div class="swiper-slide"><div style="overflow-y: scroll; height: 50px;"class="testimonial-item"><p id="rev1"><i class="bx bxs-quote-alt-left quote-icon-left"></i>' + reviews[rev].text + '<i class="bx bxs-quote-alt-right quote-icon-right"></i></p><img src="../img/unkown_c.png" class="testimonial-img" alt=""><h3>' + reviews[rev].anime_uid +' - '+ reviews[rev].score+'</h3><h4>'+result.username+'</h4></div></div>'
                                $('#swiper_review').append(html)
                            }
                user_rev.textContent ="Here are " + result.username + " last reviews!"

            }
            }
        })

        $.ajax({
                url : "/api/Top10",
                method : "get",
                data : {user: user.value},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result == null)
                        return;
                        for(fig in result){
                            if(fig<5){
                                let html = '<div style= "width: 12%; height: 12%;display: flex; flex-direction: column; align-items: center; justify-content: center;"><img src="img/unkown_c.png" class="img-fluid animated" alt="" style="width = 80%; height= 80%;"><h2 style="font-size: 15px; color: lavender;">' + result[fig].name +'</h2><h2 style="font-size: 15px;">'+ result[fig].anime +'</h2></div>'
                                $("#first5").append(html)
                            }
                            else{
                                let html = '<div style= "width: 12%; height: 12%;display: flex; flex-direction: column; align-items: center; justify-content: center;"><img src="img/unkown_c.png" class="img-fluid animated" alt="" style="width = 80%; height= 80%;"><h2 style="font-size: 15px; color: lavender;">' + result[fig].name +'</h2><h2 style="font-size: 15px;">'+ result[fig].anime +'</h2></div>'
                                $("#second5").append(html)
                            }
                        }

                }
            })
}

function Follow(){
    let user = document.getElementById("name_user")
    let fol = document.getElementById("fol")

        $.ajax({
                    url : "/api/followUser",
                    data : {user: user.textContent},
                    dataType : 'json',
                    method : "post",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        console.log(result)
                        top10.style.display = "flex"
                        user_top.textContent= user.textContent + " TOP 10"
                        fol.textContent = "Unfollow"
                        fol.onclick = Unfollow
                        alert("Now you follow " + user.textContent + " !")
                    }
        })
    }
function Unfollow(){
    let user = document.getElementById("name_user")
    let fol = document.getElementById("fol")

        $.ajax({
                    url : "/api/unfollowUser",
                    data : {user: user.textContent},
                    dataType : 'json',
                    method : "post",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        console.log(result)
                        top10.style.display = "none"
                        user_top.textContent= "You can't see the Top10 if you don't follow the user"
                        fol.textContent = "Follow"
                        fol.onclick = Follow
                        alert("You don't follow " + user.textContent + " anymore!")
                    }
        })
}