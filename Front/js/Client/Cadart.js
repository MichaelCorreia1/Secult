function mascarasCadart() {
    $("#telCdt").mask("000-00000-0000");
    $("#cpfCdt").mask("000.000.000-00");
}

function verificarCpf() {
    $("#cpfCdt").keyup(function () {
        if ($("#cpfCdt").val() != '') {
            var cpf = $("#cpfCdt").val();
            cpf = cpf.replace(/[^0-9]/g, '');
            if (isCpf(cpf) == false) {
                $("#cpfCdtInvalido").show('pulsate');
            } else {
                $("#cpfCdtInvalido").hide();
            }
        } else {
            $("#cpfCdtInvalido").hide();
        }
    })
}

function verificarTel() {
    $("#telCdt").keyup(function () {
        if ($("#telCdt").val() != '') {
            if ($("#telCdt").val().length < 14 & $("#telCdt").val().length > 0) {
                $("#TelCdtInvalido").show('pulsate');
            } else {
                $("#TelCdtInvalido").hide();
            }
        } else {
            $("#TelCdtInvalido").hide();
        }
    })
}

function verificarSenha() {
    $("#senhaCdt").keyup(function () {

        var senha = $("#senhaCdt").val();
        if (senha.length > 0 & senha.length < 6) {
            $("#senhaCdtInvalido").show('pulsate');
        } else {
            $("#senhaCdtInvalido").hide();
        }
    });
    $("#senhaRedCdt").keyup(function () {
        var senhaRed = $("#senhaRedCdt").val();
        var senha = $("#senhaCdt").val();

        if (senhaRed == senha & senhaRed != "") {
            $("#senhaRedCdtInvalido").hide();
        } else {
            $("#senhaRedCdtInvalido").show('pulsate');

        }
    });
}

function validarCampos() {
    var descricao = $("#descricaoCdt").val();
    var projetos = $("#projetosCdt").val();
    var nome = $("#nomeCdt").val();
    var nomeArt = $("#nomeArtCdt").val();
    var cpf = $("#cpfCdt").val();
    cpf = cpf.replace(/[^0-9]/g, '');
    var email = $("#emalCdt").val();
    var tel = $("#telCdt").val();
    var dtNascimento = $("#dataNascimentoCdt").val();
    var sexo = $("#sexoCdt").val();
    var arte = $("#arteCdt").val();
    var senha = $("#senhaCdt").val();
    var senhaRed = $("#senhaRedCdt").val();
    if (isCpf(cpf) & nome != "" & email != "" & tel != "" & nomeArt != "" & arte != "" & senha != "" & dtNascimento != "" & sexo != "") {
        if (senha === senhaRed) {
            cadastrarCdt()
        }
    } else {
        $("#cadastroRedCdtInvalido").show('pulsate')
    }

}

function cadastrarCdt() {
    var descricao = $("#descricaoCdt").val();
    var projetos = $("#projetosCdt").val();
    var nome = $("#nomeCdt").val();
    var nomeArt = $("#nomeArtCdt").val();
    var cpf = $("#cpfCdt").val();
    cpf = cpf.replace(/[^0-9]/g, '');
    var email = $("#emalCdt").val();
    var tel = $("#telCdt").val();
    // tel = tel.replace(/[^0-9]/g, '');

    var dtNascimento = $("#dataNascimentoCdt").val();
    var sexo = $("#sexoCdt").val();
    var arte = $("#arteCdt").val();
    var senha = $("#senhaCdt").val();
    var senhaRed = $("#senhaRedCdt").val();

    var json = servidor + "/Secult/cadart/insertUsuario/" + cpf + "&" + nome + "&" + nomeArt + "&" + tel + "&" + email + "&" + sexo + "&" + descricao + "&" + projetos + "&" + dtNascimento + "&" + senha + "&" + arte;

    var onSuccess = function (result) {

        jsonRestultado = result;

        resultado = jsonRestultado.status;

        if (resultado == "ok") {
            inserirFoto(cpf);
            swal({
                title: "Cadastrado!",
                text: "Aguarde nosso pessoal validar seus dados!",
                icon: "success",
                button: false,
            });
            setTimeout(function () {
                window.location.href = "#/page3";

            }, 2000)
        }else if (resultado == "erro") {
            swal({
                title: "CPF ja Cadastrado!",
                text: "Aguarde nosso pessoal validar seus dados!",
                icon: "error",
                button: false,
            });
        };
    };
    $.getJSON(json, onSuccess).fail();


}

function isCpf(strCPF) {
    var Soma;
    var Resto;
    Soma = 0;
    if (strCPF == "00000000000") return false;

    for (i = 1; i <= 9; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(9, 10))) return false;

    Soma = 0;
    for (i = 1; i <= 10; i++) Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
    Resto = (Soma * 10) % 11;

    if ((Resto == 10) || (Resto == 11)) Resto = 0;
    if (Resto != parseInt(strCPF.substring(10, 11))) return false;
    return true;
}

function listarCadart() {
    var json = servidor + "/Secult/cadart/listarUsuarios";
    var onSuccess = function (result) {

        dados = result.usuario;
        if (dados[0]) {
            for (var i in dados) {
                var nome = dados[i].nome;
                var nomeArtistico = dados[i].nomeArtistico;
                var arte = dados[i].nomeArte;




                $("#listaCadart").append("<div class=\"item item-avatar item-icon-right\">\n" +
                    "                <img src=\"img/utumw9gLSIe2GHD32dpQ_a1.png\">\n" +
                    "                <h2>"+nomeArtistico+"</h2>\n" +
                    "                <p>"+arte+"</p>\n" +
                    "            </div>")
            };
        }

    }
    $.getJSON(json, onSuccess).fail();
}