<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Profile</title>
</head>
<body>
<h2>Create Your Profile</h2>
<form action="<%= request.getContextPath() %>/profile" method="post" enctype="multipart/form-data">
    <label>Description:</label>
    <textarea name="description" required></textarea><br>

    <label>Competence 1:</label>
    <input type="text" name="competence1"><br>

    <label>Competence 2:</label>
    <input type="text" name="competence2"><br>

    <label>Project Title:</label>
    <input type="text" name="projet1"><br>
    <label>Project Description:</label>
    <textarea name="projetDescription1"></textarea><br>

    <label>Experience Title:</label>
    <input type="text" name="experience1"><br>
    <label>Company:</label>
    <input type="text" name="entreprise1"><br>

    <label>Upload Resume:</label>
    <input type="file" name="resume" required><br>

    <button type="submit">Save Profile</button>
</form>
</body>
</html>
