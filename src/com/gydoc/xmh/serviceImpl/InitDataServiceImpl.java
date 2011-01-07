package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.SQLUtil;
import com.gydoc.xmh.domain.AccountClassification;
import com.gydoc.xmh.domain.AppInfo;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.service.InitDataService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation=Propagation.REQUIRED)
public class InitDataServiceImpl extends ServiceBase implements InitDataService {

    public void initData() throws SQLException, IOException {
        Session session = getSessionFactory().getCurrentSession();
        Query q = session.createQuery("from AppInfo");
        List result = q.list();
        if (result.size() != 0) {
            return ;
        }

		SQLUtil.executeBatch(getCS().connection(), getClass().getClassLoader().getResourceAsStream("db/derby/init-data.sql"));
    }

}
