<%@tag pageEncoding="UTF-8"%>
<%@tag trimDirectiveWhitespaces="true"%>
<%@tag body-content="scriptless"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ attribute name="home" required="false" type="java.lang.String" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" scope="session" />
<%
    request.setAttribute("rootPath",request.getContextPath());
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>${title} - 网站管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0">
<!-- Open Sans font from Google CDN -->
<!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,600,700,300&subset=latin" rel="stylesheet" type="text/css">-->

<!-- Pixel Admin's stylesheets -->
<link href="${rootPath}/statics/assets/stylesheets/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/assets/stylesheets/pixel-admin.min.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/assets/stylesheets/widgets.min.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/assets/stylesheets/rtl.min.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/assets/stylesheets/themes.min.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/common/common.css" rel="stylesheet" type="text/css">
<link href="${rootPath}/statics/common/sie.css" rel="stylesheet" type="text/css">

<%--bootstrap datatime--%>
<link href="${rootPath}/statics/datatime/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css">
<%--end bootstrap datatime--%>
<link rel="stylesheet" type="text/css" href="${rootPath}/statics/jqGrid/ui.jqgrid.css"/>
<script>var init = [];</script>
</head>
<body class="theme-default main-menu-animated">
<script src="${rootPath}/statics/assets/demo/demo.js"></script>



<div id="main-wrapper">
    <jsp:include page="/WEB-INF/views/top.jsp"></jsp:include>
    <jsp:include page="/WEB-INF/views/left.jsp"></jsp:include>


    <div id="content-wrapper">
        <ul class="breadcrumb breadcrumb-page">
            <div class="breadcrumb-label text-light-gray">You are here: </div>

            <c:if test="${empty home}">
                <li><a href="/index.html">Home</a></li>
            </c:if>
            <c:if test="${not empty home}">
                <li><a href="javascript:window.history.go(-1)">${home}</a></li>
            </c:if>

            <li class="active"><a href="#">${title}</a></li>

        </ul>
        <jsp:doBody></jsp:doBody>
    </div>


</div>



</body>

<script src="${rootPath}/statics/assets/javascripts/jquery-1.8.3.min.js"></script>
<script src="${rootPath}/statics/assets/javascripts/bootstrap.min.js"></script>
<script src="${rootPath}/statics/assets/javascripts/pixel-admin.min.js"></script>
<script src="${rootPath}/statics/jqGrid/jquery.jqGrid.min.js"></script>
<script src="${rootPath}/statics/jqGrid/grid.locale-cn.js"></script>
<script src="${rootPath}/statics/common/jquery.validate.js"></script>
<script src="${rootPath}/statics/common/util.js"></script>

<script type="text/javascript">

    init.push(function () {
        // Javascript code here
    })

    pageRootPath = "${rootPath}";
    window.PixelAdmin.start(init);
</script>
</html>