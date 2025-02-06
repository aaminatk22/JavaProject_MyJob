package ma.ensi.service;

import ma.ensi.dao.AgentUniversitaireDAO;
import ma.ensi.model.AgentUniversitaire;

import java.sql.SQLException;
import java.util.List;

public class AgentUniversitaireService {
    private final AgentUniversitaireDAO agentDAO = new AgentUniversitaireDAO();

    public void saveAgent(AgentUniversitaire agent) throws SQLException {
        agentDAO.saveAgent(agent);
    }

    public AgentUniversitaire getAgentById(int id) throws SQLException {
        return agentDAO.findAgentById(id);
    }

    public List<AgentUniversitaire> getAllAgents() throws SQLException {
        return agentDAO.findAllAgents();
    }

    public void updateAgent(AgentUniversitaire agent) throws SQLException {
        agentDAO.updateAgent(agent);
    }

    public void deleteAgent(int id) throws SQLException {
        agentDAO.deleteAgentById(id);
    }

    public void saveAgentUniversitaire(AgentUniversitaire agent) {
    }


    public void deleteAgentUniversitaire(int id) {
    }

    public AgentUniversitaire getAgentUniversitaireById(int id) {
    }
}
