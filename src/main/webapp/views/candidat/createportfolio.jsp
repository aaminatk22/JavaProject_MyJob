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
</head>
<body class="bg-gray-100">

<div class="container mx-auto p-6">
    <h1 class="text-2xl font-bold mb-4">Create Portfolio</h1>
    <form action="${pageContext.request.contextPath}/candidat/createPortfolio" method="POST" enctype="multipart/form-data" class="space-y-6">
        <!-- Portfolio Description -->
        <div>
            <label for="description" class="block font-bold mb-2">Portfolio Description</label>
            <textarea id="description" name="description" rows="4" class="w-full border rounded-lg p-2" required></textarea>
        </div>

        <!-- Competences -->
        <div>
            <label class="block font-bold mb-2">Competences</label>
            <div id="competences" class="space-y-2">
                <div class="flex space-x-2">
                    <input type="text" name="competences[][name]" placeholder="Competence" class="w-1/2 border rounded-lg p-2">
                    <input type="text" name="competences[][level]" placeholder="Level (e.g., Beginner)" class="w-1/2 border rounded-lg p-2">
                </div>
            </div>
            <button type="button" id="addCompetence" class="mt-2 px-4 py-2 bg-blue-500 text-white rounded-lg">Add Competence</button>
        </div>

        <!-- Experiences -->
        <div>
            <label class="block font-bold mb-2">Experiences</label>
            <div id="experiences" class="space-y-2">
                <div class="grid grid-cols-5 gap-2">
                    <input type="text" name="experiences[][title]" placeholder="Title" class="border rounded-lg p-2">
                    <input type="text" name="experiences[][company]" placeholder="Company" class="border rounded-lg p-2">
                    <input type="date" name="experiences[][startDate]" class="border rounded-lg p-2">
                    <input type="date" name="experiences[][endDate]" class="border rounded-lg p-2">
                    <textarea name="experiences[][description]" placeholder="Description" class="border rounded-lg p-2"></textarea>
                </div>
            </div>
            <button type="button" id="addExperience" class="mt-2 px-4 py-2 bg-blue-500 text-white rounded-lg">Add Experience</button>
        </div>

        <!-- Projects -->
        <div>
            <label class="block font-bold mb-2">Projects</label>
            <div id="projects" class="space-y-2">
                <div class="grid grid-cols-3 gap-2">
                    <input type="text" name="projects[][title]" placeholder="Title" class="border rounded-lg p-2">
                    <textarea name="projects[][description]" placeholder="Description" class="border rounded-lg p-2"></textarea>
                    <input type="text" name="projects[][technologies]" placeholder="Technologies" class="border rounded-lg p-2">
                </div>
            </div>
            <button type="button" id="addProject" class="mt-2 px-4 py-2 bg-blue-500 text-white rounded-lg">Add Project</button>
        </div>

        <!-- Documents -->
        <div>
            <label class="block font-bold mb-2">Upload Documents</label>
            <div id="documents" class="space-y-2">
                <input type="file" name="documents[][file]" class="border rounded-lg p-2">
                <input type="text" name="documents[][type]" placeholder="Document Type (e.g., CV, Certificate)" class="border rounded-lg p-2">
            </div>
            <button type="button" id="addDocument" class="mt-2 px-4 py-2 bg-blue-500 text-white rounded-lg">Add Document</button>
        </div>

        <!-- Submit Button -->
        <div>
            <button type="submit" class="w-full px-4 py-2 bg-green-500 text-white rounded-lg">Create Portfolio</button>
        </div>
    </form>
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
