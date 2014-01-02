package com.diffbot.clients;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class DiffbotHttpClient {

    private String token;

    /**
     * @param token the access token for the diffbot API
     */
    public DiffbotHttpClient(String token)  {
        this.token=token;
    }
    /**
     * @param url the url to be processed using the diffbot API.
     * @return String representing the json data of an article according to the provided url.
     */
    public String getArticle(String url){
        URI uri=null;
        try{
        uri = new URIBuilder()
                .setScheme("http")
                .setHost("api.diffbot.com")
                .setPath("/v2/article")
                .setParameter("token", token)
                .setParameter("url", url)
                .build();}
        catch (URISyntaxException e){
            e.printStackTrace();

        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(uri);
        CloseableHttpResponse response=null;
        String json=null;
        try {
            response = httpClient.execute(httpGet);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    response.getEntity().getContent(), "utf-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            json=sb.toString();
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;

    }
}
