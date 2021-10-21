package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserbyCar(Car car) {
        TypedQuery<Car> query = null;
        // if(car.getUser() == null) {
        query = sessionFactory.getCurrentSession().createQuery("from Car where model = :carModel and series = :carSeries");
        query.setParameter("carModel", car.getModel());
        query.setParameter("carSeries", car.getSeries());
        // }

        TypedQuery<User> queryUser = sessionFactory.getCurrentSession().createQuery("from User where id = :idByCar");
        queryUser.setParameter("idByCar", query != null ? query.getSingleResult().getUser().getId() : car.getUser().getId());
        return queryUser.getSingleResult();
    }

}
