package esd.scos.servlet;

import com.google.gson.Gson;
import esd.scos.model.Food;
import esd.scos.model.Result;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.List;

import esd.scos.method.Methods;
import esd.scos.model.*;
public class FoodUpdateService extends javax.servlet.http.HttpServlet {

    private static final int TYPE_JSON = 1;
    private static final int TYPE_XML = 2;
    private static int Type;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {

        // 判断发送数据内容
    	if(request.getContentType().equals("text/xml"))Type=TYPE_XML;
    	else Type =TYPE_JSON;

        request.setCharacterEncoding("utf-8");

        List<Food> foodList = Methods.updateFoodList(100);

       if (Type == TYPE_JSON) {
            // send json
            String foodListJson = new Gson().toJson(foodList);
            response.setContentType("application/json;charset=utf-8");
            Result resultBody = new Result(1, "请求成功", foodListJson);
            String resultJson = new Gson().toJson(resultBody);
           // response.setContentLength(resultJson.length());
            Methods.putDataToResponse(response, resultJson);
       } 
            else if (Type == TYPE_XML) {
            // send xml
            Element dataString = Methods.parseFoodListToXml(foodList);
            response.setContentType("text/xml; charset=utf-8");
            Document foodDoc = DocumentHelper.createDocument();
            Element result = foodDoc.addElement("Result");
            Element resultCode = result.addElement("RESULTCODE");
            resultCode.addText("1");
            Element message = result.addElement("message");
            message.addText("请求成功");
            result.add(dataString);
            response.setContentLength(1180138);
            Methods.putDataToResponse(response, foodDoc.asXML());
           
        }
    }
}
