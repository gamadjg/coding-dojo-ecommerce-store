<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Products</title>
</head>
<body class="m-0 p-0">
    <div class="flex flex-row justify-between items-center bg-gray-700 pt-1 pb-1 pl-10 pr-10">
            <h1 class="text-4xl font-bold">
                <a href="/products" class="font-bold text-gray-400">
                    Quiver Me This
                </a>
            </h1>
        <div class="flex flex-row gap-5">
<%--            <a href="/" class="underline text-blue-500 font-bold">Dashboard</a>--%>
            <a href="/users/register" class="font-bold text-gray-400">Register</a>
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
            <a href="/products/targets" class="font-bold">
                Targets
            </a>
        </div>
    </div>
    <div class="grid grid-cols-3 w-10/12 ml-auto mr-auto mb-5 mt-5 border-l-2 border-t-2">
        <c:forEach var="product" items="${products}">
            <a href="/products/${product.id}" class="border-r-2 border-b-2  pl-5 pr-5">
                <div class="max-w-sm rounded overflow-hidden m-auto">
                    <img class="w-full" src="${product.imgLink}" alt="bow merchandise">
                        <div class="font-bold text-xl mb-2 text-center">
                            <c:out value="${product.name}" />
                        </div>
                    <div class="font-bold text-xl mb-2 text-center">
                        $<c:out value="${product.price}" />
                    </div>
    <%--                <div class="px-6 pt-4 pb-2">--%>
    <%--                    <span class="inline-block bg-gray-200 rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2">#hunting bow</span>--%>
    <%--                </div>--%>
                </div>
            </a>
        </c:forEach>
    </div>
</body>
</html>