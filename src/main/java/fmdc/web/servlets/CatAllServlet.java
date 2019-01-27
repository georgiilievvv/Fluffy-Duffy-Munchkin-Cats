package fmdc.web.servlets;

import fmdc.web.domain.entities.Cat;
import fmdc.web.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet {
    private static final String CATS_ALL_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\FMDCats\\src\\main\\resources\\views\\cat-all.html";

    private final FileUtil fileUtil;

    @Inject
    public CatAllServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String view = fileUtil.readFile(CATS_ALL_FILE_PATH);
        PrintWriter writer = resp.getWriter();
        String html;

        if (req.getSession().getAttribute("cats") == null) {
           html = view.replace("{{text}}",
                    "There are no cats. <a href=\"/cats/create\">Create some!</a>");
        } else {

            StringBuilder sb = new StringBuilder();

            ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                    .values()
                    .forEach(c -> sb.append(
                            String.format(
                                    "<a href=\"/cats/profile?catName=%s\">%s</a><br />",
                                    c.getName(), c.getName())));

           html = view.replace("{{text}}", sb.toString());
        }


        writer.println(html);
    }

}
