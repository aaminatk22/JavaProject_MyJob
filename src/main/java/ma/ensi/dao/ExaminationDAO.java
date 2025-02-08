package ma.ensi.dao;

import ma.ensi.model.Examination;
import ma.ensi.dao.DBConnection; // Ensure this is correctly imported

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExaminationDAO {

    // SQL Queries
    private static final String INSERT_EXAM = "INSERT INTO examinations (document_name, candidate_name, submission_date, status) VALUES (?, ?, ?, 'pending')";
    private static final String SELECT_ALL_EXAMS = "SELECT * FROM examinations";
    private static final String UPDATE_STATUS = "UPDATE examinations SET status=? WHERE id=?";
    private static final String DELETE_EXAM = "DELETE FROM examinations WHERE id=?";

    // ✅ Method to add an exam
    public boolean addExam(Examination exam) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_EXAM)) {

            pstmt.setString(1, exam.getDocumentName());
            pstmt.setString(2, exam.getCandidateName());
            pstmt.setString(3, exam.getSubmissionDate());

            System.out.println("📌 Executing SQL: " + pstmt.toString()); // Debugging

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Exam inserted successfully!");
                return true;
            } else {
                System.out.println("❌ Exam insertion failed!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Method to get all exams
    public List<Examination> getAllExams() {
        List<Examination> exams = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_EXAMS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Examination exam = new Examination(
                        rs.getInt("id"),
                        rs.getString("document_name"), // ✅ Corrected column name
                        rs.getString("candidate_name"),
                        rs.getString("submission_date"),
                        rs.getString("status")
                );
                exams.add(exam);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return exams;
    }

    // ✅ Method to update exam status (Approve/Reject)
    public boolean updateStatus(int id, String status) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(UPDATE_STATUS)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Exam status updated to " + status);
                return true;
            } else {
                System.out.println("❌ Exam status update failed!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ Method to delete an exam
    public boolean deleteExam(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_EXAM)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Exam deleted successfully!");
                return true;
            } else {
                System.out.println("❌ Exam deletion failed!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
