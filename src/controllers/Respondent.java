package controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public interface Respondent {

    default void responseBlank(HttpServletResponse resp, String content) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>" + "<head><title>result</title>" +
                "</head><body >");
        out.println("<a href=\"../\">Back</a><br>");
        out.println(content);
        out.println("</body></html>");
        out.close();
    }

}
