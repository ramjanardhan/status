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
public class regdata extends HttpServlet {

   

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        String firstname = request.getParameter("fname");
        String lastname = request.getParameter("lname");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        String cpass = request.getParameter("cpass");
        String phone = request.getParameter("phone");
        String add = request.getParameter("desc");
        
         //loading drivers for mysql
         try{
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database 
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jana", "root", "admin");

            PreparedStatement ps = con.prepareStatement("insert into register values(?,?,?,?,?,?,?)");

            ps.setString(1, firstname);
             ps.setString(2, lastname);
            ps.setString(3, email);
            ps.setString(4, pass);
             ps.setString(5, cpass);
             ps.setString(6, phone);
             
                ps.setString(7, add);
            int i = ps.executeUpdate();

            if (i > 0) {
                out.println("You are sucessfully registered");
            }

        } catch (Exception se) {
            se.printStackTrace();
        }
    }    

    }

