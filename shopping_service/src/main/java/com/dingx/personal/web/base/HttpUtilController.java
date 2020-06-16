package com.dingx.personal.web.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@Controller
public class HttpUtilController {
    /**
     * 调用外部接口方法
     *
     * @param methUrl 接口地址
     * @param object  参数对象（单参数）
     * @param <T>
     * @return JSON对象
     */
    public static <T> JSONObject httpPostRequestMethod(String methUrl, T object) {
        HttpHeaders headers = new HttpHeaders();
        //设置Content-Type为application/json
        headers.setContentType(MediaType.APPLICATION_JSON);
        //若要使用postForEntity()方法，使用MultiValueMap对象较为稳健
        LinkedHashMap<String, String> map = JSON.parseObject(JSON.toJSONString(object),new TypeReference<LinkedHashMap<String, String>>(){});
        HttpEntity<LinkedHashMap<String, String>> linkedMap = new HttpEntity(map, headers);
        ResponseEntity<JSONObject> response = new RestTemplate().postForEntity(methUrl, linkedMap, JSONObject.class);
        JSONObject resultJ = response.getBody();
        return resultJ;
    }

    /**
     * 调用外部接口方法
     *
     * @param methUrl 接口地址
     * @param object  参数对象（单参数）
     * @param <T>
     * @return JSON对象
     */
    public static <T> JSONObject httpGetRequestMethod(String methUrl, T object) {
        HttpHeaders headers = new HttpHeaders();
        //设置Content-Type为application/json
        headers.setContentType(MediaType.APPLICATION_JSON);
        //若要使用parseObject()方法，使用MultiValueMap对象较为稳健
        LinkedHashMap<String, String> map = null;
        //拼接一下url
        if(object != null){
            map = JSON.parseObject(JSON.toJSONString(object), new TypeReference<LinkedHashMap<String, String>>(){});
            StringBuffer url = new StringBuffer(methUrl);
            int i = 0;
            for(String key : map.keySet()){
                if(i == 0) {
                    url.append("?");
                } else {
                    url.append("&");
                }
                url.append(key)
                        .append("={")
                        .append(key)
                        .append("}");
                i++;
            }
            methUrl = url.toString();
        }
//        ResponseEntity<JSONObject> response = new RestTemplate().getForEntity(methUrl, JSONObject.class,map);
//        JSONObject resultJ = response.getBody();

        JSONObject resultJ = new RestTemplate().getForObject(methUrl, JSONObject.class,map);
        return resultJ;
    }
}
