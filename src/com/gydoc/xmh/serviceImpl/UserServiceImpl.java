package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.service.UserService;
import java.util.List;
import org.hibernate.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation= Propagation.REQUIRED)
public class UserServiceImpl extends ServiceBase implements UserService {

    public boolean isSuperUserName(String userName) {
        return "_sys".equals(userName);
    }

    public User login(String userName, String password, Long ledgerId) {
        if (isSuperUserName(userName)) {
            Query q = getCS().createQuery("from User as u where u.name=:name and u.password=:password");
            q.setString("name", userName);
            q.setString("password", password);
            List result = q.list();
            if (result.size() != 1) {
                return null;
            }
            return (User) result.get(0);
        } else {
            if (ledgerId == null) {
                return null;
            }
            Query q = getCS().createQuery("from User as u where u.name=? and password=?");
            q.setParameter(1, userName);
            q.setParameter(2, password);
            List result = q.list();
            if (result.size() != 1) {
                return null;
            }
            return null;
        }
    }

    public User updateUser(User u) {
        getCS().saveOrUpdate(u);
        return u;
    }

}
