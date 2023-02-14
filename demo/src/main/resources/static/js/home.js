$(document).ready(function () {
        const div = document.getElementById("NameUser")
        const name = document.createElement('h1')
        name.style.fontSize = "25px"
        $.ajax({
                    url : "/api/currentUser",
                    dataType: 'json',
                    method : "get",
                    success: function(data) {
                        result = jQuery.parseJSON(data)
                        name.textContent = "Welcome "+ result['username'] + "!"
                        div.appendChild(name)
                    }
                })
        $.ajax({
                            url : "/api/PersonalTop10",
                            method : "get",
                            success: function(data) {
                                result = jQuery.parseJSON(data)
                                console.log(result)
                                if(result == null)
                                    return;
                                for(fig in result){
                                if(fig<5){
                                    let html = '<div style= "margin:10px; width: 200px;display: flex; flex-direction: column; align-items: center; justify-content: center;">' +
                                        '<img referrerpolicy="no-referrer" src='+result[fig].image_url+'alt="" style="width:100px; height:140px;">' +
                                        '<h2 style="font-size: 15px; color: lavender;">' + result[fig].name +'</h2>' +
                                        '<h2 onclick="call_anime(this.textContent)" style="cursor: pointer; font-size: 13px;">'+ result[fig].anime +'</h2>' +
                                        '</div>'
                                    $("#first5").append(html)
                                }
                                else{
                                    let html = '<div style= "margin:8px; width: 200px;display: flex; flex-direction: column; align-items: center; justify-content: center;">' +
                                        '<img referrerpolicy="no-referrer" src='+result[fig].image_url+' alt="" style="width:100px; height:140px;">' +
                                        '<h2 style="font-size: 15px; color: lavender;">' + result[fig].name +'</h2>' +
                                        '<h2 onclick="call_anime(this.textContent)" style="cursor: pointer; font-size: 13px;">'+ result[fig].anime +'</h2>' +
                                        '</div>'
                                    $("#second5").append(html)
                                }
                                }
                            }
                        })
});

function call_anime(title_){
    $.ajax({
        url : "/api/anime_search",
        data : {title: title_},
        dataType : 'json',
        method : "post",
        success: function(data) {
            result = jQuery.parseJSON(data)
            console.log(result)
            if(result["type"] === 1) {
                alert("The anime does not exist")
                $("#title").val("")
            }
            else
                window.location.href= "http://localhost:8080/anime"
        }
    })
}