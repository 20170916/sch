package com.lo.bdp.sch.service.dao;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.lo.bdp.sch.service.pojo.entity.User;

import java.util.List;

public interface UserDao extends GenericDAO<User, Integer> {
    @Override
    List<User> findAll();
}
