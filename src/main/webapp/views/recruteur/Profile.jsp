<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.16/tailwind.min.css">
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
    <div class="flex items-center justify-center bg-blue-500 w-full h-12 fixed top-0 left-0 shadow-md z-10">
        <div class="flex items-center space-x-4">
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1024 1024">
                    <path d="M946.5 505L560.1 118.8l-25.9-25.9a31.5 31.5 0 0 0-44.4 0L77.5 505a63.9 63.9 0 0 0-18.8 46c.4 35.2 29.7 63.3 64.9 63.3h42.5V940h691.8V614.3h43.4c17.1 0 33.2-6.7 45.3-18.8a63.6 63.6 0 0 0 18.7-45.3c0-17-6.7-33.1-18.8-45.2zM568 868H456V664h112v204zm217.9-325.7V868H632V640c0-22.1-17.9-40-40-40H432c-22.1 0-40 17.9-40 40v228H238.1V542.3h-96l370-369.7 23.1 23.1L882 542.3h-96.1z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="currentColor" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <path d="M12 2.5a5.5 5.5 0 0 1 3.096 10.047 9.005 9.005 0 0 1 5.9 8.181.75.75 0 1 1-1.499.044 7.5 7.5 0 0 0-14.993 0 .75.75 0 0 1-1.5-.045 9.005 9.005 0 0 1 5.9-8.18A5.5 5.5 0 0 1 12 2.5ZM8 8a4 4 0 1 0 8 0 4 4 0 0 0-8 0Z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" stroke-width="2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
                    <circle cx="9" cy="21" r="1"></circle>
                    <circle cx="20" cy="21" r="1"></circle>
                    <path stroke-linecap="round" stroke-linejoin="round" d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                </svg>
            </button>
        </div>
    </div>
</nav>



<div class="max-w-4xl mx-auto p-6">
    <div class="bg-white p-6 rounded-lg shadow-lg">
        <h1 class="text-2xl font-bold mb-4">User Profile</h1>
        <p class="text-gray-600 mb-4">Here are your account details:</p>

        <!-- Static User Information -->
        <div class="flex items-center space-x-4">
            <img src="https://img.freepik.com/vecteurs-libre/homme-affaires-caractere-avatar-isole_24877-60111.jpg?t=st=1735416215~exp=1735419815~hmac=42c8e6820734ace7d68c8a8a6ca7dce773bea7c590e2bdde0ba7649f8638be25&w=740" alt="Profile Picture" class="w-20 h-20 rounded-full object-cover">
            <div>
                <h2 class="text-xl font-semibold">Recruteur</h2>
                <p class="text-gray-600">Recruteur@example.com</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
