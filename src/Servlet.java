import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Servlet extends HttpServlet {
    static final Map<String, String> db = new TreeMap<String, String>();
    static Map<String, Integer> counter = new HashMap<String, Integer>();
    private String pattern = "<html> %s </html>";

    static {
        char[] temp = {'a', 'b', 'c'};
        for (char l : temp) {
            for (int i = 1; i < 4; i++)
                counter.put(String.valueOf(l) + i, 0);
        }

        db.put("a1", "Kyiv");
        db.put("a2", "Odessa");
        db.put("a3", "Kharkiv");
        db.put("b1", "Los-Angeles");
        db.put("b2", "New York");
        db.put("b3", "Washington");
        db.put("c1", "Milan");
        db.put("c2", "Rome");
        db.put("c3", "Neapol");

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] answer = {req.getParameter("question1"), req.getParameter("question2"), req.getParameter("question3")};

        for (String s : answer) {
            counter.put(s, counter.get(s) + 1);
        }

        StringBuilder result = new StringBuilder("<b>Statistics of answers: </b><br>");
        for (String key : db.keySet()) {
            result.append(db.get(key) + " :" + counter.get(key) + "<br>");
        }
        resp.getWriter().print(String.format(pattern, result.toString()));
    }
}
