package com.meuprojeto.projetoloja;


import com.meuprojeto.enums.ApiTokenIntegracao;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



public class TesteAPIMelhorEnvio {

    public static void main(String[] args) throws Exception {


        /**
         * Faz a compra para etiqueta
         */
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"orders\":[\"\"a54d16ed-1a73-4625-b73f-fb24833b9fb6\"]}");
//        Request request = new Request.Builder()
//                .url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/checkout")
//                .post(body)
//                .addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SAND_BOX).build();
//
//        Response response = client.newCall(request).execute();





//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"options\":{\"receipt\":true,\"own_hand\":true,\"reverse\":true,\"non_commercial\":true}}");
//        Request request = new Request.Builder()
//                .url(ApiTokenIntegracao.URL_MELHOR_ENVIO_SAND_BOX + "api/v2/me/cart")
//                .post(body)
//                .addHeader("Accept", "application/json")
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Authorization", "Bearer " + ApiTokenIntegracao.TOKEN_MELHOR_ENVIO_SAND_BOX)
//                .addHeader("User-Agent", "suporte@jdevtreinamento.com.br")
//                .build();
//
//        Response response = client.newCall(request).execute();
//
//        System.out.println(response.body().string());


        /**
         * Geração de etiquetas
         */
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"orders\":[\"string\"]}");
        Request request = new Request.Builder()
                .url("https://sandbox.melhorenvio.com.br/api/v2/me/shipment/generate")
                .post(body)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", "Bearer token")
                .addHeader("User-Agent", "suporte@jdevtreinamento.com.br")
                .build();

        Response response = client.newCall(request).execute();



    }

}
