function preencherDadosLocalidade() {
    $("#nomeEvento").empty();
    var json = servidor + "/Secult/localidade/listarLocalidadeComNome";

    var onSuccess = function (result) {

        dados = result.localidades;

        for(var i in dados){

            var idLocal = dados[i].id;
            var nomeEvento = dados[i].nomeEvento;
            var nome = dados[i].nome;
            var descricao = dados[i].descricao;

            if($("#idLocalidade").val() == idLocal) {
                $("#nomeLocalidade").empty();
                $("#descLocalidade").empty();
                $("#nomeLocalidade").append(nome);
                $("#descLocalidade").append(descricao);

                $("#nomeEvento").append("<ion-item class=\"item-icon-right item positive\">"+nomeEvento+"\n" +
                    "        <i class=\"icon ion-flame\"></i>\n" +
                    "      </ion-item>");
            }
        }

    }
    $.getJSON(json, onSuccess).fail();
}

function selectLocalidade() {
    setTimeout(function () {

        var json = servidor + "/Secult/localidade/carregarLocalidade";

        var onSuccess = function (result) {

            dados = result.localidades;

            for (var i in dados) {

                var idLocal = dados[i].id;
                var local = dados[i].nome;

                $("#idLocalidade").append("<option value=" + idLocal + ">" + local + "</option>")
            }
            $("#idLocalidade option[value=1]").prop("selected", true).change()
        }
        $.getJSON(json, onSuccess).fail();
    },1000)
}