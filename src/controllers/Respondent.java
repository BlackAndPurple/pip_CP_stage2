package controllers;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    default Date parseDate(String stringDate){
        Date date = null;
        if ((stringDate != null) && !stringDate.equals("")) {
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            try {
                date = format.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

}
