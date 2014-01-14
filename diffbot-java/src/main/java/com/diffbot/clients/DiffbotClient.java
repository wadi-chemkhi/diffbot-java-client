package com.diffbot.clients;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by wadi chemkhi on 02/01/14.
 * Email : wadi.chemkhi@gmail.com
 */
public class DiffbotClient {

    private DiffbotHttpClient httpClient;

    /**
     * @param token the access token for the diffbot API
     */
    public DiffbotClient(String token) {
        httpClient=new DiffbotHttpClient(token);
    }
    public DiffbotClient(String token,String version) {
        httpClient=new DiffbotHttpClient(token,version);
    }

    public Object analyze(Class<?> clazz ,String url) throws IOException {
        return callApi("analyze",clazz,url);
    }

    public Object analyze(Class<?> clazz ,String url,Map<String,String> params) throws IOException {
        return callApi("analyze",clazz,url,params);
    }

    public Object analyze(ResponseType responseType ,String url) throws IOException {
        return callApi("analyze",responseType,url);
    }
    public Object analyze(ResponseType responseType ,String url,Map<String,String> params) throws IOException {
        return callApi("analyze",responseType,url,params);
    }
    /**
     * @param clazz the class type for a single product . This can be any class with fields named similarly to a product element of the products array in defined by diffbot API.
     * @param url the url to be processed using the diffbot API
     * @return Object to be casted to the clazz type , filled with data from the diffbot API according to the provided url
     */
    public List<Object> getProducts(Class<?> clazz ,String url) throws IOException {
        return getProducts(clazz, url,null);
    }

    public List<Object> getProducts(Class<?> clazz ,String url,Map<String,String> params) throws IOException {
        List<Object> products=new ArrayList<Object>();
        ObjectMapper mapper = getMapper();
        JsonNode root= mapper.readTree(httpClient.getProducts(url,params));
        JsonNode array=root.get("products");
        Iterator<JsonNode> ite = array.getElements();
        while (ite.hasNext()) {
            JsonNode temp = ite.next();
            products.add(mapper.treeToValue(temp, clazz));
        }
        return products;
    }

    /**
     * @param clazz the class type to be generated for output . This can be any class with fields named similarly to the article RESTFUL resource fields.
     * @param url the url to be processed using the diffbot API
     * @return Object to be casted to the clazz type , filled with data from the diffbot API according to the provided url
     */
    public Object getArticle(Class<?> clazz ,String url) throws IOException {
        return  getArticle(clazz, url,null);
    }

    public Object getArticle(Class<?> clazz ,String url,Map<String,String> params) throws IOException {
        return  getMapper().readValue(httpClient.getArticle(url,params), clazz);
    }

    public Object callApi(String api,Class<?> clazz ,String url) throws IOException {
        return callApi(api,clazz,url,null);
    }

    public Object callApi(String api,Class<?> clazz ,String url,Map<String,String> params) throws IOException {
        return getMapper().readValue( httpClient.getJson(api, url,params),clazz);
    }

    public Object callApi(String api,ResponseType responseType,String url) throws IOException {
        return callApi(api,responseType,url,null);
    }

    public Object callApi(String api,ResponseType responseType,String url,Map<String,String> params) throws IOException {
        Object instance;
        switch (responseType){
            case Jackson: instance=getMapper().readTree(httpClient.getJson(api, url,params));
                break;
            default: instance= new JSONObject(httpClient.getJson(api, url,params));
        }
        return instance;
    }

    protected ObjectMapper getMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return mapper;
    }

    public enum ResponseType{
        Jackson,
        JSONObject
    }
}
