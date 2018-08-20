function listarEventoNoticias() {

    var json = servidor + "/Secult/evento/listarEventoGrande";

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


                    $("#listaEventoNoticas").append(" <a onclick='preencherNoticiaInfo(\""+descricao+'","'+ titulo+"\")' href='#/page21' class='linkSemDecoracao'> <div class=\"list card manual-card-fullwidth \" id=\"noticias-card21\" style='padding-top: 0px'>\n" +
                        "            <ul class=\"item item-icon-left item-icon-right positive\">\n" +
                        "                <i class=\"icon ion-android-calendar \"></i>\n" +
                        "                <p style=\"text-align: center; font-weight: bold; font-size: large; color: #3f83f5;\">"+titulo+"</p>\n" +
                        "                <i class=\"icon ion-android-share-alt\"></i>\n" +
                        "            </ul>\n" +
                        "            <div class=\" item item-image \">\n" +
                        "                <img src=\"img/0IInOP0dQUyLRwgnLH86_e1.jpg\"\n" +
                        "                     style=\"display: block; width: 100%; height: auto; margin-left: auto; margin-right: auto;\">\n" +
                        "            </div>\n" +
                        "            <div id=\"noticias-markdown4\" style=\"text-align:center;\" class=\"show-list-numbers-and-dots padding \">\n" +
                        "                <p style=\"margin-top:0px;color:#000000;\" >"+descricao+"</p>\n" +
                        "            </div>\n" +
                        "            <div class=\" item item-image\">\n" +
                        "                <img src=\"img/BuueI7PTdiAXyX5Enotk_WhatsAppImage2018-07-31at13.51.37.jpeg\"\n" +
                        "                     style=\"display: block; width: 100%; height: 60px; margin-left: auto; margin-right: auto;\">\n" +
                        "            </div>\n" +
                        "        </div></a>");



                if (visibilidade != "" & titulo != "" & dataEvento != "" & descricao != "" & horaEvento != "" & tipo != "" & idLocalidade != "" & dataCadastro != "") {
                    $("#alert" + id).hide();
                } else {
                    $("#checked" + id).hide();
                }


            }
        }

    };


    $.getJSON(json, onSuccess).fail(

    );


}

function preencherNoticiaInfo(d,t) {
    $("#titloNoticia").empty();
    $("#descricaoNoticia").empty();
    setTimeout(function () {
        $("#titloNoticia").append(""+t);
        $("#descricaoNoticia").append(""+d);

    },10)
}



