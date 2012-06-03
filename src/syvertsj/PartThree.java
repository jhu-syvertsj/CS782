package syvertsj;

// Tomcat7 / Servlets 3.0 / JSP 2.2

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/** 
 * HW02: Part3 
 * Browser detection: detect IE and warn user
 */

@WebServlet("/part-three")
public class PartThree extends HttpServlet {

  // shared by all instances of servlet:
  static int countTotal = 0;
  static int countIE    = 0;

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    synchronized(this) {
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title, message;

      String userAgent = request.getHeader("user-agent");
      System.out.println(" user-agent: " + userAgent); 
      
      if (userAgent != null) {
        countTotal++;
        if (userAgent.contains("MSIE")) {
          response.setStatus(503);
    	  countIE++;
          title = "Pathetic Microsoft Minion";
          message = "IE has crashed! Use Firefox next time!\n" +
                     countIE + " foolish people have accessed this website with IE so far.";
          response.sendError(response.getStatus(), message);
        } else {
          if (userAgent.contains("Firefox")) {
             title = "Firefox Browser";
             int status = response.getStatus();
             message = "Status: " + status + " \n";
             message += "You are using Firefox browser.\n" +
                        (countTotal - countIE) + " people have accessed this website so far.";
          } else if (userAgent.contains("Chrome")) {
             title = "Chrome Browser";
             int status = response.getStatus();
             message = "Status: " + status + " \n";
             message += "You are using Chrome browser.\n" +
                        (countTotal - countIE) + " people have accessed this website so far.";
          } else {
             title = "Non IE Browser";
             int status = response.getStatus();
             message = "Status: " + status + " \n";
             message += "You are using a non IE browser.\n" +
                        (countTotal - countIE) + " people have accessed this website so far.";
          }
          
          String docType =
          "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 " +
          "Transitional//EN\">\n";
          out.println(docType +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n" +
                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
                message + "\n" +
                "</BODY></HTML>");
        }
      }
    }
  }
  
  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}
  