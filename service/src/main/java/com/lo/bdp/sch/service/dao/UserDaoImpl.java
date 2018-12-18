package com.lo.bdp.sch.service.dao;

import com.googlecode.genericdao.search.ExampleOptions;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.SearchResult;
import com.lo.bdp.sch.service.dao.impl.BaseDaoImpl;
import com.lo.bdp.sch.service.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User,Integer> implements UserDao {

}
