function listarEventoHoje() {
    var tres = 2;
    var res;
    var json = servidor + "/Secult/evento/listarEventoPequeno";

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

            //<a href='#/page21' class='linkSemDecoracao' onclick='preencherEventoNoticiaInfo(\""+descricao+'","'+ titulo+"\")'>

                $("#listaEventoHoje").append("<a href='#/page21'  class='linkSemDecoracao' onclick='preencherEventoInfo(\""+descricao+'","'+ titulo+"\")'><li class=\"item item-thumbnail-left item-icon-right balanced\">\n" +
                    "        <img src=\"img/BjQ4ZRhETaiMKtwCXnLk_ac3.png\">\n" +
                    "        <h2balanced>" + titulo + "\n" +
                    "          <p style=\"white-space:normal;\">" + descricao.substring(0, 47) + "...</p>\n" +
                    "          <i class=\"icon ion-android-share\"></i>\n" +
                    "        </h2 balanced>\n" +
                    "      </li>\n" +
                    "      <li class=\"item item-icon-left\">\n" +
                    "        <i class=\"icon ion-android-star-outline\"></i>Entrada da Cidade\n" +
                    "        <span class=\"item-note\">" + horaEvento + "</span>\n" +
                    "      </li></a>");


                if (i == tres) {
                    tres = tres + 3;
                    $("#listaEventoHoje").append("    <div class=\"spacer\" style=\"height: 4px;\"></div>\n" +
                        "    <div>\n" +
                        "      <img src=\"img/sEbQCcVzT22AyiWd4w0I_WhatsAppImage2018-07-31at13.51.371.jpeg\" style=\"display:block;width:100%;height:50px;\">\n" +
                        "    </div>\n" +
                        "    <div class=\"spacer\" style=\"height: 5px;\"></div>\n");
                }
            }
        }
    };
    $.getJSON(json, onSuccess).fail(

    );
}
function preencherEventoInfo(d,t) {
    $("#titloNoticia").empty();
    $("#descricaoNoticia").empty();
    setTimeout(function () {
        $("#titloNoticia").append(""+t);
        $("#descricaoNoticia").append(""+d);

    },10)
}