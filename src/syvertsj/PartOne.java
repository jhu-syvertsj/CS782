package syvertsj;

// Tomcat7 / Servlets 3.0 / JSP 2.2

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.util.*;

/** 
 * HW02: Part1 
 * Description: 
 * Using counter and redirection. First two hits are redirected to 
 * google, later ones redirected to yahoo. This gets reset when the
 * server is restarted.
 */


@WebServlet("/part-one")
public class PartOne extends HttpServlet {

  // shared by all instances of servlet:
  static int count = 0;

  @Override
  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {

    synchronized(this) {
      count++;
      String userAgent = request.getHeader("User-Agent");
      if ( (userAgent != null) && (count < 3) ) { 
        response.sendRedirect("http://www.google.com");
      } else { 
        response.sendRedirect("http://www.yahooo.com");
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
