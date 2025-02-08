<%@ page import="java.util.List" %>
<%@ page import="ma.ensi.dao.ExperienceDAO" %>
<%@ page import="ma.ensi.dao.ExaminationDAO" %>
<%@ page import="ma.ensi.model.Experiences" %>
<%@ page import="ma.ensi.model.Examination" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="../../../../../../IdeaProjects/JavaProject_MyJob/src/main/webapp/css/STYLEUNI.css">
    <title>Filtrage | My Job</title>
    <style>
        .filters { display: flex; gap: 10px; margin-bottom: 20px; }
        .filters select, .filters input { padding: 8px; border-radius: 5px; border: 1px solid #ccc; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table th, table td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        table th { background: #007bff; color: white; }
        .status { padding: 5px 10px; border-radius: 5px; font-weight: bold; }
        .pending { background: #ffcc00; color: white; }
        .approved { background: #28a745; color: white; }
        .rejected { background: #dc3545; color: white; }
    </style>
</head>
<body>
    <div class="sidebar">
        <a href="#" class="logo"><i class='bx bx-code-alt'></i><div class="logo-name"><span>My</span>Job</div></a>
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
                    <input type="search" placeholder="Search..." id="search">
                    <button class="search-btn" type="submit"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <a href="#" class="notif"><i class='bx bx-bell'></i><span class="count">12</span></a>
        </nav>
        <main>
            <h1>Filtrage</h1>
            <div class="filters">
                <select id="filter-candidate">
                    <option value="">All Candidates</option>
                    <%
                        ExperienceDAO experienceDAO = new ExperienceDAO();
                        List<String> candidates = experienceDAO.getAllCandidates();
                        for (String candidate : candidates) {
                    %>
                    <option value="<%= candidate %>"><%= candidate %></option>
                    <% } %>
                </select>

                <select id="filter-role">
                    <option value="">All Roles</option>
                    <option value="Software Developer">Software Developer</option>
                    <option value="Project Manager">Project Manager</option>
                    <option value="Intern">Intern</option>
                </select>

                <select id="filter-status">
                    <option value="">All Status</option>
                    <option value="pending">Pending</option>
                    <option value="approved">Approved</option>
                    <option value="rejected">Rejected</option>
                </select>

                <input type="date" id="filter-start-date">
                <input type="date" id="filter-end-date">
                <button onclick="applyFilters()">Apply Filters</button>
            </div>

            <table id="results-table">
                <thead>
                    <tr>
                        <th>Candidate</th>
                        <th>Role</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody id="results-body">
                    <!-- Filtered results will be inserted here dynamically -->
                </tbody>
            </table>
        </main>
    </div>

    <script>
        function applyFilters() {
            const candidate = document.getElementById('filter-candidate').value;
            const role = document.getElementById('filter-role').value;
            const status = document.getElementById('filter-status').value;
            const startDate = document.getElementById('filter-start-date').value;
            const endDate = document.getElementById('filter-end-date').value;

            fetch(`FilterServlet?candidate=${candidate}&role=${role}&status=${status}&startDate=${startDate}&endDate=${endDate}`)
                .then(response => response.json())
                .then(data => updateResultsTable(data));
        }

        function updateResultsTable(data) {
            const tableBody = document.getElementById('results-body');
            tableBody.innerHTML = '';

            if (data.length === 0) {
                tableBody.innerHTML = '<tr><td colspan="5">No results found.</td></tr>';
                return;
            }

            data.forEach(item => {
                const row = `<tr>
                    <td>${item.candidateName}</td>
                    <td>${item.role}</td>
                    <td>${item.startDate}</td>
                    <td>${item.endDate}</td>
                    <td><span class="status ${item.status}">${item.status}</span></td>
                </tr>`;
                tableBody.innerHTML += row;
            });
        }
    </script>
</body>
</html>
