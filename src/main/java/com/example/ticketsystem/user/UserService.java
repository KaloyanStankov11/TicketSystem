package com.example.ticketsystem.user;

import com.example.ticketsystem.Base._BaseService;
import jakarta.persistence.EntityTransaction;
import org.springframework.stereotype.Service;

@Service
public class UserService extends _BaseService {

    public void addUser(String email, String password, String username){
        User user = new User();
        user.setUserType(UserType.USER.name());
        user.setEmail(email);
        user.setPassword(password);
        user.setDiscountCard(username);
        em.persist(user);
    }

    public void editUser(Long userId, String newEmail, String newPassword, String newUsername){
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User user = em.find(User.class, userId);
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        user.setEmail(newEmail);
        tx.commit();
    }

    public User getUser(Long userId){
        return em.find(User.class, userId);
    }
}
