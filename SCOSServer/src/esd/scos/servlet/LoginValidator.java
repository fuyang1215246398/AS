package esd.scos.servlet;

import com.google.gson.Gson;

import esd.scos.method.Methods;
import esd.scos.model.Result;
import esd.scos.model.User;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginValidator extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        StringBuilder Builder = new StringBuilder();
        User user;
        String getLine;
        try {
            //读取输入流到StringBuilder中
            BufferedReader reader = request.getReader();
            while ((getLine = reader.readLine()) != null)
                Builder.append(getLine);
        } catch (Exception e) { /*report an error*/ }

        try {
            Gson gson = new Gson();
            user = gson.fromJson(Builder.toString(), User.class);
        } catch (Exception e) {
            throw new IOException("Error parsing JSON request string");
        }
        Result result = new Result(0, "登陆失败", null);
        if (user != null && Methods.isNotEmpty(user.getUserName()) && Methods.isNotEmpty
                (user.getPassword())) {
            result.setRESULTCODE(1);
            result.setMsg("登录成功");
        }
        String responseString = new Gson().toJson(result);
        Methods.putDataToResponse(response, responseString);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse
            response) throws javax.servlet.ServletException, IOException {
        doPost(request, response);
    }
}
