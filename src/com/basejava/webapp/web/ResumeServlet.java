package com.basejava.webapp.web;

import com.basejava.webapp.Config;
import com.basejava.webapp.model.*;
import com.basejava.webapp.storage.Storage;
import com.basejava.webapp.util.DateUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ResumeServlet extends HttpServlet {
    private Storage storage;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        storage = Config.get().getStorage();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        Resume resume;
        if (isUuidExists(uuid)) {
            resume = storage.get(uuid);
            resume.setFullName(fullName);
        } else {
            resume = new Resume(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                resume.putContact(type, value);
            } else {
                resume.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                switch (type.name()) {
                    case "PERSONAL":
                    case "OBJECTIVE":
                        resume.putSection(type, new TextSection(value));
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATIONS":
                        resume.putSection(type, new ListSection(value.trim().split("\n")));
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        int amount = request.getParameterValues(type.name()).length;
                        List<Organization> organizations = new ArrayList<>();
                        for (int index = 0; index < amount; index++) {
                            String name = request.getParameterValues(type.name())[index];
                            if (!name.trim().equals("")) {
                                String url = request.getParameter(type.name() + "url");
                                LocalDate startDate = DateUtil.parse(request.getParameter(type.name() + index + "startDate").trim());
                                LocalDate endDate = DateUtil.parse(request.getParameter(type.name() + index + "endDate").trim());
                                String title = request.getParameter(type.name() + index + "title");
                                String description = request.getParameter(type.name() + index + "description");
                                Organization.Position position = new Organization.Position(startDate, endDate, title, description);
                                organizations.add(new Organization(name, url, position));
                            }
                        }
                        resume.putSection(type, new OrganizationSection(organizations));
                }
            } else {
                resume.removeSection(type);
            }
        }

        if (isUuidExists(uuid)) {
            storage.update(resume);
        } else {
            storage.save(resume);
        }
        response.sendRedirect("resume");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume resume;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                resume = storage.get(uuid);
                break;
            case "add":
                resume = new Resume();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", resume);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    private boolean isUuidExists(String uuid) {
        return uuid != null && !uuid.equals("");
    }

}