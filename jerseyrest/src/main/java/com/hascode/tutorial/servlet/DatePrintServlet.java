package com.hascode.tutorial.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DatePrintServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(final HttpServletRequest req,
                         final HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        res.setCharacterEncoding("utf-8");

        res.getWriter()
                .append(String.format("It's %s now\n\n\n\nwww.hascode.com\nпривет Вад!!!!",
                        new Date()));

        System.out.println("qqq2");
    }
}