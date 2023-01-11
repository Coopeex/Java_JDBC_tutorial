package jdbc_recap;

import jdbc_recap.entity.Project;
import jdbc_recap.repository.ProjectRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProjectRepository projectRepository = new ProjectRepository();
        List<Project> allProjects = projectRepository.findAll();
        System.out.println(allProjects);

        Project nauajasProjektas = new Project();
        nauajasProjektas.setName("Kepykla");
        nauajasProjektas.setBudget(15000);

        projectRepository.add(nauajasProjektas);

        System.out.println();

        System.out.println(projectRepository.findAll());
    }
}
