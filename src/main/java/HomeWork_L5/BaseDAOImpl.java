package HomeWork_L5;

import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

public class BaseDAOImpl implements BaseDAO<Student, Long> {
    Session session = HibernateUtil.getSessionFactory().openSession();

    @Override
    public void insert(Student student) {
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Class<Student> student, Long id) {
        session.beginTransaction();
        session.delete(session.get(student, id));
        session.getTransaction().commit();
    }

    @Override
    public void update(Student student) {
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
    }

    @Override
    public Student findById(Class<Student> student, Long id) {
        session.beginTransaction();
        Student student1 = session.get(student, id);
        session.getTransaction().commit();
        return student1;
    }

    @Override
    public List<Student> findAll() {
        session.beginTransaction();
        Criteria criteria = session.createCriteria (Student.class);
        List<Student> students = criteria.list();
        session.getTransaction().commit();
        return students;
    }
}
