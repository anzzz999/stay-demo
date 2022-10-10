package test;

import cn.hutool.json.JSONUtil;

import java.util.Map;

/**
 * @Author: zhanmingwei
 */
public class JsonTest {


    public void jsonChange(){
        String json = "{\n" +
                "\"type\":1,\"campId\" :  \"${campId}\",\n" +
                "\"key\" :  \"${campId}\",\n" +
                "\"params\" :  \"[${campName}]\"\n" +
                "}";
        Map map = JSONUtil.toBean(json, Map.class);
//        map.put("title")


        String template = "{\n" +
                "\t\"version\": \"v1\",\n" +
                "\t\"isImVisible\": \"1\",\n" +
                "\t\"timestamp\": 123456789,\n" +
                "\t\"message\": {\n" +
                "\t\t\"title\": \"msg\",\n" +
                "\t\t\"msgContent\": {\n" +
                "\t\t\t\"msg\": \"哈哈哈\"\n" +
                "\t\t},\n" +
                "\t\t\"contentType\": \"text|picture...\"\n" +
                "\t},\n" +
                "\t\"callback\": {\n" +
                "\t\t\"action\": \"jump\",\n" +
                "\t\t\"type\": \"非必传 0\",\n" +
                "\t\t\"campId\": \"非必传\",\n" +
                "\t\t\"params\": {}\n" +
                "\t}\n" +
                "}";
    }
}
