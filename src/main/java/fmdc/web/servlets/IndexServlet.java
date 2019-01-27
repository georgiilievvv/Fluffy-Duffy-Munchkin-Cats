package fmdc.web.servlets;

import fmdc.web.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class IndexServlet extends HttpServlet {

    private static final String CATS_INDEX_FILE_PATH = "C:\\Users\\joroi\\Desktop\\SoftUni\\JavaWeb\\FMDCats\\src\\main\\resources\\views\\index.html";

    private final FileUtil fileUtil;

    @Inject
    public IndexServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String view = fileUtil.readFile(CATS_INDEX_FILE_PATH);
        PrintWriter writer = resp.getWriter();
        writer.println(view);
    }
}
