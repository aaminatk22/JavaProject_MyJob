<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Espace Recruteur</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<!-- Navbar -->
<nav class="bg-blue-500 text-white p-4">
    <div class="flex items-center justify-center bg-blue-500 w-full h-12 fixed top-0 left-0 shadow-md z-10">
        <div class="flex items-center space-x-4">
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg
                        class="w-5 h-5"
                        fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 1024 1024">
                    <path d="M946.5 505L560.1 118.8l-25.9-25.9a31.5 31.5 0 0 0-44.4 0L77.5 505a63.9 63.9 0 0 0-18.8 46c.4 35.2 29.7 63.3 64.9 63.3h42.5V940h691.8V614.3h43.4c17.1 0 33.2-6.7 45.3-18.8a63.6 63.6 0 0 0 18.7-45.3c0-17-6.7-33.1-18.8-45.2zM568 868H456V664h112v204zm217.9-325.7V868H632V640c0-22.1-17.9-40-40-40H432c-22.1 0-40 17.9-40 40v228H238.1V542.3h-96l370-369.7 23.1 23.1L882 542.3h-96.1z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg
                        class="w-5 h-5"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24">
                    <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg
                        class="w-5 h-5"
                        fill="currentColor"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24">
                    <path d="M12 2.5a5.5 5.5 0 0 1 3.096 10.047 9.005 9.005 0 0 1 5.9 8.181.75.75 0 1 1-1.499.044 7.5 7.5 0 0 0-14.993 0 .75.75 0 0 1-1.5-.045 9.005 9.005 0 0 1 5.9-8.18A5.5 5.5 0 0 1 12 2.5ZM8 8a4 4 0 1 0 8 0 4 4 0 0 0-8 0Z"></path>
                </svg>
            </button>
            <button class="flex items-center justify-center w-10 h-10 rounded-full bg-transparent text-white transition-transform duration-300 ease-in-out hover:-translate-y-1">
                <svg
                        class="w-5 h-5"
                        fill="none"
                        stroke="currentColor"
                        stroke-width="2"
                        xmlns="http://www.w3.org/2000/svg"
                        viewBox="0 0 24 24">
                    <circle cx="9" cy="21" r="1"></circle>
                    <circle cx="20" cy="21" r="1"></circle>
                    <path
                            stroke-linecap="round"
                            stroke-linejoin="round"
                            d="M1 1h4l2.68 13.39a2 2 0 0 0 2 1.61h9.72a2 2 0 0 0 2-1.61L23 6H6"></path>
                </svg>
            </button>
        </div>
    </div>

</nav>

<!-- User Profile -->
<div class="bg-white shadow p-6 max-w-6xl mx-auto mt-4 rounded-lg">
    <h2 class="text-lg font-bold">Bienvenue, Recruteur!</h2>
    <p class="text-gray-600">Gérez vos annonces avec facilité.</p>
</div>

<!-- Main Content -->
<div class="p-6 max-w-6xl mx-auto">
    <!-- Header -->
    <header class="flex justify-between items-center mb-8">
        <h1 class="text-2xl font-bold">Espace Recruteur</h1>
        <button onclick="openModal()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">
            Ajouter une annonce
        </button>
    </header>

    <!-- Filters -->
    <div class="bg-gray-100 p-4 rounded-lg mb-6">
        <h2 class="text-lg font-semibold mb-4">Filtrer annonces</h2>
        <div class="grid grid-cols-4 gap-4">
            <input type="text" name="title" placeholder="Titre du poste" class="p-2 border rounded-lg">
            <input type="text" name="function" placeholder="Fonction" class="p-2 border rounded-lg">
            <select name="experience" class="p-2 border rounded-lg">
                <option value="">Niveau d'expérience</option>
                <option value="0-2 years">0-2 years</option>
                <option value="3-5 years">3-5 years</option>
                <option value="5-7 years">5-7 years</option>
            </select>
            <select name="contract" class="p-2 border rounded-lg">
                <option value="">Contrat</option>
                <option value="Full-time">Full-time</option>
                <option value="Part-time">Part-time</option>
            </select>
        </div>
    </div>

    <!-- Job Listings -->
    <div class="bg-white p-4 rounded-lg shadow">
        <h2 class="text-lg font-semibold mb-4">Annonces publiées</h2>
        <ul id="jobListings" class="space-y-4">
            <!-- Dynamic Content -->
            <!-- Example Job -->
            <li class="p-4 border rounded-lg flex justify-between items-center">
                <div>
                    <h3 class="text-lg font-semibold">Software Engineer</h3>
                    <p class="text-gray-600">Development - 3-5 years - Full-time</p>
                </div>
                <div class="flex space-x-2">
                    <button class="px-3 py-1 bg-yellow-500 text-white rounded-lg hover:bg-yellow-600">Modifier</button>
                    <button class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600">Supprimer</button>
                </div>
            </li>
        </ul>
    </div>
</div>

<!-- Modal -->
<div id="modal" class="fixed inset-0 bg-gray-800 bg-opacity-50 hidden justify-center items-center">
    <div class="bg-white p-6 rounded-lg w-1/3">
        <h2 class="text-lg font-bold mb-4">Ajouter une annonce</h2>
        <form id="newJobForm" class="space-y-4">
            <input type="text" name="title" placeholder="Titre du poste" class="p-2 border rounded-lg w-full">
            <input type="text" name="function" placeholder="Fonction" class="p-2 border rounded-lg w-full">
            <input type="text" name="experience" placeholder="Expérience" class="p-2 border rounded-lg w-full">
            <input type="text" name="contract" placeholder="Contrat" class="p-2 border rounded-lg w-full">
        </form>
        <div class="mt-6 flex justify-end space-x-4">
            <button onclick="closeModal()" class="px-4 py-2 bg-gray-300 rounded-lg hover:bg-gray-400">Annuler</button>
            <button onclick="addJob()" class="px-4 py-2 bg-blue-500 text-white rounded-lg hover:bg-blue-600">Ajouter</button>
        </div>
    </div>
</div>

<!-- Scripts -->
<script>
    function openModal() {
        document.getElementById("modal").classList.remove("hidden");
    }

    function closeModal() {
        document.getElementById("modal").classList.add("hidden");
    }

    function addJob() {
        const form = document.getElementById("newJobForm");
        const jobListings = document.getElementById("jobListings");

        const title = form.title.value;
        const func = form.function.value;
        const experience = form.experience.value;
        const contract = form.contract.value;

        if (title && func && experience && contract) {
            const li = document.createElement("li");
            li.className = "p-4 border rounded-lg flex justify-between items-center";
            li.innerHTML = `
                    <div>
                        <h3 class="text-lg font-semibold">${title}</h3>
                        <p class="text-gray-600">${func} - ${experience} - ${contract}</p>
                    </div>
                    <div class="flex space-x-2">
                        <button class="px-3 py-1 bg-yellow-500 text-white rounded-lg hover:bg-yellow-600">Modifier</button>
                        <button class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600">Supprimer</button>
                    </div>
                `;
            jobListings.appendChild(li);
            closeModal();
        } else {
            alert("Veuillez remplir tous les champs !");
        }
    }
</script>
</body>
</html>
