import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class HttpRequestUtil {
	private static Logger logger = Logger.getLogger(HttpRequestUtil.class);
	  
    /** 
     * 连接超时 
     */  
    private static int connectTimeOut = 5000;  
  
    /** 
     * 读取数据超时 
     */  
    private static int readTimeOut = 10000;  
  
    /** 
     * 请求编码 
     */  
    private static String requestEncoding = "GBK";
  
    /** 
     * <pre> 
     * 发送带参数的GET的HTTP请求 
     * </pre> 
     *  
     * @param reqUrl 
     *            HTTP请求URL 
     * @param parameters 
     *            参数映射表 
     * @return HTTP响应的字符串 
     */  
    @SuppressWarnings("unchecked")  
    public static String doGet(String reqUrl, Map parameters,String recvEncoding) {  
        HttpURLConnection url_con = null;  
        String responseContent = null;  
        try {  
            StringBuffer params = new StringBuffer();  
            for (Iterator iter = parameters.entrySet().iterator(); iter  
                    .hasNext();) {  
                Entry element = (Entry) iter.next();  
                params.append(element.getKey().toString());  
                params.append("=");  
                params.append(URLEncoder.encode(element.getValue().toString(),  
                        HttpRequestUtil.requestEncoding));  
                params.append("&");  
            }  
  
            if (params.length() > 0) {  
                params = params.deleteCharAt(params.length() - 1);  
                reqUrl = reqUrl + "?" + params;  
            }  
  
            URL url = new URL(reqUrl);  
            url_con = (HttpURLConnection) url.openConnection();  
            url_con.setRequestMethod("GET");  
  
            url_con.setConnectTimeout(HttpRequestUtil.getConnectTimeOut());  
            url_con.setReadTimeout(HttpRequestUtil.getReadTimeOut());  
  
            InputStream in = url_con.getInputStream();  
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,  
                    recvEncoding));  
            String tempLine = rd.readLine();  
            StringBuffer temp = new StringBuffer();  
            String crlf = System.getProperty("line.separator");  
            while (tempLine != null) {  
                temp.append(tempLine);  
                temp.append(crlf);  
                tempLine = rd.readLine();  
            }  
            responseContent = temp.toString();  
            rd.close();  
            in.close();  
        } catch (IOException e) {  
            logger.error("链接SMS Server 失败。 网络不稳定，或者网络已断开");  
        } finally {  
            if (url_con != null) {  
                url_con.disconnect();  
            }  
        }  
  
        return responseContent;  
    }  
  
    /** 
     * <pre> 
     * 发送不带参数的GET的HTTP请求 
     * </pre> 
     *  
     * @param reqUrl 
     *            HTTP请求URL 
     * @return HTTP响应的字符串 
     */  
    public static String doGet(String reqUrl, String recvEncoding) {  
        Map<String, String> parameters = new HashMap<String, String>();  
        String queryUrl = reqUrl;  
        int paramIndex = reqUrl.indexOf("?");  
  
        if (paramIndex > 0) {  
            queryUrl = reqUrl.substring(0, paramIndex);  
            String params = reqUrl.substring(paramIndex + 1, reqUrl.length());  
            String[] paramArray = params.split("&");  
            for (int i = 0; i < paramArray.length; i++) {  
                String string = paramArray[i];  
                int index = string.indexOf("=");  
                if (index > 0) {  
                    String name = string.substring(0, index);  
                    String value = string.substring(index + 1, string.length());  
                    parameters.put(name, value);  
                }  
            }  
        }  
  
        return HttpRequestUtil.doGet(queryUrl, parameters, recvEncoding);  
    }  
  
    /** 
     * <pre> 
     * 发送带参数的POST的HTTP请求 
     * </pre> 
     *  
     * @param reqUrl 
     *            HTTP请求URL 
     * @param parameters 
     *            参数映射表 
     * @return HTTP响应的字符串 
     */  
    @SuppressWarnings("unchecked")  
    public static String doPost(String reqUrl, Map parameters,
            String recvEncoding) {
        setRequestEncoding(recvEncoding);

        HttpURLConnection url_con = null;  
        String responseContent = null;  
        try {  
            StringBuffer params = new StringBuffer();  
            for (Iterator iter = parameters.entrySet().iterator(); iter  
                    .hasNext();) {  
                Entry element = (Entry) iter.next();  
                params.append(element.getKey().toString());  
                params.append("=");  
                params.append(URLEncoder.encode(element.getValue().toString(),  
                        HttpRequestUtil.getRequestEncoding()));
                params.append("&");  
            }  
  
            if (params.length() > 0) {  
                params = params.deleteCharAt(params.length() - 1);  
            }  
  
            URL url = new URL(reqUrl);  
            url_con = (HttpURLConnection) url.openConnection();  
            url_con.setRequestMethod("POST");  
  
            url_con.setConnectTimeout(HttpRequestUtil.getConnectTimeOut());  
            url_con.setReadTimeout(HttpRequestUtil.getReadTimeOut());  
  
            url_con.setDoOutput(true);  
            byte[] b = params.toString().getBytes();  
            url_con.getOutputStream().write(b, 0, b.length);  
            url_con.getOutputStream().flush();  
            url_con.getOutputStream().close();  
  
            InputStream in = url_con.getInputStream();  
            BufferedReader rd = new BufferedReader(new InputStreamReader(in,  
                    recvEncoding));  
            String tempLine = rd.readLine();  
            StringBuffer tempStr = new StringBuffer();  
            String crlf = System.getProperty("line.separator");  
            while (tempLine != null) {  
                tempStr.append(tempLine);  
                tempStr.append(crlf);  
                tempLine = rd.readLine();  
            }  
            responseContent = tempStr.toString();  
            rd.close();  
            in.close();  
        } catch (IOException e) {  
            logger.error("链接SMS Server 失败。 网络不稳定，或者网络已断开");  
        } finally {  
            if (url_con != null) {  
                url_con.disconnect();  
            }  
        }  
        return responseContent;  
    }  
  
    /** 
     * @return 连接超时(毫秒) 
     */  
    public static int getConnectTimeOut() {  
        return HttpRequestUtil.connectTimeOut;  
    }  
  
    /** 
     * @return 读取数据超时(毫秒) 
     */  
    public static int getReadTimeOut() {  
        return HttpRequestUtil.readTimeOut;  
    }  
  
    /** 
     * @return 请求编码 
     */  
    public static String getRequestEncoding() {  
        return requestEncoding;  
    }  
  
    /** 
     * @param connectTimeOut 
     *            连接超时(毫秒) 
     */  
    public static void setConnectTimeOut(int connectTimeOut) {  
        HttpRequestUtil.connectTimeOut = connectTimeOut;  
    }  
  
    /** 
     * @param readTimeOut 
     *            读取数据超时(毫秒) 
     */  
    public static void setReadTimeOut(int readTimeOut) {  
        HttpRequestUtil.readTimeOut = readTimeOut;  
    }  
  
    /** 
     * @param requestEncoding 
     *            请求编码 
     */  
    public static void setRequestEncoding(String requestEncoding) {  
        HttpRequestUtil.requestEncoding = requestEncoding;  
    }  

}
