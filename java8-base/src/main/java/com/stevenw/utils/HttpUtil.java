package com.stevenw.utils;

//import com.tpg.erp.mail.EmailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP工具
 *
 * @author robinzhang
 */
public class HttpUtil {
    /**
     * 请求类型： GET
     */
    public final static String GET = "GET";
    /**
     * 请求类型： POST
     */
    public final static String POST = "POST";
    private final static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * 模拟Http Get请求
     *
     * @param urlStr   请求路径
     * @param paramMap 请求参数
     * @return
     * @throws Exception
     */
    public static String get(String urlStr, Map<String, String> paramMap) {
        urlStr = urlStr + "?" + getParamString(paramMap);
        HttpURLConnection conn = null;
        String result = "";
        try {
            //创建URL对象
            URL url = new URL(urlStr);
            if("https".equalsIgnoreCase(url.getProtocol())){
                SslUtils.ignoreSsl();
            }
            //获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            if (null == conn){
//                EmailUtils.sendEmail("HTTPClient连接失败,请检查");
            }
            //设置通用的请求属性
            setHttpUrlConnection(conn, GET);
            //建立实际的连接
            conn.connect();
            //获取响应的内容
            result =  readResponseContent(conn.getInputStream());
        } catch (Exception e) {
//            EmailUtils.sendEmail(e.getMessage());
            e.printStackTrace();
        } finally {
            if (null != conn) {
                conn.disconnect();
            }
        }
        return result;
    }

    /**
     * 模拟Http Post请求
     *
     * @param urlStr   请求路径
     * @param paramMap 请求参数
     * @return
     * @throws Exception
     */
    public static String post(String urlStr, Map<String, String> paramMap){
        HttpURLConnection conn = null;
        PrintWriter writer = null;
        String result = "";
        try {
            //创建URL对象
            URL url = new URL(urlStr);
            if("https".equalsIgnoreCase(url.getProtocol())){
                SslUtils.ignoreSsl();
            }
            //获取请求参数
            String param = getParamString(paramMap);
            //获取URL连接
            conn = (HttpURLConnection) url.openConnection();
            if (null == conn){
//                EmailUtils.sendEmail("HTTPClient连接失败,请检查");
            }
            //设置通用请求属性
            setHttpUrlConnection(conn, POST);
            //建立实际的连接
            conn.connect();
            //将请求参数写入请求字符流中
            logger.info("post参数" + param);
            writer = new PrintWriter(conn.getOutputStream());
            writer.print(param);
            writer.flush();
            //读取响应的内容
            result =  readResponseContent(conn.getInputStream());
        }catch (Exception e){
//            EmailUtils.sendEmail(e.getMessage());
            e.printStackTrace();
        }finally {
            if (null != conn) {
                conn.disconnect();
            }
            if (null != writer) {
                writer.close();
            }
        }
        return result;
    }

    /**
     * 读取响应字节流并将之转为字符串
     *
     * @param in 要读取的字节流
     * @return
     * @throws IOException
     */
    private static String readResponseContent(InputStream in) throws IOException {
        Reader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            reader = new InputStreamReader(in);
            char[] buffer = new char[1024];
            int head = 0;
            while ((head = reader.read(buffer)) > 0) {
                content.append(new String(buffer, 0, head));
            }
            return content.toString();
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != reader) {
                reader.close();
            }
        }
    }

    /**
     * 设置Http连接属性
     *
     * @param conn http连接
     * @return
     * @throws ProtocolException
     * @throws Exception
     */
    private static void setHttpUrlConnection(HttpURLConnection conn, String requestMethod) throws ProtocolException {
        conn.setRequestMethod(requestMethod);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Accept-Language", "zh-CN");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0)");
        conn.setRequestProperty("Proxy-Connection", "Keep-Alive");
        if (null != requestMethod && POST.equals(requestMethod)) {
            conn.setDoOutput(true);
            conn.setDoInput(true);
        }
    }

    /**
     * 将参数转为路径字符串
     *
     * @param paramMap 参数
     * @return
     */
    private static String getParamString(Map<String, String> paramMap) {
        if (null == paramMap || paramMap.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (String key : paramMap.keySet()) {
            builder.append("&")
                    .append(key).append("=").append(paramMap.get(key));
        }
        return builder.deleteCharAt(0).toString();
    }
    public static void main(String[] args) {
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("dt_from", "2021/03/18 10:00:00");
        map.put("dt_to", "2021/03/19 18:00:00");
        String body = HttpUtil.get("https://www49.rpm-sys.jp/RPMv4-threepro-preview/api/20/applicants_listV2.cfm", map);
    }

}
