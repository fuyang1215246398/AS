package esd.scos.method;

import esd.scos.model.Food;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import javax.servlet.http.HttpServletResponse;

public class Methods {

    // 添加字符串到输出流中
    public static void putDataToResponse(HttpServletResponse response, String jsonValue) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsonValue);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static boolean isNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }

    // 获取食物列表
    public static List<Food> updateFoodList(int amount) {
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            foodList.add(new Food("青椒肉丝", 20, 30, false, 0));
        }
        return foodList;
    }

    // 获取食物列表
    public static Element parseFoodListToXml(List<Food> foodList) {
        Document foodDoc = DocumentHelper.createDocument();
        Element dataString = foodDoc.addElement("dataString");
        for (Food food : foodList) {
            Element foodElement = dataString.addElement("food");
            // 子节点
            Element foodName = foodElement.addElement("foodName");
            foodName.addText(food.getFoodName());
            Element price = foodElement.addElement("price");
            price.addText(String.valueOf(food.getPrice()));
            Element store = foodElement.addElement("store");
            store.addText(String.valueOf(food.getStore()));
            Element order = foodElement.addElement("order");
            order.addText(String.valueOf(food.isOrder()));
            Element imgId = foodElement.addElement("imgId");
            imgId.addText(String.valueOf(food.getImgId()));
        }
        return dataString;
    }
}
