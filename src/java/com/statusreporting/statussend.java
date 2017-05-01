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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Janardhn Randhi
 */
public class statussend extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out = response.getWriter();
         response.setContentType("text/html;charset=UTF-8");
         String date=request.getParameter("date");
         String data=request.getParameter("comment");
         System.out.println(date);
         System.out.println(data);
         
          try{
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jana", "root", "admin");

            PreparedStatement ps = con.prepareStatement("insert into status values(?,?)");

            ps.setString(1, date);
             ps.setString(2, data);
          
             
                
            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("<html>");
                out.println("<body>");
                out.println("<h4>");
                
         out.println("<table align=center border=1>");   
                out.println("You are sucessfully entered");
                out.println("</table>");
                 out.println("</h4>");
                out.println("</html>");
                out.println("</body>");
            
            }

        } catch (Exception se) {
            se.printStackTrace();
            
        }
         
    }    
          
    }

   


