function inserirFoto() {
    var id_usuario = 1;
    var json = "http://localhost:8080/Secult/evento/salvarFoto/" + id_usuario;

    var ImageURL = localStorage.getItem("fotoCadastro");

    var block = ImageURL.split(";");

    var contentType = block[0].split(":")[1];

    var realData = block[1].split(",")[1];

    var blob = b64toBlob(realData, contentType);

    var formDataToUpload = new FormData();

    formDataToUpload.append("id", id_usuario);
    formDataToUpload.append("foto", blob);

    $.ajax({
        url: json,
        data: formDataToUpload,
        type: "POST",
        contentType: false,
        processData: false,
        cache: false,
        dataType: "json",

        error: function (err) {
            console.log(err);

        },

        complete: function () {
            window.location = "#/page1/page2";
            location.reload();
        },
    })
}

//converte foto
function saveFotoLS() {

    document.getElementById("tableBanner").style.display = "none";
    var bannerImage = document.getElementById("bannerImg");

    var img = document.getElementById("tableBanner");

    bannerImage.addEventListener("change", function () {

        var file = this.files[0];
        if (file.type.indexOf("image") < 0) {
            alert("arquivo invalido");
            return;
        }
        var fReader = new FileReader();
        fReader.onload = function () {
            img.onload = function () {

                localStorage.setItem("fotoCadastro", toBase64String(img));

            };
            img.src = fReader.result;

        };

        fReader.readAsDataURL(file);

    });

}