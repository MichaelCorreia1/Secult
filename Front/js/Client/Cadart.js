function validarCampos() {
    var descricao = $("#descricaoCdt").val();
    var projetos = $("#projetosCdt").val();
    var nome = $("#nomeCdt").val();
    var nomeArt = $("#nomeArtCdt").val();
    var cpf = $("#cpfCdt").val();
    var email = $("#emalCdt").val();
    var tel = $("#telCdt").val();
    var dtNascimento = $("#dataNascimentoCdt").val();
    var sexo = $("#sexoCdt").val();
    var arte = $("#arteCdt").val();
    var senha = $("#senhaCdt").val();
    var senhaRed = $("#senhaRedCdt").val();
    if(cpf != "" & nome != "" & email != "" & tel!= "" & arte != "" & senha!= ""& dtNascimento != ""  & sexo != ""){

    }

}
function cadastrarCdt() {
    var descricao = $("#descricaoCdt").val();
    var projetos = $("#projetosCdt").val();
    var nome = $("#nomeCdt").val();
    var nomeArt = $("#nomeArtCdt").val();
    var cpf = $("#cpfCdt").val();
    var email = $("#emalCdt").val();
    var tel = $("#telCdt").val();
    var dtNascimento = $("#dataNascimentoCdt").val();
    var sexo = $("#sexoCdt").val();
    var arte = $("#arteCdt").val();
    var senha = $("#senhaCdt").val();
    var senhaRed = $("#senhaRedCdt").val();

    var json = servidor + "/Secult/cadart/insertUsuario/" + cpf + "&" + nome + "&" + nomeArt+ "&" + email + "&"+ tel +  "&"+ sexo + "&" + descricao+  "&" + projetos + "&" + dtNascimento + "&" + senha + "&" + arte;

    var onSuccess = function (result) {

        jsonRestultado = result;

        resultado = jsonRestultado.status;

        if (resultado == "ok") {
            setTimeout(function () {
                window.location.href = "#/page18";
            }, 1000)
        }
        ;
    };
    $.getJSON(json, onSuccess).fail();


}