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
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Janardhn Randhi
 */
public class logcheck extends HttpServlet {

  
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("username");
        
        String pass = request.getParameter("passowrd");
         try{
       ResultSet rs=null;
             //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jana", "root", "admin");
            Statement st=con.createStatement();
            
            rs=st.executeQuery("select * from register where email='"+email+"' and pass='"+pass+"'");
            if(rs.next())
            {
                System.out.println("succuessfully entered username and password");
               response.sendRedirect("dashboard.html"); 
            }
            
            else{
                System.out.println("you are not authenticated user");
                response.sendRedirect("register.html"); 
            }

    }catch (Exception se) {
            se.printStackTrace();
        }


    }

  
}
