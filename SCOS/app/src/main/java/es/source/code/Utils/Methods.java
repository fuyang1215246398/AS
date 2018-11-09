package es.source.code.Utils;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.res.AssetManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import com.google.gson.Gson;
import es.source.code.model.Food;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import es.source.code.model.*;



public class Methods {

    private static final String TAG = "Methods";

    public static String streamToString(InputStream is) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            is.close();
            Log.d(TAG, "Json test outputStream.size() = " + outputStream.size());
            byte[] byteArray = outputStream.toByteArray();
            return new String(byteArray);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return null;
        }
    }


    public static Document streamToXml(InputStream is) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(is);
    }
    public static Xml getResultFromXml(Document document) {
        Xml xml = new Xml();

        Element result = document.getDocumentElement();

        // 获取该节点下面的所有子节点
        NodeList resultChildNodes = result.getChildNodes();
        Log.d(TAG, "resultChildNodes.getLength() = " + resultChildNodes.getLength());
        //把节点转换成元素节点
        Element resultCodeElement = (Element) resultChildNodes.item(0);
        Element messageElement = (Element) resultChildNodes.item(1);
        xml.setRESULTCODE(Integer.valueOf(resultCodeElement.getTextContent()));
        xml.setMsg(messageElement.getTextContent());

        // 计时开始
        Date startDate = new Date(System.currentTimeMillis());
        // 开始解析列表
        Element dataStringElement = (Element) resultChildNodes.item(2);
        NodeList foodElemList = dataStringElement.getElementsByTagName(Const.ELEMENT_ID.FOOD);
        List<Food> foodList = new ArrayList<>();
        // 遍历food结点
        for (int i = 0; i < foodElemList.getLength(); i++) {
            Element foodElement = (Element) foodElemList.item(i);
            // 遍历food内容
            NodeList foodParams = foodElement.getChildNodes();
            Food food = new Food();
            for (int j = 0; j < foodParams.getLength(); j++) {
                Element param = (Element) foodParams.item(j);
                switch (param.getTagName()) {
                    case Const.ELEMENT_ID.FOOD_NAME:
                        food.setFoodName(param.getTextContent());
                        break;
                    case Const.ELEMENT_ID.PRICE:
                        food.setPrice(Integer.valueOf(param.getTextContent()));
                        break;
                    case Const.ELEMENT_ID.STORE:
                        food.setStore(Integer.valueOf(param.getTextContent()));
                        break;
                    case Const.ELEMENT_ID.ORDER:
                        food.setOrder(Boolean.valueOf(param.getTextContent()));
                        break;
                    case Const.ELEMENT_ID.IMGID:
                        food.setImgId(Integer.valueOf(param.getTextContent()));
                        break;
                }
            }
            foodList.add(food);
        }
        Date endDate = new Date(System.currentTimeMillis());
        long duration = endDate.getTime() - startDate.getTime();
        Log.d(TAG, "Xml test parse time = " + String.valueOf(duration) + "ms , size = " + String
                .valueOf(foodList.size()));

        xml.setDataList(foodList);
        return xml;
    }
    public static InputStream connectServerToUpdateFood(String param, String urlString, String contentType) {
        try {
            String postUrl = Const.URL.BASE + urlString;
            // 新建一个URL对象
            URL url = new URL(postUrl);
            // 打开一个HttpURLConnection连接
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            // 设置连接主机超时时间
            urlConn.setConnectTimeout(5 * 1000);
            //设置从主机读取数据超时
            urlConn.setReadTimeout(5 * 1000);
            // 设置是否使用缓存  默认是true
            urlConn.setUseCaches(true);
            // 设置为Post请求
            urlConn.setRequestMethod("POST");
           urlConn.setRequestProperty("Content-Type", contentType);
            urlConn.addRequestProperty("Accept-Encoding", "identity");
//            urlConn.setRequestProperty("charset", "utf-8");
            //设置客户端与服务连接类型
            urlConn.addRequestProperty("Connection", "Keep-Alive");
            // 开始连接
            urlConn.connect();
            // 判断请求是否成功
            if (urlConn.getResponseCode() == 200) {
                // 统计Xml输入流长度
                if( contentType.startsWith("t")) {
                    Log.d("Methods", "Xml test，urlConn.getContentLength() = " + urlConn.getContentLength());
                }
                // 返回输入流以便于分情况处理
                return urlConn.getInputStream();
            } else {
                Log.d(TAG, "Get方式请求失败");
            }
            // 关闭连接
            urlConn.disconnect();
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return null;
    }
}
