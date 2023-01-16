package org.example.dao.impl;

import org.example.dao.AbstractDAO;
import org.example.model.User;

import java.util.Set;

public class UsersDAO extends AbstractDAO <User>{
    @Override
    public boolean insert(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean delete(User user) {
        return false;
    }

    @Override
    public User getById(User user) {
        return null;
    }

    @Override
    public Set<User> getAll() {
        return null;
    }
}
