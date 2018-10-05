
function cadastrarRedeSocial(id_usuario,idImputRedeSocial) {
    var nome = $("#"+idImputRedeSocial).parent().text().trim()
    var link = $("#"+idImputRedeSocial).val();
    alert(link, nome)
    var json = servidor + "/Secult/redes/inserirRedes/" + nome + "&" + link + "&" + id_usuario;
    var onSuccess = function (result) {
        var status = result.status;
        if (status == "ok") {
            alert('sim')
        } else {
           alert("erro ")
        }
        ;
    };
    $.getJSON(json, onSuccess).fail(
    );
}

function listarRedeSociais(cpf) {
    var json = servidor + "/Secult/redes/listarRedesById/" +cpf;
    var onSuccess = function (result) {
        dados = result.redeSocial;
        if (dados[0]) {
            for (var i in dados) {
                var nome = dados[i].nomeRede;
                var link = dados[i].nomeLink;
                    switch (nome) {
                        case "Facebook":
                            $("#facebook").attr("href", link);
                            break;
                        case "Instagram":
                            $("#instagram").attr("href", "https://www.instagram.com/"+link);
                            break;
                        case "Youtube":
                            $("#youtube").attr("href", link);
                            break;

                    }
            };
        }

    }
    $.getJSON(json, onSuccess).fail();
}

function preencherCamposRedesUpdate(cpf) {
    var json = servidor + "/Secult/redes/listarRedesById/" +cpf;
    var onSuccess = function (result) {
        var dados = result.redeSocial;
        if (dados[0]) {
            for (var i in dados) {
                var nome = dados[i].nomeRede;
                var link = dados[i].nomeLink;
                switch (nome) {
                    case "Facebook":
                        $("#upRedeSocial1").val(link);
                        break;
                    case "Instagram":
                        $("#upRedeSocial2").val(link);
                        break;
                    case "Youtube":
                        $("#upRedeSocial3").val(link);
                        break;

                }
            };
        }

    }
    $.getJSON(json, onSuccess).fail();
}

function updateRedeSocial(cpf, idInputRedeSocial, nomeRedesocial) {
    var link = $("#"+idInputRedeSocial).val();

   var json = servidor + "/Secult/redes/alterarRedes/"+ nomeRedesocial + "&" +link+ "&" +cpf;
   var onSucess = function(result){
        if(result.status == "ok"){

        }else{
            alert("Erro ao modificar uma rede social, entre emcontato com a administração");
        }
    }

    $.getJSON(json, onSucess).fail();
}