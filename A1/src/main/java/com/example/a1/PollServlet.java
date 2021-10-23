package com.example.a1;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

@WebServlet(name = "PollServlet", value = "/Poll-Servlet")
public class PollServlet extends HttpServlet {

    public void init() {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next_page = "/PollManager.jsp";
        if(request.getParameter("CreatePoll") != null) {
            
            int numChoices = Integer.parseInt(request.getParameter("numChoices"));
            Hashtable<Choice, Integer> choices = new Hashtable();
            for (int i = 0; i < numChoices; i++) {
                Choice choice = new Choice(String.valueOf(i), "");
                choices.put(choice, 0);
            }

            try {
                ((User) request.getSession().getAttribute("user")).createPoll(request.getParameter("name"), request.getParameter("question"), choices);
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
        }
        if(request.getParameter("UpdatePoll") != null) {
            if (request.getParameter("newName") == null) {
            } else if (((String) request.getParameter("newName")).length() > 1) {
                System.out.println(((String) request.getParameter("newName")));
                ((User) request.getSession().getAttribute("User")).getPoll().setName((String) request.getParameter("newName"));
            }
            if (request.getParameter("newQuestion") == null) {
            } else if (((String) request.getParameter("newQuestion")).length() > 0) {
                ((User) request.getSession().getAttribute("User")).getPoll().setQuestion((String) request.getParameter("newQuestion"));
            }
        }
        if(request.getParameter("UpdatePollChoices") != null) {

            // Iterating through the Hashtable
            // object using for-Each loop
            Hashtable<Choice, Integer> ht = new Hashtable();
            ((User) request.getSession().getAttribute("User")).getPoll().getChoices().forEach((key, value) -> {
                if (((String) request.getParameter(key.getText())).length() > 0) {
                    Choice c = new Choice(request.getParameter(key.getText()), "");
                    //((User) request.getSession().getAttribute("User")).getPoll().getChoices().remove(key);
                    ht.put(c, 0);
                    //((User) request.getSession().getAttribute("User")).getPoll().getChoices().put(c, 0);
                    System.out.println(c);
                }
                else {
                    Choice c = key;
                    ht.put(c,0);
                }
            });
            ((User) request.getSession().getAttribute("User")).getPoll().setChoices(ht);
        }
        if(request.getParameter("ClearPoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).clearPoll();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
            System.out.println(((User) request.getSession().getAttribute("user")).getPoll().getStatus());
        }
        if(request.getParameter("ClosePoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).closePoll();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
            System.out.println("Poll successfully closed");
        }
        if(request.getParameter("RunPoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).runPoll();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
            System.out.println(((User) request.getSession().getAttribute("user")).getPoll().getStatus());
        }
        if(request.getParameter("ReleasePoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).releasePoll();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
            System.out.println(((User) request.getSession().getAttribute("user")).getPoll().getStatus());
        }
        if(request.getParameter("UnreleasePoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).unreleasePoll();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
            System.out.println(((User) request.getSession().getAttribute("user")).getPoll().getStatus());
        }

        response.sendRedirect(request.getContextPath() + next_page);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String next_page = "/index.jsp";
        if(request.getParameter("VotePoll") != null){
            Choice c = new Choice(request.getParameter("UserVote"),"");
            try {
                ((User) request.getSession().getAttribute("user")).vote(c);
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
        }
        if(request.getParameter("GetPoll") != null){
            try {
                ((User) request.getSession().getAttribute("user")).getPollResults();
                next_page = "/ViewResults.jsp";
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }
        }
        if(request.getParameter("DownloadPoll") != null){
            String filename = ((User)request.getSession().getAttribute("User")).getPoll().getName()+"-"+((User)request.getSession().getAttribute("User")).getPoll().getReleaseDateTime()+"out.txt";
            PrintWriter out = response.getWriter();
            try {
                String str = ((User)request.getSession().getAttribute("User")).downloadPollDetails(out, filename);
                response.setContentType("text/plain");
                response.setHeader("Content-disposition", "attachment; filename="+str);
                out.flush();
                out.close();
            } catch (PollException e) {
                e.printStackTrace();
                request.setAttribute("error", e);
                next_page = "/ErrorHandler.jsp";
            }

        }
        response.sendRedirect(request.getContextPath() + next_page);
    }
}
