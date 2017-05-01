<%-- 
    Document   : index
    Created on : Apr 29, 2017, 2:25:04 AM
    Author     : Janardhn Randhi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login Page</title>
    </head>
    <body>
        <form action="logcheck" method="post">
            <table border="1" align="center">
                <tr><td>user:</td><td> <input type="text" name="username"  id="username" placeholder="username"/></td></tr>
                
                <tr><td>password:</td>   <td>  <input type="text" name="passowrd"  id="password" placeholder="password"/></td></tr>
                <tr><td colspan="2" align="center"> <input type="button" name="reset" value="reset" id="reset" />
               <input type="submit" name="submit" value="submit"/></td></tr>
              

               
            </table>
        </form>
        <script>
            
        </script>
        
    </body>
</html>
