<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- header login -->
<div style="text-align:right;font-size:13px">
	Logged as: 
	<sec:authorize access="isAuthenticated()">
	    <span style="color:blue;font-weight:bold;">
	    	<%=SecurityContextHolder.getContext().getAuthentication().getName()%>
	    </span> 
	</sec:authorize>|
	<span><a href="<c:url value='j_spring_security_logout'/>">Logout Session</a></span>
</div>
<!-- header login -->

<!-- Menu       -->
<sec:authorize access="hasRole('ROLE_ADMIN')">
ADMIN
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
USER
</sec:authorize>
<sec:authorize access="hasRole('ROLE_RECEPTION')">
RECEPCION
</sec:authorize>
<!-- Menu       -->