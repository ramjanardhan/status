/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.statusreporting;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class pdfgenerate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String start = request.getParameter("date1");
            String end = request.getParameter("date2");
            ResultSet rs = null;

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jana", "root", "admin");
            Statement st = con.createStatement();
            /* Define the SQL query */
            PreparedStatement ps = con.prepareStatement("SELECT date,status FROM status WHERE date BETWEEN ? AND ?");
            ps.setString(1, start);
            ps.setString(2, end);
            rs = ps.executeQuery();
            /* Step-2: Initialize PDF documents - logical objects */
            Document my_pdf_report = new Document();
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream("weekly.pdf"));
            my_pdf_report.open();
            //we have four columns in our table
            PdfPTable my_report_table = new PdfPTable(2);
            //create a cell object
            PdfPCell table_cell;

            while (rs.next()) {
                String dept_id = rs.getString("date");
                table_cell = new PdfPCell(new Phrase(dept_id));
                my_report_table.addCell(table_cell);
                String dept_name = rs.getString("status");
                table_cell = new PdfPCell(new Phrase(dept_name));
                my_report_table.addCell(table_cell);
 
            }
            /* Attach report table to PDF */
            my_pdf_report.add(my_report_table);
           System.out.println("pdf generated successdully");
            my_pdf_report.close();
            st.close(); 
            ps.close();
          con.close();
          
            /* Close all DB related objects */
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
}
