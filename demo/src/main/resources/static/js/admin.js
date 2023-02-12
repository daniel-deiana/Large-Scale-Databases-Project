$(document).ready(function () {
    document.getElementById("addAnime").onclick = function (e) {
    title = document.getElementById("title").value
    synopsis = document.getElementById("synopsis").value
    episodes = document.getElementById("episodes").value
    image = document.getElementById("image").value
        $.ajax({
                url : "/api/AddAnime",
                method : "post",
                data : {title: title, synopsis: synopsis, episodes: episodes, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']==1)
                        alert("This anime already exists!")
                    if(result['type']==0)
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
                data : {name: name, anime: anime, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']==1)
                        alert("This anime already exists!")
                    if(result['type']==0)
                        alert("Correctly added!")
                }
            })
        }
});