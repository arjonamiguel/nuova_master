<%@page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@taglib uri="http://www.springframework.org/security/tags"  prefix="sec" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="<%=request.getContextPath()%>/resources/css/breadcrumb.css" rel="stylesheet"/>
<style>
.navbar{
	margin-bottom:0px;
}
</style>
<div class="row" style="padding-left:2%;height:60px;">
        <hr class="hr-primary" />
        <ol id="breadcrumbList"class="breadcrumb bread-primary ">
          <button id="breadButton" href="" class="btn btn-info" onclick="javascript:window.location.href = '/nuova'"><i class="ico icon-home"></i>Home</button>
        </ol>
</div>

<script>
var urlHome="<%=request.getContextPath()%>";
var pathArray = window.location.pathname.split( '/' );
var arrayLength = pathArray.length;
var partialUrl="";
var htmlText="";
//document.getElementsByClassName("ico icon-home")[0].innerHTML=pathArray[1];
//$("#breadButton").attr("href",urlHome);
for (var i = 2; i < arrayLength; i++) {
	partialUrl=partialUrl+pathArray[i]+'/';
	if(i==arrayLength-1){
	 htmlText="<li class='active'>"+pathArray[i]+"</li>"
	}else{
	 htmlText="<li class='active'><a href='"+partialUrl+"'>"+pathArray[i]+"</a></li>"
	}
	
	$("#breadcrumbList").append(htmlText);
}
</script>
