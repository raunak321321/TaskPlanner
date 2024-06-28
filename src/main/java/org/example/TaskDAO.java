package org.example;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

public class TaskDAO extends AbstractDAO<Task> {

    /**
     * Constructor.
     *
     * @param sessionFactory Hibernate session factory.
     */
    public TaskDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Task> findAll() {
        return list((org.hibernate.query.Query<Task>) namedQuery("Task.findAll"));
    }

    public Task findById(Long id) {
        return get(id);
    }

    public Task create(Task task) {
        return persist(task);
    }

    public void delete(Task task) {
        currentSession().delete(task);
    }

//    public void delete(Task task) {
//        Transaction transaction = null;
//        try (Session session = currentSession()) {
//            transaction = session.beginTransaction();
//            session.delete(task);
//            transaction.commit();
//        } catch (Exception e) {
//            if (transaction != null && transaction.getStatus() != TransactionStatus.COMMITTED) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        }
//    }

}