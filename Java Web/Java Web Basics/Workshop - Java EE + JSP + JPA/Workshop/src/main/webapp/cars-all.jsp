<%@ page import="domain.view.ViewModel" %>
<%@ page import="domain.service.CarServiceModel" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="jsp-templates/head.jsp"/>
</head>
<body>
<div class="container-fluid">
    <c:import url="jsp-templates/navbar.jsp"/>
    <h2 class="text-center text-white mt-5">West Compass Offers</h2>
    <hr style="width: 50%"/>
    <% ViewModel<List<CarServiceModel>> model = (ViewModel<List<CarServiceModel>>) request.getAttribute("model");%>
    <div class='row mb-4 d-flex justify-content-around'>
        <% for (CarServiceModel car : model.getObject()) {%>
        <div class="col-md-4 d-flex flex-column bg-text mb-3">
            <h2>Owner: <%=car.getUser()%>
            </h2>
            <h2>Brand: <%=car.getBrand()%>
            </h2>
            <h4>Model: <%=car.getModel()%>
            </h4>
            <h4>Year: <%=car.getYear()%>
            </h4>
            <h4>Engine: <%=car.getEngine().name()%>
            </h4>
        </div>
        <%}%>
    </div>
</div>
</body>
</html>
