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
<body class="m-0 p-0">
    <div class="flex flex-row justify-between items-center bg-gray-700 pt-1 pb-1 pl-10 pr-10 w-screen">
        <h1 class="text-4xl font-bold">
            <a href="/products" class="font-bold text-white">
                Quiver Me This
            </a>
        </h1>
        <div class="flex flex-row gap-5">
            <a href="/" class="block text-white font-medium text-sm px-5 py-2.5 text-center">Home</a>
            <a href="/users/login/register" class="block text-white font-medium text-sm px-5 py-2.5 text-center">Login / Register</a>
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
    <div class="grid grid-cols-2 w-6/12 ml-auto mr-auto mb-5 mt-5 align-center gap-5">
        <div class="relative w-full h-full max-w-md md:h-auto ml-auto mr-auto mt-0 mb-0">
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700">
                <div class="px-6 py-6 lg:px-8">
                    <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">Register</h3>
                    <form:form class="space-y-6" action="/users/login/register" method="post" modelAttribute="newUser">
                        <div>
                            <form:label path="name" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Name</form:label>
                            <form:input path="name" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="name"/>
                            <form:errors path="name" class="text-red-600"/>
                        </div>
                        <div>
                            <form:label path="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</form:label>
                            <form:input path="email" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="name@company.com"/>
                            <form:errors path="email" class="text-red-600"/>
                        </div>
                        <div>
                            <form:label path="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</form:label>
                            <form:input path="password" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="????????"/>
                            <form:errors path="password" class="text-red-600"/>
                        </div>
                        <div>
                            <form:label path="confirm" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Confirm Password</form:label>
                            <form:input path="confirm" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="????????"/>
                            <form:errors path="confirm" class="text-red-600"/>
                        </div>
                        <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">Create Account</button>
                    </form:form>
                </div>
            </div>
        </div>
        <div class="relative w-full h-full max-w-md md:h-auto ml-auto mr-auto mt-0 mb-0">
            <div class="relative bg-white rounded-lg shadow dark:bg-gray-700 ">
                <div class="px-6 py-6 lg:px-8">
                    <h3 class="mb-4 text-xl font-medium text-gray-900 dark:text-white">Login</h3>
                    <form:form class="space-y-6" action="/users/login" method="post" modelAttribute="newLogin">
                        <div>
                            <form:label path="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Email</form:label>
                            <form:input path="email" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="name@company.com"/>
                            <form:errors path="email" class="text-red-600"/>
                        </div>
                        <div>
                            <form:label path="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Password</form:label>
                            <form:input path="password" class="outline-none border text-sm rounded-lg block w-full p-2.5 bg-gray-600 border-gray-500 placeholder-gray-400 text-white" placeholder="????????"/>
                            <form:errors path="password" class="text-red-600"/>
                        </div>
                        <div class="flex justify-between">
                            <a href="#" class="text-sm text-blue-700 hover:underline dark:text-blue-500">Lost Password?</a>
                        </div>
                        <button type="submit" class="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                            Login to your account
                        </button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>