package com.example.a1;

import com.sun.glass.ui.Accessible;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static java.lang.System.out;

@WebServlet(name = "PollManagerServlet", value = "/Poll-Manager-Servlet")
public class PollManagerServlet extends HttpServlet {
    private String message;
    private User pm;

    public void init() {
        message = "Welcome Admin!";
        User pm = new User();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void service(){

    }

    public void destroy(){out.println("servlet is destroyed");}
}