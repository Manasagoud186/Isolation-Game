import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class servletdemo extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
        throws ServletException, IOException {

        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        pw.println("<html><head><title>servlet1</title></head>");
        pw.println("<body><h1>Hello World</h1></body></html>");

        pw.close();
    }
}