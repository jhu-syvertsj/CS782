package syvertsj;

// Tomcat7 / Servlets 3.0 / JSP 2.2  

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/** 
 * HW02: Part2
 * Description:
 * Use HttpServletRequest object to extract the "referer" url from the 
 * http header using the "getHeader()" method.
 * The "referer" will be null if the user accesses the url in directly 
 * versus via a link.
 */

@WebServlet("/part-two")
public class PartTwo extends HttpServlet {
  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
	  
    String referer = request.getHeader("referer");
    
    if (referer == null) { // accessed via link
      response.sendRedirect("part-two.html");
    } else {               // accessed directly
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String docType =
      "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
      "Transitional//EN\">\n";
      String title = "Blah Software News!";
      docType +=  "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
                "<TR BGCOLOR=\"#FFAD00\">\n";
      Enumeration<String> paramNames = request.getParameterNames();
      out.println(docType);
    }
  }

  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
