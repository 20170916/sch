package com.lo.bdp.sch.service.service.impl;

import com.lo.bdp.sch.service.dao.UserDao;
import com.lo.bdp.sch.service.pojo.entity.User;
import com.lo.bdp.sch.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
