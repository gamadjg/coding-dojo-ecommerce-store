<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="https://unpkg.com/flowbite@1.5.5/dist/flowbite.min.css" />
    <script src="https://kit.fontawesome.com/38da9ec89a.js" crossorigin="anonymous"></script>
    <title>Products</title>
</head>
<body class="m-0 p-0">
<div class="flex flex-row justify-between items-center bg-gray-700 pt-1 pb-1 pl-10 pr-10 w-screen">
    <div class="flex flex-row items-baseline gap-5">
        <a href="/products">
            <h1 class="font-bold text-white text-4xl">
                Quiver Me This
            </h1>
        </a>
        <c:choose>
            <c:when test="${sessionScope.user_id != null}">
                <p class="font-bold text-white text-3xl">
                    Welcome <c:out value="${user.name}"/>!
                </p>
            </c:when>
        </c:choose>
    </div>
    <div class="flex flex-row gap-1 items-center">
        <a href="/" class="block text-white font-medium text-sm px-2 py-2 text-center">Home</a>
        <c:choose>
            <c:when test="${sessionScope.user_id != null}">
                <a href="/users/logout" class="block text-white font-medium text-sm px-2 py-2 text-center">Logout</a>
            </c:when>
            <c:otherwise>
                <a href="/users/login/register" class="block text-white font-medium text-sm px-2 py-2 text-center">Login / Register</a>
            </c:otherwise>
        </c:choose>
        <a href="/users/cart">
            <i class="fa-solid fa-cart-shopping text-white"></i>
        </a>
    </div>
</div>
<div class="m-auto">
    <div class="flex flex-row items-center items-center justify-center pl-3 pr-3 underline gap-5">
        <a href="/products/bows" class="font-bold">
            Bows
        </a>
        <a href="/products/arrows" class="font-bold">
            Arrows
        </a>
        <a href="/products/archery%20targets" class="font-bold">
            Targets
        </a>
    </div>
</div>
<div class="grid grid-cols-3 w-10/12 ml-auto mr-auto mb-5 mt-5 border-l-2 border-t-2">
    <c:forEach var="product" items="${products}">
        <a href="/products/product/${product.id}" class="border-r-2 border-b-2  pl-5 pr-5">
            <div class="max-w-sm rounded overflow-hidden m-auto">
                <img class="w-full" src="${product.imgLink}" alt="bow merchandise">
                <div class="font-bold text-xl mb-2 text-center">
                    <c:out value="${product.name}" />
                </div>
                <div class="font-bold text-xl mb-2 text-center">
                    $<c:out value="${product.price}" />
                </div>
            </div>
        </a>
    </c:forEach>
</div>
<script src="https://unpkg.com/flowbite@1.5.5/dist/flowbite.js"></script>
</body>
</html>