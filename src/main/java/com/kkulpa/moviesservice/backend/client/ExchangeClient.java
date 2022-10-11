package com.kkulpa.moviesservice.backend.client;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ExchangeClient {

    private final String API_KEY = "CRbZVBJ1dVDJr334AMO8oTX458VYLF9r";
    private final String API_ENDPOINT = "https://api.apilayer.com/exchangerates_data/convert?"; // left here because it is example project

    public double convertCurrencies(String from, String to, String amount )
            throws IOException, PathNotFoundException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String url = generateUrl(from, to, amount);
        Request request = generateRequest(url);

        Response response = client.newCall(request).execute();
        String rawJson = response.body() != null ? response.body().string() : null;

        if( rawJson==null)
            throw new PathNotFoundException();

        return JsonPath.read(rawJson, "$.result");
    }

    private Request generateRequest(String url){
        return new Request.Builder()
                .url(url)
                .addHeader("apikey", API_KEY)
                .method("GET", null)
                .build();
    }

    private String generateUrl(String from, String to, String amount){
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(API_ENDPOINT).newBuilder();
        urlBuilder.addQueryParameter("from", from);
        urlBuilder.addQueryParameter("to", to);
        urlBuilder.addQueryParameter("amount",amount);

        return urlBuilder.build().toString();
    }

}
