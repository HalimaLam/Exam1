package Employe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import DAOproject.Projet;
import java.util.List;
import java.util.Set;

public class EmployeImp implements EmployeDao {

    private EntityManagerFactory entityManagerFactory;

    public EmployeImp() {
        // Initialisation de l'EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("exemplePU");
    }

    @Override
    public List<Employee> getAllEmployee() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            TypedQuery<Employee> query = entityManager.createQuery("SELECT e FROM Employe e", Employee.class);
            List<Employee> employees = query.getResultList();
            entityManager.getTransaction().commit();
            return employees;
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Set<Projet> getProjet(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Employee emp = entityManager.find(Employee.class, id);
            entityManager.getTransaction().commit();
            return emp.getProjets();
        } catch (Exception ex) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw ex;
        } finally {
            entityManager.close();
        }
    }

    public void close() {
        entityManagerFactory.close();
    }

    public void addEmployeeWithProjects(Employee emp, Set<Projet> projects) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Ajout des associations de projets à l'employé
            emp.setProjets(projects);

            // Persistance de l'employé et de ses associations de projets
            entityManager.persist(emp);

            // Commit de la transaction
            transaction.commit();
        } catch (Exception ex) {
            // Rollback de la transaction en cas d'erreur
            if (transaction.isActive()) {
                transaction.rollback();
            }
            // Gérer l'exception ou la propager si nécessaire
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}





/*


    public void updateStudent(int id, String fullname, String sex, Date birthday, String email,Set<Course> courses) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Recherche de l'étudiant à mettre à jour
            Student student = entityManager.find(Student.class, id);

            // Mise à jour des propriétés de l'étudiant
            if (student != null) {
                student.setFullname(fullname);
                student.setSex(sex);
                student.setBirthday(birthday);
                student.setEmail(email);
                student.setCourses(courses);
                // Fusion des modifications avec l'entité gérée par l'EntityManager
                entityManager.merge(student);
            } else {
                // Gérer le cas où l'étudiant n'est pas trouvé
                throw new IllegalArgumentException("Student with id " + id + " not found.");
            }

            // Commit de la transaction
            transaction.commit();
        } catch (Exception ex) {
            // Rollback de la transaction en cas d'erreur
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Gérer l'exception ou la propager si nécessaire
            ex.printStackTrace();
        }
    }
}
*/



    /* EntityManager em = null;

        try {
            // Création de l'EntityManager
            em = emf.createEntityManager();

            // Début de la transaction pour les opérations de création et de mise à jour
            EntityTransaction transaction = em.getTransaction();

            transaction.begin();

            // Retrieve students
            TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
            students = query.getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (PersistenceException e) {
            // Handle persistence-related exceptions
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Close the EntityManager
            if (em != null) {
                em.close();
            }
        }*/



   /* public void deleteStudentWithCourses(int studentId) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();

            // Recherche de l'étudiant à supprimer
            Student student = entityManager.find(Student.class, studentId);

            // Suppression de l'étudiant et de ses associations de cours
            if (student != null) {
                // Si vous avez une relation Many-to-Many avec une table de jointure entre Student et Course,
                // vous devrez également supprimer les associations de cours de l'étudiant
                student.getCourses().clear(); // Supprime toutes les associations de cours de l'étudiant
                entityManager.remove(student); // Supprime l'étudiant de la base de données
            } else {
                // Gérer le cas où l'étudiant n'est pas trouvé
                throw new IllegalArgumentException("Student with id " + studentId + " not found.");
            }

            // Commit de la transaction
            transaction.commit();
        } catch (Exception ex) {
            // Rollback de la transaction en cas d'erreur
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            // Gérer l'exception ou la propager si nécessaire
            ex.printStackTrace();
        }
    }





*/
