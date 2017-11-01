<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 16.10.2017
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <style type="text/css">
        .container {
            margin-top: 20px;
            margin-left: 20px;
        }
    </style>

    <title>Title</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="span8">
            <ul class="nav nav-pills">
               <li class="active"> <a>FL911</a></li>
             <li class="nav navbar-nav navbar-right"> <a href="#" data-toggle="modal" data-target="#logoutModal">logout</a></li>
            </ul>

        </div>

        </div>
    </div>


</div>

<!-- Small modal -->
<div class="modal" id="logoutModal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4>Log Out <i class="fa fa-lock"></i></h4>
            </div>

            <div class="modal-body">
                <p><i class="fa fa-question-circle"></i> Are you sure you want to log-off? <br /></p>
                <div class="actionsBtns">
                    <form action="/web/logout" method="post" id="logoutForm">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="button" onclick="document.forms['logoutForm'].submit()" class="btn btn-default btn-primary" data-dismiss="modal" value="Logout" />
                        <button class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<h2>Здесь могла быть ваша реклама!!!</h2>

</body>
</html>
