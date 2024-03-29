package DAOproject;
import Employe.Employee;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Project")
@NamedQuery(name ="findById" ,query = "SELECT p FROM Project p")
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String name;
    private double budget;
    @ManyToMany
    @JoinTable(
            name = "mtom",
            joinColumns = @JoinColumn(name = "pid"),
            inverseJoinColumns = @JoinColumn(name = "eid"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"pid", "eid"})
    )
    private Set<Employee> employees = new HashSet<>();
    public Projet() {
    }
    public Projet(Long id, String name, double budget) {
        this.pid = id;
        this.name = name;
        this.budget = budget;
    }

    // Getters et setters pour les attributs
    public Long getLong() {
        return pid;
    }

    public void setId(Long id) {
        this.pid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
    @Override
    public String toString() {
        return "Projet{" +
                "id=" + pid +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }


}

