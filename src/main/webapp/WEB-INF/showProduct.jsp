<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/38da9ec89a.js" crossorigin="anonymous"></script>
    <title>Product</title>
</head>

<body class="m-0 p-0">
    <div class="flex flex-row justify-between items-center bg-gray-700 pt-1 pb-1 pl-10 pr-10 w-screen">
        <h1 class="text-4xl font-bold">
            <a href="/products" class="font-bold text-white">
                Quiver Me This
            </a>
        </h1>
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
    <div class="flex w-10/12 ml-auto mr-auto mb-5 mt-5">
        <div class="flex-1 rounded overflow-hidden m-auto flex flex-col items-center">
            <img class="max-w-md" src="${product.imgLink}" alt="bow merchandise">
        </div>
        <div class="flex-1 rounded overflow-hidden m-auto flex flex-col items-center">
            <div class="font-bold text-xl mb-2 text-center">
                <c:out value="${product.name}" />
            </div>
            <div class="font-bold text-xl mb-2 text-center">
                $<c:out value="${product.price}" />
            </div>
            <ul class="list-disc pl-5">
                <c:forEach var="line" items="${descriptionList}">
                    <li>
                        ${line}
                    </li>
                </c:forEach>
            </ul>
            <form:form action="/products/addToCart/${product.id}" method="post">
                <input type="hidden" name="_method" value="put" />
                <button type="submit" class="w-full text-white focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center bg-blue-600 hover:bg-blue-700 focus:ring-blue-800">
                    Add To Cart
                </button>
            </form:form>
        </div>
    </div>
</body>
</html>