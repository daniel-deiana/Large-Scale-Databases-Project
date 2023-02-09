$(document).ready(function () {
        const div = document.getElementById("NameUser")
        const name = document.createElement('h1')
        name.style.fontSize = "40px"
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
});

//             <img src="img/unkown_c.png" class="img-fluid animated" alt="" style="width: 15%; height: 15%;">