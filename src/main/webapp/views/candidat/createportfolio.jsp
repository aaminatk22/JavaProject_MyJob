<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.ensi.model.Utilisateur" %>
<%
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    if (utilisateur == null || !"candidat".equalsIgnoreCase(utilisateur.getRole())) {
        response.sendRedirect(request.getContextPath() + "/views/login/loginpage.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Portfolio</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Include Required CSS -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>recruteur/assets/modules/bootstrap-5.1.3/css/bootstrap.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/fontawesome6.1.1/css/all.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/modules/boxicons/css/boxicons.min.css">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap JS (required for interactive components) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</head>
<body class="bg-gray-100">



<!--Sidebar-->
<div class="sidebar transition overlay-scrollbars animate_animated  animate_slideInLeft">
    <div class="sidebar-content">
        <div id="sidebar">
            <!-- Logo -->
            <!-- Logo -->
            <div class="logo">
                <h2 class="mb-0 text-2xl">MyJob.ma</h2>
            </div>
            <ul class="side-menu">
                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CandidatProfil.jsp" class="active">
                        <i class='bx bxs-dashboard icon' ></i> Candidate Profile
                    </a>
                </li>

                <!-- Divider-->
                <li class="divider" data-text="STARTER">MYJOB.ma</li>

                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/createportfolio.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Créer Portfolio
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>

                <li>
                    <a href="<%= request.getContextPath() %>/views/candidat/CreerProfil.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Créer Profil
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
                <li>
                    <a href="<%= request.getContextPath() %>views/candidat/mesCandidatures.jsp">
                        <i class='bx bx-columns icon' ></i>
                        Mes Candidatures
                        <i class='bx bx-chevron-right icon-right' ></i>
                    </a>
                </li>
            </ul>
        </div>


    </div>
</div>




<div class="ml-64 flex-1 p-8 bg-gray-100 min-h-screen">
    <div class="container mx-auto p-8 bg-white rounded-2xl shadow-xl">
        <h1 class="text-4xl font-extrabold text-blue-600 mb-8 text-center">Create Your Portfolio</h1>
        <form action="${pageContext.request.contextPath}/candidat/createPortfolio" method="POST" enctype="multipart/form-data" class="space-y-10">

            <!-- Portfolio Description -->
            <div class="space-y-2">
                <label for="description" class="block text-xl font-semibold text-gray-700">Portfolio Description</label>
                <textarea id="description" name="description" rows="4" class="w-full border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300" placeholder="Write a brief description about your portfolio..." required></textarea>
            </div>

            <!-- Competences -->
            <div class="space-y-4">
                <label class="block text-xl font-semibold text-gray-700">Competences</label>
                <div id="competences" class="space-y-4">
                    <div class="flex items-center space-x-4">
                        <input type="text" name="competences[][name]" placeholder="Competence Name" class="flex-1 border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <input type="text" name="competences[][level]" placeholder="Level (e.g., Beginner)" class="flex-1 border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                    </div>
                </div>
                <button type="button" id="addCompetence" class="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 transition">Add Competence</button>
            </div>

            <!-- Experiences -->
            <div class="space-y-4">
                <label class="block text-xl font-semibold text-gray-700">Experiences</label>
                <div id="experiences" class="space-y-4">
                    <div class="grid grid-cols-5 gap-4">
                        <input type="text" name="experiences[][title]" placeholder="Title" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <input type="text" name="experiences[][company]" placeholder="Company" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <input type="date" name="experiences[][startDate]" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <input type="date" name="experiences[][endDate]" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <textarea name="experiences[][description]" placeholder="Description" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300"></textarea>
                    </div>
                </div>
                <button type="button" id="addExperience" class="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 transition">Add Experience</button>
            </div>

            <!-- Projects -->
            <div class="space-y-4">
                <label class="block text-xl font-semibold text-gray-700">Projects</label>
                <div id="projects" class="space-y-4">
                    <div class="grid grid-cols-3 gap-4">
                        <input type="text" name="projects[][title]" placeholder="Project Title" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <textarea name="projects[][description]" placeholder="Project Description" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300"></textarea>
                        <input type="text" name="projects[][technologies]" placeholder="Technologies Used" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                    </div>
                </div>
                <button type="button" id="addProject" class="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 transition">Add Project</button>
            </div>

            <!-- Documents -->
            <div class="space-y-4">
                <label class="block text-xl font-semibold text-gray-700">Upload Documents</label>
                <div id="documents" class="space-y-4">
                    <div class="grid grid-cols-2 gap-4">
                        <input type="file" name="documents[][file]" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                        <input type="text" name="documents[][type]" placeholder="Document Type (e.g., CV, Certificate)" class="border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300">
                    </div>
                </div>
                <button type="button" id="addDocument" class="px-6 py-3 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-blue-600 transition">Add Document</button>
            </div>

            <!-- Verification Request -->
            <div class="bg-blue-50 p-6 rounded-lg shadow">
                <h3 class="text-2xl font-semibold text-blue-600 mb-4">Verification Request</h3>
                <div class="space-y-4">
                    <input type="text" name="university" placeholder="University Name" class="w-full border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300" required>
                    <input type="text" name="verificationDocumentType" placeholder="Document Type (e.g., CV, Certificate)" class="w-full border border-gray-300 rounded-lg p-4 text-gray-700 shadow-sm focus:ring focus:ring-blue-300" required>
                </div>
                <button type="submit" name="submitVerificationRequest" class="mt-4 w-full px-6 py-3 bg-yellow-500 text-white font-semibold rounded-lg shadow-md hover:bg-yellow-600 transition">Request Verification</button>
            </div>

            <!-- Submit Button -->
            <div>
                <button type="submit" class="w-full px-6 py-3 bg-green-500 text-white font-semibold rounded-lg shadow-md hover:bg-green-600 transition">Create Portfolio</button>
            </div>
        </form>
    </div>
</div>


<script>
    function addInput(containerId, fields, isFile = false) {
        const container = document.getElementById(containerId);
        const div = document.createElement('div');
        div.className = 'flex space-x-2 mt-2';

        fields.forEach(field => {
            const input = document.createElement(field.tag);
            input.type = isFile ? 'file' : field.type || 'text';
            input.name = field.name;
            input.placeholder = field.placeholder || '';
            input.className = 'w-full border rounded-lg p-2';
            div.appendChild(input);
        });

        container.appendChild(div);
    }

    document.getElementById('addCompetence').addEventListener('click', () => {
        addInput('competences', [
            { name: 'competences[][name]', placeholder: 'Competence' },
            { name: 'competences[][level]', placeholder: 'Level (e.g., Beginner)' }
        ]);
    });

    document.getElementById('addExperience').addEventListener('click', () => {
        addInput('experiences', [
            { name: 'experiences[][title]', placeholder: 'Title' },
            { name: 'experiences[][company]', placeholder: 'Company' },
            { name: 'experiences[][startDate]', type: 'date' },
            { name: 'experiences[][endDate]', type: 'date' },
            { name: 'experiences[][description]', tag: 'textarea', placeholder: 'Description' }
        ]);
    });

    document.getElementById('addProject').addEventListener('click', () => {
        addInput('projects', [
            { name: 'projects[][title]', placeholder: 'Title' },
            { name: 'projects[][description]', tag: 'textarea', placeholder: 'Description' },
            { name: 'projects[][technologies]', placeholder: 'Technologies' }
        ]);
    });

    document.getElementById('addDocument').addEventListener('click', () => {
        addInput('documents', [
            { name: 'documents[][file]', type: 'file' },
            { name: 'documents[][type]', placeholder: 'Document Type (e.g., CV, Certificate)' }
        ], true);
    });
</script>

</body>
</html>
