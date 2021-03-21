package HomeWork_L5;

import java.util.List;

public class MainEntity {
    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");
        BaseDAOImpl studentBaseDAO = new BaseDAOImpl();
        Student newStudent = new Student("Petr", "World!");
        Student student = studentBaseDAO.findById(Student.class, 1L);
        student.setName("Alex");
        student.setMark("Hello!");
        studentBaseDAO.update(student);
        studentBaseDAO.insert(newStudent);
        studentBaseDAO.delete(Student.class, 9L);
        System.out.println(student.getName());
        for(int i = 1; i <= 1000; i++) {
            studentBaseDAO.insert(new Student("Petr_" + i, "World!"));
        }
        List <Student> students = studentBaseDAO.findAll();
        students.forEach(System.out :: println);
    }
}