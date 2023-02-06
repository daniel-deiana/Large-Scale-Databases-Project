$(document).ready(function () {
        let obj = document.getElementById("title_anime")
        let desc = document.getElementById("desc_anime")
        let img = document.getElementById("pic_anime")
        $.ajax({
                    url : "/api/currentAnime",
                    method : "get",
                    dataType: "json",
                    success: function(data) {
                        result = JSON.parse(data)
                        console.log(result)
                        obj.textContent = result['title']
                        desc.textContent = result['desc']
                        img.setAttribute('src', result['img'])
                    }
                })
});