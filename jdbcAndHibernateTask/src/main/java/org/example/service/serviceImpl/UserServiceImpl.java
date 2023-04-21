package org.example.service.serviceImpl;

import org.example.dao.UserDao;
import org.example.dao.daoImpl.UserDaoJdbcImpl;
import org.example.model.User;
import org.example.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJdbcImpl();
    @Override
    public void createUsersTable() {
        userDao.createUsersTable();

    }

    @Override
    public void dropUsersTable() {
        userDao.dropUsersTable();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name,lastName,age);

    }

    @Override
    public void removeUserById(Long id) {
        userDao.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();

    }
}
