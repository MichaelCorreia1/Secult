servidor = localStorage.getItem("servidor");

function validarCadastroEvento() {
    var titulo = $("#tituloAdm").val();
    var descricao = $("#descricaoAdm").val();
    var dataEvento = $("#dataEventoAdm").val();
    var horaEvento = $("#horarioAdm").val();
    var localidade = $("#localidadeAdm").val();
    var tipo = $("#tipoAdm").val();
    if (titulo != "" & dataEvento != "" & descricao != "" & horaEvento != "" & localidade != "" & tipo != "") {
        cadastroEvento();

    } else {
        alert("Preencha todos os campos!")
    }
}

function cadastroEvento() {
    var titulo = $("#tituloAdm").val();
    var descricao = $("#descricaoAdm").val();
    var dataEvento = $("#dataEventoAdm").val();
    var horaEvento = $("#horarioAdm").val();
    var localidade = $("#localidadeAdm").val();
    var tipo = $("#tipoAdm").val();

    var json = servidor + "/Secult/evento/insertEvento/" + titulo + "&" + descricao + "&" + dataEvento + "&n&" + tipo + "&" + horaEvento + "&" + localidade;

    var onSuccess = function (result) {

        jsonAdministrador = result;

        Administrador = jsonAdministrador.status;

        if (Administrador == "ok") {
            setTimeout(function () {
                window.location.href = "#/page18";
            }, 1000)
        }
        ;
    };
    $.getJSON(json, onSuccess).fail();
}

function listarEvento() {

    var json = servidor + "/Secult/evento/listarEvento";

    var onSuccess = function (result) {

        dados = result.eventos;

        if (dados[0]) {

            for (var i in dados) {

                var id = dados[i].id;
                var titulo = dados[i].titulo;
                var descricao = dados[i].descricao;
                var visibilidade = dados[i].visibilidade;
                var tipo = dados[i].tipo_evento

                var dataCadastro = dados[i].data_cadastro;
                var horaEvento = dados[i].hora_evento;
                var dataEvento = dados[i].data_evento;
                var idLocalidade = dados[i].id_localidade;


                $("#inicioListaEventoHoje").append("<ul class='list' >\n" +
                    "            <li class=\"item item-stable item-icon-right item-icon-left  \" >\n" +
                    "                    <i id='visivel" + id + "' class=\"icon ion-eye inline \"></i>\n" +
                    "                    <h2 id='titulo" + id + "'>" + titulo + "</h2>\n" +
                    "                    <i id='checked" + id + "' class=\"icon ion-ios-checkmark inline \"></i>\n" +
                    "            </li>\n" +
                    "\n" +
                    "            <li class=\"item\" style=\"padding: 0px\">\n" +
                    "                <div  class=\"button-bar\">\n" +
                    "                    <a class='button button-light button-outline' href='#/page20' onclick='preencherEventoAtualizar(" + id + ",\"" + visibilidade + "\",\"" + titulo + "\",\"" + dataEvento + "\",\"" + descricao + "\",\"" + horaEvento + "\",\"" + tipo + "\",\"" + idLocalidade + "\",\"" + idLocalidade + "\")'><div  style=\"font-weight:600;color:#0092FF;font-size:17px;\"\n" +
                    "                              id='" + id + "'>Editar\n" +
                    "                    </div></a>\n" +
                    "                    <a class='button button-light button-outline'><div  style=\"font-weight:600;color:#FF0020;font-size:17px;\">Excluir\n" +
                    "                    </div></a>\n" +
                    "                </div>\n" +
                    "            </li>\n" +
                    "        </ul>");

                if (visibilidade == "s") {
                    $("#visivel" + id).css('color', 'green');

                } else {
                    $("#visivel" + id).css('color', '#e43a38');

                }
                var aaa = false;
                if (titulo != "" & dataEvento != "" & descricao != "" & horaEvento != "" & idLocalidade != "" & tipo != "" & visibilidade == "s") {
                    $("#checked" + id).css('color', 'green');
                } else {
                    $("#checked" + id).css('color', '#e43a38');

                }

            }
        }

    };


    $.getJSON(json, onSuccess).fail(

    );


}

function preencherEventoAtualizar(id, visibilidade, titulo, dataEvento, descricao, horaEvento, tipo, idLocalidade) {
    id = $("#" + id).attr('id');
    setTimeout(function () {
        $("#tituloUp").val(titulo);
        $("#descricaoUp").val(descricao);
        $("#dataEventoUp").val(dataEvento);
        $("#horarioUp").val(horaEvento);
        $("#localidadeUp").val(idLocalidade);
        $("#tipoUp").val(tipo);
        $("#visibilidadeUp").val(visibilidade);
        $("#updateEventoBtn").attr('onclick', "updateEvento(" + id + ")");
    }, 1000);
}

function updateEvento(id) {
    var titulo = $("#tituloUp").val();
    var descricao = $("#descricaoUp").val();
    var dataEvento = $("#dataEventoUp").val();
    var horaEvento = $("#horarioUp").val();
    var localidade = $("#localidadeUp").val();
    var tipo = $("#tipoUp").val();
    var visibilidade = $("#visibilidadeUp").val();


    var json = servidor + "/Secult/evento/updateEvento/" + id + "&" + titulo + "&" + descricao + "&" + dataEvento + "&" + visibilidade + "&" + tipo + "&" + horaEvento + "&" + localidade;

    var onSuccess = function (result) {

        jsonAdministrador = result;

        Administrador = jsonAdministrador.status;

        if (Administrador == "ok") {
            setTimeout(function () {
                window.location.href = "#/page18";

            }, 1000);
            listarEvento();
        }
        ;
    };
    $.getJSON(json, onSuccess).fail();
}


function limparEListarEventoAdm() {

    $("#inicioListaEventoHoje").empty();
    listarEvento();


}

function pullToRefreshNoticia() {
    PullToRefresh.init({
        mainElement: '.refresh', // above which element?
        onRefresh: function (done) {
            setTimeout(function () {
                done(); // end pull to refresh

                $("#listaEventoNoticas").empty();
                listarEventoNoticias();
            }, 500);
        }
    });
}

function pullToRefreshHoje() {
    PullToRefresh.init({
        mainElement: '.refresh', // above which element?
        onRefresh: function (done) {
            setTimeout(function () {
                done(); // end pull to refresh

                $("#listaEventoHoje").empty();
                listarEventoHoje();
            }, 500);
        }
    });
}

function pullToRefreshHome() {
    PullToRefresh.init({
        mainElement: '.refresh', // above which element?
        onRefresh: function (done) {
            setTimeout(function () {
                done(); // end pull to refresh

                $("#listaEventoNoticas").empty();
                listarEventoNoticias();
                $("#listaEventoHoje").empty();
                listarEventoHoje();
            }, 500);
        }
    });
}




