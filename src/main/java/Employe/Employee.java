package Employe;
import DAOproject.Projet;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "Employe")
@NamedQuery(name ="findById" ,query = "SELECT e FROM Employe e")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eid;
    private String name;
    private String email;
    private List<String> skills;
    @ManyToMany
    @JoinTable(
            name = "mtom",
            joinColumns = @JoinColumn(name = "eid"),
            inverseJoinColumns = @JoinColumn(name = "pid"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id", "pid"})
    )
    private Set<Projet> projets = new HashSet<>();
    public Employee() {
    }

    public Set<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

    public Employee(Long id, String name, String email, List<String> skills) {
        this.eid = id;
        this.name = name;
        this.email = email;
        this.skills = skills;
    }

    public Long getLong() {
        return eid;
    }

    public void setLong(Long id) {
        this.eid = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employe{" +
                "id=" + eid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skills=" + skills +
                '}';
    }
}

