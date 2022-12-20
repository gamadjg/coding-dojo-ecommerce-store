<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Login & Registration</title>
</head>
<body class="w-6/12 m-auto">
<h1 class="text-4xl font-bold mb-4">Joy Bundler Names</h1>
    <div class="flex flex-col w-6/12">
        <h2 class="text-3xl font-bold mb-4">Register</h2>
        <form:form class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4" action="/users/register" method="post" modelAttribute="newUser">
            <div class="mb-4">
                <form:label path="name" class="block text-gray-700 text-sm font-bold mb-2">Name</form:label>
                <form:input path="name" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
                <form:errors path="name" class="text-red-600"/>
            </div>
            <div class="mb-6">
                <form:label path="email" class="block text-gray-700 text-sm font-bold mb-2">Email</form:label>
                <form:input path="email" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
                <form:errors path="email" class="text-red-600"/>
            </div>
            <div class="mb-4">
                <form:label path="password" class="block text-gray-700 text-sm font-bold mb-2">Password</form:label>
                <form:input path="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
                <form:errors path="password" class="text-red-600" />
            </div>
            <div class="mb-4">
                <form:label path="confirm" class="block text-gray-700 text-sm font-bold mb-2">Confirm Password</form:label>
                <form:input path="confirm" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
                <form:errors path="confirm" class="text-red-600"/>
            </div>
            <div class="flex items-center justify-between">
                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline" type="submit">
                    Register
                </button>
            </div>
        </form:form>
    </div>
</body>
</html>