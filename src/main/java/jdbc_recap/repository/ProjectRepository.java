package jdbc_recap.repository;

import jdbc_recap.entity.Project;
import jdbc_recap.utils.DatabaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * Repository (Persistence layer) is responsible for database related operations.
 * Eg. Service is responsible for Business logic
 * Isivaizduokime repository
 */
public class ProjectRepository {
    private static final String SELECT_FROM_PROJECTS = "SELECT * FROM projects,";
    private static final String INSERT_PROJECT="INSERT INTO PROJECTS (name, budget) VALUES('%s', %d)";

    public void add(Project project){
        try {
            Statement statement = DatabaseUtils.dbConnection.createStatement();
            statement.executeUpdate(String.format(INSERT_PROJECT, project.getName(),project.getBudget()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Project> findAll() {

        List<Project> projects;

        try {
            Statement statement = DatabaseUtils.dbConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_PROJECTS);

            projects = constructProjectList(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return projects;

    }

    private static List<Project> constructProjectList(ResultSet resultSet) throws SQLException {
        List<Project> projects = new ArrayList<>();
        while (resultSet.next()) {
            Project project = new Project();
            project.setId(resultSet.getInt("id"));
            project.setName(resultSet.getString("name"));
            project.setBudget(resultSet.getInt("budget"));
            projects.add(project);
        }
        return projects;
    }
}
