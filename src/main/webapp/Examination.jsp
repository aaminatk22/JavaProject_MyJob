<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ma.ensi.model.Examination" %>
<%@ page import="ma.ensi.dao.ExaminationDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="../../../../../../IdeaProjects/JavaProject_MyJob/src/main/webapp/css/STYLEUNI.css">
    <title>Examination | My Job</title>
    <style>
        body { font-family: Arial, sans-serif; background-color: #f4f4f4; }
        .content { margin-left: 0px; padding: 8px; }
        .filters { display: flex; gap: 10px; margin-bottom: 20px; }
        .filters input, .filters select { padding: 8px; border-radius: 5px; border: 1px solid #ccc; }
        table { width: 100%; background: white; border-collapse: collapse; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }
        table th, table td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
        table th { background: #007bff; color: white; }
        table tr:hover { background: #f1f1f1; }
        .status { padding: 5px 10px; border-radius: 5px; font-weight: bold; }
        .pending { background: #ffcc00; color: white; }
        .approved { background: #28a745; color: white; }
        .rejected { background: #dc3545; color: white; }
        .actions button { padding: 5px 10px; border: none; border-radius: 5px; cursor: pointer; margin: 2px; }
        .view { background: #007bff; color: white; }
        .approve { background: #28a745; color: white; }
        .reject { background: #dc3545; color: white; }
        .delete { background: #ff4d4d; color: white; }
        .actions button:hover { opacity: 0.8; }
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
            <li>
                <a href="#" class="logout">
                    <i class='bx bx-log-out-circle'></i>
                    Logout
                </a>
            </li>
        </ul>
    </div>

    <div class="content">
        <nav>
            <i class='bx bx-menu'></i>
            <form action="#">
                <div class="form-input">
                    <input type="search" id="search" placeholder="Search...">
                    <button class="search-btn" type="submit"><i class='bx bx-search'></i></button>
                </div>
            </form>
            <a href="#" class="notif">
                <i class='bx bx-bell'></i>
                <span class="count">12</span>
            </a>
        </nav>

        <main>
            <h1>Examination</h1>
            <div class="filters">
                <select id="status-filter">
                    <option value="all">Tous</option>
                    <option value="pending">En attente</option>
                    <option value="approved">Validé</option>
                    <option value="rejected">Rejeté</option>
                </select>
            </div>

            <table>
                <thead>
                    <tr>
                        <th>Nom du document</th>
                        <th>Candidat</th>
                        <th>Date de soumission</th>
                        <th>Statut</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ExaminationDAO examDAO = new ExaminationDAO();
                        List<Examination> exams = examDAO.getAllExams();
                        for (Examination exam : exams) {
                    %>
                        <tr>
                            <td><%= exam.getDocumentName() %></td> <!-- ✅ Updated from title -->
                            <td><%= exam.getCandidateName() %></td>
                            <td><%= exam.getSubmissionDate() %></td>
                            <td><span class="status <%= exam.getStatus() %>"><%= exam.getStatus() %></span></td>
                            <td class="actions">
                                <form action="ExaminationServlet" method="post">
                                    <input type="hidden" name="id" value="<%= exam.getId() %>">
                                    <button type="submit" name="action" value="approve" class="approve">✅ Valider</button>
                                    <button type="submit" name="action" value="reject" class="reject">❌ Rejeter</button>
                                    <button type="submit" name="action" value="delete" class="delete">🗑️ Supprimer</button>
                                </form>
                            </td>
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </main>
    </div>

    <script>
        // Search functionality
        document.getElementById('search').addEventListener('input', function() {
            let searchValue = this.value.toLowerCase();
            document.querySelectorAll('.exam-row').forEach(row => {
                let documentName = row.children[0].textContent.toLowerCase();
                let candidateName = row.children[1].textContent.toLowerCase();
                row.style.display = (documentName.includes(searchValue) || candidateName.includes(searchValue)) ? '' : 'none';
            });
        });

        // Filter functionality
        document.getElementById('status-filter').addEventListener('change', function() {
            let selectedStatus = this.value;
            document.querySelectorAll('.exam-row').forEach(row => {
                let rowStatus = row.getAttribute('data-status');
                row.style.display = (selectedStatus === 'all' || rowStatus === selectedStatus) ? '' : 'none';
            });
        });
    </script>
</body>
</html>
