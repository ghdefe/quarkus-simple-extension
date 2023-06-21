package org.acme.greeting.extension.runtime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @Author 曾春苗
 * @Date 2023/6/20 17:48
 */
public class GreetingExtensionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hello from doGet");
        resp.getWriter().write("Hello");
    }
}
