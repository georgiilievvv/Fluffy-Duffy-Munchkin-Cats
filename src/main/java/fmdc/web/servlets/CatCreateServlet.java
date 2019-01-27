package fmdc.web.servlets;

import fmdc.web.domain.entities.Cat;
import fmdc.web.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {

    private static final String CATS_CREATE_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\FMDCats\\src\\main\\resources\\views\\cat-create.html";

    private final FileUtil fileUtil;

    @Inject
    public CatCreateServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String view = fileUtil.readFile(CATS_CREATE_FILE_PATH);
        PrintWriter writer = resp.getWriter();
        writer.println(view);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cat cat = new Cat();

        cat.setName(req.getParameter("name"));
        cat.setBreed(req.getParameter("breed"));
        cat.setColor(req.getParameter("color"));
        cat.setAge(Integer.parseInt(req.getParameter("age")));

        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new LinkedHashMap<>());
        }

        ((Map<String, Cat>)req.getSession()
                .getAttribute("cats")).putIfAbsent(cat.getName(), cat);

        resp.sendRedirect("/cats/profile?catName=" + cat.getName());
    }
}
