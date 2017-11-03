import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonResults {

    /**
     * 返回json字符串result
     */
    public static String getJsonResult(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        if (!(obj.get("result").toString()).isEmpty()) {
            return obj.get("result").toString();
        } else {
            return null;
        }

    }

    /**
     * 返回json字符串msg
     */
    public static String getJsonMsg(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        if (!(obj.get("msg")).toString().isEmpty()) {
            return obj.get("msg").toString();
        } else {
            return null;
        }

    }

    /**
     * 返回json字符串data
     */
    public static String getJsonData(String json) {
        JSONObject obj = JSONObject.fromObject(json);
        if (!(obj.get("data")).toString().isEmpty()) {
            return obj.get("data").toString();
        } else {
            return null;
        }

    }


    /**
     * 返回json字符串data
     */
    public static String getJsonDataParam(String json, String paramlist) {
        JSONObject obj = JSONObject.fromObject(json);
        if (!(obj.get(paramlist)).toString().isEmpty()) {
            return obj.get(paramlist).toString();
        } else {
            return null;
        }

    }


    /**
     * 返回json字符串data
     * json json格式字符串
     * paramlist data下的数据
     * param paramlist下的key
     * listCount paramlist个数
     */
    public static String getJsonListPara(String json, String paramlist, int k) {
        String jsono1 = null;
        JSONObject obj = JSONObject.fromObject(json);
        //data下的数据转化成json对象
        JSONObject jdata = JSONObject.fromObject(obj.get("data"));
        if (jdata.get(paramlist) != null) {
            JSONArray jarr = jdata.getJSONArray(paramlist);
            //list对象循环
            for (int i = 0; i < jarr.size(); i++) {
                JSONObject jsono = (JSONObject) jarr.get(i);
                System.out.println("param对应的值:" + jsono);
                jsono1 = (String) jsono.get(k);
            }
        }
        return jsono1;
    }


    /**
     * 返回json字符串中data下param字段对应的值
     * json json格式字符串
     * paramlist data下的数据
     * param paramlist下的key
     * i paramlist的第几个行，从0开始
     */
    public static String getJsonParam(String json, String param) {
        JSONObject jsonObject = JSONObject.fromObject(json);

//        System.out.println("result:" + jsonObject.get("result"));
//        System.out.println("msg:" + jsonObject.get("msg"));
//        System.err.println("data:" + jsonObject.getJSONObject("data"));

        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("identityList");
        JSONObject row = null;
        row = jsonArray.getJSONObject(0);
//        System.out.println("schoolId:" + row.get("schoolId"));
//        System.out.println("jid:" + row.get("jid"));
//        System.out.println("redisIp:" + row.get("redisIp"));
//        System.out.println("netClassUrl:" + row.get("netClassUrl"));
//        System.out.println("userName:" + row.get("userName"));

        if (param.equals("schoolId")) {
            return row.get("schoolId").toString();
        } else if (param.equals("jid")) {
            return row.get("jid").toString();
        } else if (param.equals("userName")) {
            return row.get("userName").toString();
        } else {
            System.out.println(param + "----param参数错误！----");
            return null;
        }
    }
    /**
     * 返回json字符串中对应的值
     * json json格式字符串
     * regex 正则表达式
     */
    public static String getParamByRex(String json, String regex) {

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(json);
        String result = null;
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }
}
