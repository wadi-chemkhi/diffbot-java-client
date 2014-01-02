package com.diffbot.clients;

import org.json.JSONObject;

import java.lang.reflect.Field;

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
     * @param clazz the class type to be generated for output . This can be any class with fields named similarly to the article RESTFUL resource fields.
     * @param url the url to be processed using the diffbot API
     * @return Object to be casted to the clazz type , filled with data from the diffbot API according to the provided url
     */
    public Object getArticle(Class<?> clazz ,String url){

        Object instance=null;
        try {
            instance =clazz.newInstance();
            JSONObject articleJson =new JSONObject( httpClient.getArticle(url));
            for (Field field : clazz.getDeclaredFields()){
                if (articleJson.has(field.getName())){
                    field.setAccessible(true);
                    field.set(instance,toObject(field.getType(),articleJson.getString(field.getName())));
                    field.setAccessible(false);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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

}
