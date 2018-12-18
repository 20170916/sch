package com.lo.bdp.sch.service.dao.impl;

import com.lo.bdp.sch.service.dao.UserDao;
import com.lo.bdp.sch.service.pojo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User, Integer> implements UserDao {
}
