package ma.ensi.dao;

import ma.ensi.model.Portfolio;

import java.sql.*;

public class PortfolioDAO {

    public int savePortfolio(Portfolio portfolio) throws SQLException {
        String sql = "INSERT INTO portfolio (id_utilisateur, description) VALUES (?, ?) RETURNING id_portfolio";

        try (Connection conn = ConnexionBDD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, portfolio.getIdUser());
            stmt.setString(2, portfolio.getDescription());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_portfolio"); // Return the generated ID
            }
        }
        return 0; // Return 0 if insertion fails
    }
}
