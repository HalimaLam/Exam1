package Employe;
import DAOproject.Projet;
import java.util.List;
import java.util.Set;

public interface EmployeDao {
    List<Employee> getAllEmployee();

    Set<Projet> getProjet(Long id);
}
