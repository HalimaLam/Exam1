package model;

import Employe.Employee;
import Employe.EmployeImp;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "employeBean")
@ViewScoped
public class EmplyeBean implements Serializable {

    private List<Employee> employees;
    private EmployeImp empdao;
    private Employee newEmployee;

    public EmplyeBean() {
        // Initialize StudentDAO
        empdao = new EmployeImp();
    }


    // Method to retrieve all students and populate the list
    public List<Employee> getStudents() {
        System.out.println("bean function is called");
        employees = empdao.getAllEmployee();
        return employees;
    }


/*public void updateStudent(int id, String fullname, String sex, Date birthday, String email) {
        studentDAO.updateStudent(id, fullname, sex, birthday, email);
    }*/

    /* public void deleteEmployeWithProjects(int employeId) {
    try {
            studentDAO.deleteStudentWithCourses(studentId);
            showMessage("student deleted with success");
             } catch (Exception e) {
        showMessage("student not deleted");
    }
     }*/


    /*public void addStudentWithCourses(){
        boolean allValidationsCorrect = true;

        if (!Validations.validateFullName(newStudent.getFullName())) {
            showMessage("Format du full name incorrect ");
            allValidationsCorrect = false;
        }

        if (!Validations.validateEmail(newStudent.getEmail())) {
            showMessage("Format de l'Email incorrect ");
            allValidationsCorrect = false;
        }

        if (allValidationsCorrect == false) {
            return;
        }

        boolean saved = studentDAO.addStudentWithCourses(newStudent,cour);

        if (saved) {
            allStudents = studentDAO.getAllStudents();
            showMessage("student added with success");
            newStudent = new Student();
        }else{
            showMessage("Echec lors de l ajout");
        }
    }*/
    public static void showMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
    public void preDestroy() {
        empdao.close();
    }
}