<%@ page import="java.util.List" %>
<%@ page import="ma.ensi.dao.ExperienceDAO" %>
<%@ page import="ma.ensi.model.Experiences" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="../../../../../../IdeaProjects/JavaProject_MyJob/src/main/webapp/css/STYLEUNI.css">
    <title>Experiences | My Job</title>
    <style>
        .content { margin-left: 0px; padding: 8px; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 40px; margin-top: 30px; }
        table th, table td { padding: 15px; text-align: left; border: 1px solid #ddd; font-size: 16px; }
        table th { background-color: #2980B9; color: white; }
        table td { background-color: #f9f9f9; }
        .view-experiences-btn { padding: 8px 16px; background-color: #3498db; color: white; border: none; cursor: pointer; border-radius: 4px; transition: background-color 0.3s; }
        .view-experiences-btn:hover { background-color: #2980b9; }
        #experiences-container { display: grid; grid-template-columns: repeat(auto-fill, minmax(300px, 1fr)); gap: 20px; margin-top: 40px; }
        .experience-card { background-color: white; padding: 20px; border-radius: 8px; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1); transition: box-shadow 0.3s; }
        .experience-card:hover { box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15); }
        .experience-card h3 { color: #2980B9; font-size: 20px; margin-bottom: 10px; }
        .experience-card p { color: #7f8c8d; margin: 5px 0; }
        .experience-card .role { font-weight: bold; color: #2C3E50; }
        .experience-card .dates { color: #16A085; }
    </style>
</head>

<body>
    <div class="sidebar">
        <a href="#" class="logo">
            <i class='bx bx-code-alt'></i>
            <div class="logo-name"><span>My</span>Job</div>
        </a>
        <ul class="side-menu">
            <li><a href="index.jsp"><i class='bx bxs-dashboard'></i> Dashboard</a></li>
            <li><a href="Examination.jsp"><i class='bx bx-file-find'></i> Examination</a></li>
            <li><a href="Experiences.jsp"><i class='bx bx-check-shield'></i> Expériences</a></li>
            <li><a href="Filtrage.jsp"><i class='bx bx-filter-alt'></i> Filtrage</a></li>
        </ul>
        <ul class="side-menu">
            <li><a href="#" class="logout"><i class='bx bx-log-out-circle'></i> Logout</a></li>
        </ul>
    </div>

    <div class="content">
        <nav>
            <i class='bx bx-menu'></i>
            <form action="#">
                <div class="form-input">
                    <input type="search" placeholder="Search...">
                    <button class="search-btn" type="submit"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <a href="#" class="notif">
                <i class='bx bx-bell'></i>
                <span class="count">12</span>
            </a>
        </nav>

        <main>
            <h1>Experiences</h1>
            <p>Select a candidate to view their experiences.</p>

            <table>
                <thead>
                    <tr>
                        <th>Candidate Name</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ExperienceDAO experienceDAO = new ExperienceDAO();
                        List<String> candidates = experienceDAO.getAllCandidates();
                        for (String candidate : candidates) {
                    %>
                        <tr>
                            <td><%= candidate %></td>
                            <td><button class="view-experiences-btn" data-candidate="<%= candidate %>">View Experiences</button></td>
                        </tr>
                    <% } %>
                </tbody>
            </table>

            <div id="experiences-container"></div>
        </main>
    </div>

    <script>
        document.querySelectorAll('.view-experiences-btn').forEach(button => {
            button.addEventListener('click', function () {
                const candidate = this.getAttribute('data-candidate');
                fetch(`ExperienceServlet?candidate=${candidate}`)
                    .then(response => response.json())
                    .then(data => displayExperiences(data));
            });
        });

        function displayExperiences(experiences) {
            const container = document.getElementById('experiences-container');
            container.innerHTML = '';

            if (!experiences || experiences.length === 0) {
                container.innerHTML = '<p>No experiences available for this candidate.</p>';
                return;
            }

            experiences.forEach(exp => {
                const card = document.createElement('div');
                card.classList.add('experience-card');
                card.innerHTML = `
                    <h3>${exp.role}</h3>
                    <p class="dates">${exp.startDate} - ${exp.endDate}</p>
                    <p class="description">${exp.description}</p>
                `;
                container.appendChild(card);
            });
        }
    </script>
</body>
</html>
