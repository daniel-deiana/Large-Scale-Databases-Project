function addAnime(){
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

function updateAnime(){
    title = document.getElementById("title").value
    synopsis = document.getElementById("synopsis").value
    episodes = document.getElementById("episodes").value
    image = document.getElementById("image").value
        $.ajax({
                url : "/api/UpdateAnime",
                method : "post",
                dataType: "json",
                data : {title: title, synopsis: synopsis, episodes: episodes, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']===1)
                        alert("This anime does not exists!")
                    if(result['type']===0)
                        alert("Correctly updated!")
                }
            })
}


function addCharacter(){
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

function removeCharacter(){
    name = document.getElementById("name").value
    anime = document.getElementById("anime").value
    image = document.getElementById("image").value
        $.ajax({
                url : "/api/RemoveCharacter",
                method : "post",
                dataType: "json",
                data : {name: name, anime: anime, image: image},
                success: function(data) {
                    result = jQuery.parseJSON(data)
                    console.log(result)
                    if(result['type']===1)
                        alert("This character does not exist!")
                    if(result['type']===0)
                        alert("Correctly removed!")
                }
            })
        }


function deleteUser(){
    username = document.getElementById("user").value
    $.ajax({
            url : "/api/deleteUser",
            method : "post",
            dataType: "json",
            data : {username: username},
            success: function(data) {
                result = jQuery.parseJSON(data)
                console.log(result)
                if(result['type']===1)
                    alert("This user does not exists!")
                if(result['type']===0)
                    alert("Correctly deleted!")
            }
        })
    }
