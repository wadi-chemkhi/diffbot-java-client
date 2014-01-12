package com.diffbot.clients;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param clazz the class type for a single product . This can be any class with fields named similarly to a product element of the products array in defined by diffbot API.
     * @param url the url to be processed using the diffbot API
     * @return Object to be casted to the clazz type , filled with data from the diffbot API according to the provided url
     */
    public List<Object> getProducts(Class<?> clazz ,String url) throws IOException {
        List<Object> products=new ArrayList<Object>();
        JSONObject json =new JSONObject( httpClient.getProducts(url));
        JSONArray jsonProducts=json.getJSONArray("products");
        for(int i=0;i<jsonProducts.length();i++){
            products.add(jsonToClass(clazz,jsonProducts.getJSONObject(i)));
        }
        return products;
    }

    /**
     * @param clazz the class type to be generated for output . This can be any class with fields named similarly to the article RESTFUL resource fields.
     * @param url the url to be processed using the diffbot API
     * @return Object to be casted to the clazz type , filled with data from the diffbot API according to the provided url
     */
    public Object getArticle(Class<?> clazz ,String url) throws IOException {
        return  jsonStringToClass(clazz, httpClient.getArticle(url));
    }

    public Object callApi(String api,Class<?> clazz ,String url) throws IOException {
        return jsonStringToClass(clazz,httpClient.getJson(api,url));
    }
    public Object callApi(String api,ResponseType responseType,String url) throws IOException {
        Object instance=null;
        switch (responseType){
            case Jackson:  ObjectMapper mapper = new ObjectMapper();
                mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
                instance= mapper.readTree(httpClient.getJson(api, url));
                break;
            default: instance= new JSONObject(httpClient.getJson(api, url));
        }
        return instance;
    }
    private Object jsonToClass(Class clazz, JSONObject json ){
        Object instance=null;
        try {
            instance =clazz.newInstance();
            for (Field field : clazz.getDeclaredFields()){
                if (json.has(field.getName())){
                    field.setAccessible(true);
                    field.set(instance,toObject(field.getType(),json.getString(field.getName())));
                    field.setAccessible(false);
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return instance;

    }
    private Object jsonStringToClass(Class clazz, String json ) throws IOException {
        Object instance=null;
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        instance = mapper.readValue(json, clazz);
        return instance;
    }
    private Object toObject( Class clazz, String value ) {
        if( Boolean.class.isAssignableFrom( clazz ) ) return Boolean.parseBoolean( value );
        if( Byte.class.isAssignableFrom( clazz ) ) return Byte.parseByte( value );
        if( Short.class.isAssignableFrom( clazz ) ) return Short.parseShort( value );
        if( Integer.class.isAssignableFrom( clazz ) ) return Integer.parseInt(value);
        if( Long.class.isAssignableFrom( clazz ) ) return Long.parseLong( value );
        if( Float.class.isAssignableFrom( clazz ) ) return Float.parseFloat( value );
        if( Double.class.isAssignableFrom( clazz ) ) return Double.parseDouble( value );
        return value;
    }

    public enum ResponseType{
        Jackson,
        JSONObject

    }
}
