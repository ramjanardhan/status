/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statusreporting;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Janardhn Randhi
 */
public class getdata extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String start = request.getParameter("date1");
        String end = request.getParameter("date2");
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jana", "root", "admin");

            PreparedStatement ps = con.prepareStatement("SELECT date,status FROM status WHERE date BETWEEN ? AND ?");
            // PreparedStatement ps = con.prepareStatement("SELECT * FROM psr");
            ps.setString(1, start);
            ps.setString(2, end);
            rs = ps.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int total = rsmd.getColumnCount();
            out.print("<table  border=1 width=1500 height=50 align=center>");
            out.print("<caption><h2>weekly status</h2></caption>");
            out.print("<tr>");
            for (int i = 1; i <= total; i++) {
                out.print("<th>" + rsmd.getColumnName(i) + "</th>");
            }
            out.print("</tr>");

            while (rs.next()) {
                out.print("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td></tr>");

            }
            out.print("</table>");
            out.println("< a heref=excelsheet.action <input type=button name=GetExcelSheet value=GetExcelSheet /></a>");
            out.println();
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

}
