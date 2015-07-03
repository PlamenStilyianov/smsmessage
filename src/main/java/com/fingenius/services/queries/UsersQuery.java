package com.fingenius.services.queries;




import com.fingenius.domain.login.Authorities;
import com.fingenius.domain.login.Users;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class UsersQuery {
    private final static Logger logger = LogManager.getLogger(UsersQuery.class.getName());

    @PersistenceContext
    private static EntityManager entityManager;

    public static boolean isValidUser(EntityManager em, String userName, String passwordUser) {
        logger.log(Level.INFO, "UsersQuery: inside isValidUser");
        entityManager =  em;
        Query query= entityManager.createQuery("select u from Users u where u.username = :userName and u.userpassword = :userPassword")
                .setParameter("userName", userName)
                .setParameter("userPassword",passwordUser);

        List users = (List) query.getResultList();
        int count = users.size();
        return count != 0;
    }

    public static List<Users> listUsers(EntityManager em) {
        logger.log(Level.INFO, "UsersQuery: inside listUsers");
        entityManager =  em;
        Query query= entityManager.createQuery("select u from Users u order by u.username");

        List<Users> users = (List<Users>) query.getResultList();

        return users;
    }

    public static int editUser(EntityManager em, String userId, String passwordUser) {
        logger.log(Level.INFO, "UsersQuery: inside editUser");
        entityManager =  em;
        int updatedUser = entityManager.createNativeQuery("update Users u set u.userpassword = :userPassword where u.id = :userId",Users.class)
                .setParameter("userId", userId)
                .setParameter("userPassword",passwordUser).executeUpdate();

        return updatedUser;
    }

    public static int deleteUser(EntityManager em, String userId) {
        logger.log(Level.INFO, "UsersQuery: inside deleteUser");
        entityManager =  em;
        int deleteUser= entityManager.createNativeQuery("delete from Users u where u.id = :userId",Users.class)
                .setParameter("userId", userId).executeUpdate();

        int deleteUserAuthority= entityManager.createNativeQuery("delete from Authorities a where a.username = (select username from Users u where u.id = :userId)", Authorities.class)
                .setParameter("userId", userId).executeUpdate();
        
        return deleteUser;
    }

    public static Users findUserById(EntityManager em, String userId) {
        logger.log(Level.INFO, "UsersQuery: inside findUserById");
        entityManager =  em;
        Query query= entityManager.createQuery("select u from Users u where u.id = :userId")
                .setParameter("userId", Long.valueOf(userId));
        Users user = (Users) query.getSingleResult();

        return user;
    }

    public static Users findUserByName(EntityManager em, String userName) {
        logger.log(Level.INFO, "UsersQuery: inside findUserById");
        entityManager =  em;
        Query query= entityManager.createQuery("select u from Users u where u.username = :userName")
                .setParameter("userName", userName);
        Users user = (Users) query.getSingleResult();

        return user;
    }

    public static Authorities findUserAuthority(EntityManager em, String username) {
        entityManager = em;
        Query query= entityManager.createQuery("select a from Authorities a where a.username = :userName")
                .setParameter("userName", username);
        Authorities authorities = (Authorities) query.getSingleResult();
        return authorities;
    }
}




