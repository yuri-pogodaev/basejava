package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.Resume;
import com.basejava.webapp.storage.Storage;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + "!");
        PrintWriter writer = response.getWriter();
        writer.println("<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "<title>Список всех резюме.</title>\n" +
                "<link rel=\"stylesheet\" href=\"css/style.css\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<table>\n" +
                "<caption>Список резюме</caption>\n" +
                "<tr>\n" + "<th>UUID</th>\n" +
                "<th>Full_Name</th>\n" +
                "</tr>\n");
        for (Resume r : storage.getAllSorted()) {
            writer.println("<tr>\n");
            writer.println("<td>" + r.getUuid() + "</td>\n");
            writer.println("<td>" + r.getFullName() + "</td>\n");
            writer.println("</tr>\n");
        }
        writer.println("</table>\n" +
                "</body>\n" +
                "</html>\n");
    }
}