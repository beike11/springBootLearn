package com.stevenw.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author stevenw
 * @date 2019/11/26
 */
public class HttpUtils {
    public static String get(String url){

        return "";
    }

    public static void main(String[] args) {
        System.err.println(10%10);
//        System.setProperty("proxyType", "4");
//        System.setProperty("proxyPort", "2333");
//        System.setProperty("proxyHost", "127.0.0.1");
//        System.setProperty("proxySet", "true");
//
//        HttpUtils.ssl("https://www.googleapis.com/calendar/v3/calendars/zh-cn.japanese%23holiday%40group.v.calendar.google.com/events?timeMax=2019-11-25T23%3A59%3A59Z&timeMin=2019-11-25T00%3A00%3A00Z&key=AIzaSyDCCWzoL2pEYb15WoJFLPBkcCo0EYRFxiA");
    }

    public static String ssl(String url){
        HttpClient httpClient = new HttpClient();
        httpClient.getHostConfiguration().setProxy("127.0.0.1",2334);
        GetMethod httpget = new GetMethod(url);
        try {
            httpClient.executeMethod(httpget);
            System.err.println(httpget.getResponseBodyAsString());
            return "";
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpget.releaseConnection();

        }
        return "";
    }
}
