<%@ page import="com.basejava.webapp.model.TextSection" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="static com.basejava.webapp.util.DateUtil.NOW" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.basejava.webapp.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>

        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.basejava.webapp.model.SectionType, com.basejava.webapp.model.Section>"/>
    <H2><c:out value="${sectionEntry.key.title}"/></H2>
    <c:choose>
        <c:when test="${(sectionEntry.key.name() == 'PERSONAL') || (sectionEntry.key.name() == 'OBJECTIVE')}">
            <%=((TextSection) sectionEntry.getValue()).getContent() %><br/>
        </c:when>

        <c:when test="${(sectionEntry.key.name() == 'ACHIEVEMENT') || (sectionEntry.key.name() == 'QUALIFICATIONS')}">
            <c:set var="listSection" value="${sectionEntry.value}"/>
            <jsp:useBean id="listSection" type="com.basejava.webapp.model.ListSection"/>
            <c:forEach var="item" items="${listSection.part}">
                <LI><c:out value="${item}"/></LI>
            </c:forEach>
        </c:when>

        <c:when test="${(sectionEntry.key.name() == 'EXPERIENCE') || (sectionEntry.key.name() == 'EDUCATION')}">

            <c:set var="organizationSection" value="${sectionEntry.value}"/>
            <jsp:useBean id="organizationSection" type="com.basejava.webapp.model.OrganizationSection"/>
            <c:forEach var="organization" items="${organizationSection.organizations}">

                <a href="${organization.link.url}"><H3>${organization.link.name}</H3></a>

                <TABLE cellspacing="10">
                    <c:forEach var="position" items="${organization.positions}">
                        <jsp:useBean id="position" type="com.basejava.webapp.model.Organization.Position"/>
                        <TR>
                            <TD ROWSPAN="2" valign="top">
                                <%= position.getStartDate().format(DateTimeFormatter.ofPattern("MM/yyyy")) %> -
                                <%= position.getFinalDate().equals(NOW) ? "Сейчас" : position.getFinalDate().format(DateTimeFormatter.ofPattern("MM/yyyy"))%>
                            </TD>
                            <TD>
                                <B><c:out value="${position.title}"/></B>
                            </TD>
                        </TR>
                        <TR>
                            <TD>
                                <c:out value="${position.description}"/>
                            </TD>
                        </TR>
                    </c:forEach>
                </TABLE>

            </c:forEach>

        </c:when>

    </c:choose>
    </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>