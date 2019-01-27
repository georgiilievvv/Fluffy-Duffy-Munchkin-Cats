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
import java.util.Map;


@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {

    private static final String CATS_PROFILE_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\FMDCats\\src\\main\\resources\\views\\cat-profile.html";
    private static final String CATS_PROFILE_NOT_FOUND_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\FMDCats\\src\\main\\resources\\views\\cat-not-found.html";

    private final FileUtil fileUtil;

    @Inject
    public CatProfileServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);

        String html;

        if (cat == null) {
            html = this.fileUtil.readFile(CATS_PROFILE_NOT_FOUND_FILE_PATH)
                    .replace("{{name}}", req.getQueryString().split("=")[1]);
        } else {

            html = this.fileUtil.readFile(CATS_PROFILE_FILE_PATH)
                    .replace("{{name}}", cat.getName())
                    .replace("{{age}}", cat.getAge().toString())
                    .replace("{{color}}", cat.getColor())
                    .replace("{{breed}}", cat.getBreed());

        }
        PrintWriter writer = resp.getWriter();
        writer.println(html);
    }
}
