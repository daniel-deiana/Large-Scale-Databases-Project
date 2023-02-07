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
                                '              <img src='+ characters[fig].url +' class="img-fluid" alt="">\n' +
                                '              <div>\n' +
                                '                <h4>'+ characters[fig].name + '</h4>\n' +
                                '              </div>\n' +
                                '              <div>\n' +
                                '                <a href="characters.html" title="More Details"><i class="bx bx-link"></i></a>\n' +
                                '              </div>\n' +
                                '            </div>\n' +
                                '          </div>'
                            $('#figures').append(html)

                        }
                    }
        })
});