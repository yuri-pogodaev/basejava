<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.SectionType" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="static com.basejava.webapp.util.DateUtil.NOW" %>
<%@ page import="com.basejava.webapp.model.TextSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" class="com.basejava.webapp.model.Section"/>
            <dl>
                <dt>${type.title}</dt>
                <c:choose>
                    <c:when test="${(type == 'PERSONAL') || (type == 'OBJECTIVE')}">
                        <dd>

                            <input type="text" name="${type.name()}" size=30
                            <c:if test="${resume.getSection(type) != null}">
                                   value="<%=((TextSection)section).getContent()%>"
                            </c:if>>
                        </dd>
                    </c:when>
                    <c:when test="${(type == 'ACHIEVEMENT') || (type == 'QUALIFICATIONS')}">
                        <dd>
                            <c:if test="${resume.getSection(type) != null}">
                                <textarea name='${type}' cols=75
                                          rows=5><%=String.join("\n", ((ListSection) section).getPart())%></textarea>
                            </c:if>
                            <c:if test="${resume.getSection(type) == null}">
                                <textarea name='${type}' cols=75 rows=5></textarea>
                            </c:if>
                        </dd>
                    </c:when>
                    <c:when test="${(type == 'EXPERIENCE') || (type == 'EDUCATION')}">
                        <dd>
                            <c:if test="${resume.getSection(type) != null}">
                                <c:set var="organizationSection" value="${section}"/>
                                <jsp:useBean id="organizationSection"
                                             type="com.basejava.webapp.model.OrganizationSection"/>
                                <c:forEach var="organization" items="${organizationSection.organizations}"
                                           varStatus="counter">
                                    Название учреждения:<input type="text" name="${type}" value="${organization.link.name}">
                                    Сайт учреждения:<input type="text" name="${type}url" value="${organization.link.url}">
                                    <BR>
                                    <c:forEach var="position" items="${organization.positions}">
                                        <jsp:useBean id="position" type="com.basejava.webapp.model.Organization.Position"/>
                                        Начальная дата:<input type="text" name="${type}${counter.index}startDate"
                                        value="<%= position.getStartDate().format(DateTimeFormatter.ofPattern("MM/yyyy")) %>"
                                        placeholder="MM/yyyy"><BR>
                                        Конечная дата:<input type="text" name="${type}${counter.index}endDate"
                                        value="<%= position.getFinalDate().equals(NOW) ? "Сейчас" :
                                            position.getFinalDate().format(DateTimeFormatter.ofPattern("MM/yyyy"))%>"
                                        placeholder="MM/yyyy"><BR>
                                        Должность:<input type="text" name="${type}${counter.index}title" value="${position.title}"/><BR>
                                        Описание:<BR><textarea name="${type}${counter.index}description" cols=75 rows=5>${position.description}</textarea><BR>
                                    </c:forEach>
                                    <HR>
                                    <BR>
                                </c:forEach>
                                <c:set var="begin" value="${organizationSection.organizations.size()}"/>
                                <c:set var="end" value="${organizationSection.organizations.size()}"/>
                            </c:if>
                            <c:if test="${resume.getSection(type) == null}">
                                <c:set var="begin" value="0"/>
                                <c:set var="end" value="1"/>
                            </c:if>
                            <c:forEach begin="${begin}" end="${end}" varStatus="counter">
                                Название учреждения:<input type="text" name="${type}">
                                Сайт учреждения:<input type="text" name="${type}url">
                                <BR>
                                Начальная дата:<input type="text" name="${type}${counter.index}startDate" placeholder="MM/yyyy"><BR>
                                Конечная дата:<input type="text" name="${type}${counter.index}endDate" placeholder="MM/yyyy"><BR>
                                Должность:<input type="text" name="${type}${counter.index}title"/><BR>
                                Описание:<BR><textarea name="${type}${counter.index}description" cols=75 rows=5></textarea><BR>
                                <HR>
                                <BR>
                            </c:forEach>
                        </dd>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button type="reset" onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>