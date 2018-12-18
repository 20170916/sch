package com.lo.bdp.sch.service.dao.impl;

import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.io.Serializable;

public class BaseDaoImpl<T, ID extends Serializable> extends GenericDAOImpl<T, ID> {

    @Resource(name="sessionFactory")
    @Override
    public void setSessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }
}
