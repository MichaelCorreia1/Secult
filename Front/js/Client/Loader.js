var servidor = localStorage.getItem("servidor");

function autenticar(txtEmail, txtSenha) {
    var json = servidor + "/Collegium/usuario/autenticar/" + txtEmail + '&' + txtSenha;

    var onSucess = function (result) {
        dados = result.usuario;

        if (dados[0]) {
            var id = dados[0].id;
            var nome = dados[0].nome;
            var email = dados[0].email;
            var telefone = dados[0].telefone;
            var tipo = dados[0].tipo;
            var endereco = dados[0].endereco;
            var cidade = dados[0].cidade;
            var estado = dados[0].estado;
            var foto = dados[0].foto;


            localStorage.setItem("id", id);
            localStorage.setItem("nome", nome);
            localStorage.setItem("email", email);
            localStorage.setItem("telefone", telefone);
            localStorage.setItem("tipo", tipo);
            localStorage.setItem("endereco", endereco);
            localStorage.setItem("cidade", cidade);
            localStorage.setItem("estado", estado);
            localStorage.setItem("foto", foto);

            window.location = "#/page1/page2";

            validarAdministrador();



        } else {
            $("#invalido").text("Email e/ou senha inválidos");
        }
    };

    $.getJSON(json, onSucess).fail(
        function () {


        }
    );


}

function toBase64String(img) {

    var c = document.createElement('canvas');

    var ctx = c.getContext("2d");

    var nBase = 500;
    var nProporcao = 0;
    var width = img.naturalWidth;
    var height = img.naturalHeight;


    if (width > 500 || height > 500) {

        if (width > height) {
            nProporcao = ((100 * nBase) / width);
            height = ((height * nProporcao) / 100);
            width = nBase;
        } else {
            nProporcao = ((100 * nBase) / height);
            width = ((width * nProporcao) / 100);
            height = nBase;
        }

        c.width = width;
        c.height = height;
    } else {

        c.height = img.naturalHeight;
        c.width = img.naturalWidth;
    }


    ctx.drawImage(img, 0, 0, c.width, c.height);
    return c.toDataURL();


}


function b64toBlob(b64Data, contentType, sliceSize) {
    contentType = contentType || '';
    sliceSize = sliceSize || 512;

    var byteCharacters = atob(b64Data);
    var byteArrays = [];

    for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
        var slice = byteCharacters.slice(offset, offset + sliceSize);

        var byteNumbers = new Array(slice.length);
        for (var i = 0; i < slice.length; i++) {
            byteNumbers[i] = slice.charCodeAt(i);
        }

        var byteArray = new Uint8Array(byteNumbers);

        byteArrays.push(byteArray);
    }

    var blob = new Blob(byteArrays, {type: contentType});
    return blob;
}