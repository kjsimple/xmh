package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.I18NMessage;
import com.gydoc.xmh.domain.AccountClassification;
import com.gydoc.xmh.domain.AppInfo;
import com.gydoc.xmh.domain.Ledger;
import com.gydoc.xmh.domain.User;
import com.gydoc.xmh.service.InitDataService;
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

    public void initData() {
        Session session = getSessionFactory().getCurrentSession();
        Query q = session.createQuery("from AppInfo");
        List result = q.list();
        if (result.size() != 0) {
            return ;
        }
        initSuperUser();
        initAppInfo();
        initLedgerAndAC();
        initGroup();
    }

    private void initGroup() {
//        Group g = new Group();
//        g.setName("\u7ba1\u7406\u5458");
//        Query q = getCS().createQuery("from User as u where u.name=:name");
//        q.setString("name", "_sys");
//        User u = (User) q.uniqueResult();
//        u.setGroup(g);
//        g.addUser(u);
//        
//        getCS().saveOrUpdate(g);
//        getCS().saveOrUpdate(u);
    }

    private void initSuperUser() {
        User u = new User();
        u.setName("_sys");
        u.setPassword("manager");
        getCS().saveOrUpdate(u);
    }

    private void initAppInfo() {
        AppInfo info = new AppInfo();
        
        getCS().saveOrUpdate(info);
    }

    private void initLedgerAndAC() {
        addXKJZZ();
        addGYQY();
    }


    private void addGYQY() {
        AccountClassification ac = createAC(null, "\u5de5\u4e1a\u4f01\u4e1a", "top");

        AccountClassification ac0 = createAC(ac, "\u8d44\u4ea7", "1");
        createAC(ac0, "\u73b0\u91d1", "01");
        createAC(ac0, "\u94f6\u884c\u5b58\u6b3e", "02");
        createAC(ac0, "\u5176\u5b83\u8d27\u5e01\u8d44\u91d1", "09");
        createAC(ac0, "\u77ed\u671f\u6295\u8d44", "11");
        createAC(ac0, "\u5e94\u6536\u7968\u636e", "12");
        createAC(ac0, "\u5e94\u6536\u8d26\u6b3e", "13");
        createAC(ac0, "\u574f\u8d26\u51c6\u5907", "14");
        createAC(ac0, "\u9884\u4ed8\u8d26\u6b3e", "15");
        createAC(ac0, "\u5e94\u6536\u8865\u8d34\u6b3e", "18");
        createAC(ac0, "\u5176\u5b83\u5e94\u6536\u6b3e", "19");
        createAC(ac0, "\u6750\u6599\u91c7\u8d2d", "21");
        createAC(ac0, "\u539f\u6750\u6599", "23");
        createAC(ac0, "\u5305\u88c5\u7269", "28");
        createAC(ac0, "\u4f4e\u503c\u6613\u8017\u54c1", "29");
        createAC(ac0, "\u6750\u6599\u6210\u672c\u5dee\u5f02", "31");
        createAC(ac0, "\u59d4\u6258\u52a0\u5de5\u6750\u6599", "33");
        createAC(ac0, "\u81ea\u5236\u534a\u6210\u54c1", "35");
        createAC(ac0, "\u534a\u6210\u54c1", "37");
        createAC(ac0, "\u5206\u671f\u6536\u6b3e\u53d1\u51fa\u5546\u54c1", "38");
        createAC(ac0, "\u5f85\u644a\u8d39\u7528", "39");
        createAC(ac0, "\u957f\u671f\u6295\u8d44", "51");
        createAC(ac0, "\u56fa\u5b9a\u8d44\u4ea7", "61");
        createAC(ac0, "\u7d2f\u8ba1\u6298\u65e7", "65");
        createAC(ac0, "\u56fa\u5b9a\u8d44\u4ea7\u6e05\u7406", "66");
        createAC(ac0, "\u5728\u5efa\u5de5\u7a0b", "69");
        createAC(ac0, "\u65e0\u5f62\u8d44\u4ea7", "71");
        createAC(ac0, "\u9012\u5ef6\u8d44\u4ea7", "81");
        createAC(ac0, "\u5f85\u5904\u7406\u8d22\u4ea7\u635f\u76ca", "91");
        createAC(ac0, "\u5f85\u5904\u7406\u6d41\u52a8\u8d44\u4ea7\u635f\u76ca", "911");
        createAC(ac0, "\u5f85\u5904\u7406\u56fa\u5b9a\u8d44\u4ea7\u635f\u76ca", "912");
        createAC(ac0, "\u5f85\u8f6c\u9500\u6c47\u5151\u635f\u76ca", "95");

        AccountClassification ac1 = createAC(ac, "\u8d1f\u503a", "2");
        createAC(ac1, "\u77ed\u671f\u501f\u6b3e", "01");
        createAC(ac1, "\u5e94\u4ed8\u7968\u636e", "02");
        createAC(ac1, "\u5e94\u4ed8\u8d26\u6b3e", "03");
        createAC(ac1, "\u9884\u6536\u8d26\u6b3e", "04");
        createAC(ac1, "\u5176\u5b83\u5e94\u4ed8\u6b3e", "09");
        createAC(ac1, "\u5e94\u4ed8\u5de5\u8d44", "11");
        createAC(ac1, "\u5e94\u4ed8\u798f\u5229\u8d39", "14");
        createAC(ac1, "\u5e94\u4ea4\u7a0e\u91d1", "21");
        createAC(ac1, "\u5e94\u4ed8\u5229\u6da6", "23");
        createAC(ac1, "\u5176\u5b83\u5e94\u4ea4\u6b3e", "29");
        createAC(ac1, "\u9884\u63d0\u8d39\u7528", "31");
        createAC(ac1, "\u957f\u671f\u501f\u6b3e", "41");
        createAC(ac1, "\u5e94\u4ed8\u503a\u5238", "51");
        createAC(ac1, "\u957f\u671f\u5e94\u4ed8\u6b3e", "61");
        createAC(ac1, "\u9012\u5ef6\u7a0e\u6b3e", "70");
        createAC(ac1, "\u4e13\u9879\u5e94\u4ed8\u6b3e", "72");
        createAC(ac1, "\u79df\u623f\u5468\u8f6c\u91d1", "75");

        AccountClassification ac2 = createAC(ac, "\u6240\u6709\u8005\u6743\u76ca", "3");
        createAC(ac2, "\u5b9e\u6536\u8d44\u672c", "01");
        createAC(ac2, "\u8d44\u672c\u516c\u79ef", "11");
        createAC(ac2, "\u76c8\u4f59\u516c\u79ef", "13");
        createAC(ac2, "\u516c\u76ca\u91d1", "132");

    }

    private AccountClassification createAC(AccountClassification parent, String name, String code) {
        AccountClassification ac0 = new AccountClassification();
        ac0.setName(name);
        ac0.setCode(code);
        if (parent != null) {
            parent.addChild(ac0);
        }
        return ac0;
    }

    private void addXKJZZ() {
        AccountClassification ac = createAC(null, "\u65b0\u4f1a\u8ba1\u51c6\u5219", "top");

        AccountClassification ac0 = createAC(ac, "\u8d44\u4ea7", "1");
        createAC(ac0, "\u5e93\u5b58\u73b0\u91d1", "001");
        createAC(ac0, "\u94f6\u884c\u5b58\u6b3e", "002");
        createAC(ac0, "\u5176\u5b83\u8d27\u5e01\u8d44\u91d1", "012");
        createAC(ac0, "\u4ea4\u6613\u6027\u91d1\u878d\u8d44\u4ea7", "101");
        createAC(ac0, "\u5e94\u6536\u7968\u636e", "121");
        createAC(ac0, "\u5e94\u6536\u8d26\u6b3e", "122");
        createAC(ac0, "\u9884\u4ed8\u8d26\u6b3e", "123");
        createAC(ac0, "\u5e94\u6536\u80a1\u5229", "131");
        createAC(ac0, "\u5e94\u6536\u5229\u606f", "132");
        createAC(ac0, "\u5176\u5b83\u5e94\u6536\u6b3e", "221");
        createAC(ac0, "\u574f\u8d26\u51c6\u5907", "231");
        createAC(ac0, "\u4ee3\u7406\u4e1a\u52a1\u8d44\u4ea7", "321");
        createAC(ac0, "\u6750\u6599\u91c7\u8d2d", "401");
        createAC(ac0, "\u5728\u9014\u7269\u8d44", "402");
        createAC(ac0, "\u539f\u6750\u6599", "403");
        createAC(ac0, "\u6750\u6599\u6210\u672c\u5dee\u5f02", "404");
        createAC(ac0, "\u5e93\u5b58\u5546\u54c1", "405");
        createAC(ac0, "\u53d1\u51fa\u5546\u54c1", "406");
        createAC(ac0, "\u5546\u54c1\u8fdb\u9500\u5dee\u4ef7", "407");
        createAC(ac0, "\u59d4\u6258\u52a0\u5de5\u7269\u8d44", "408");
        createAC(ac0, "\u5468\u8f6c\u6750\u6599", "411");
        createAC(ac0, "\u5b58\u8d27\u8dcc\u4ef7\u51c6\u5907", "471");
        createAC(ac0, "\u6301\u6709\u81f3\u5230\u671f\u6295\u8d44", "501");
        createAC(ac0, "\u6301\u6709\u81f3\u5230\u671f\u6295\u8d44\u51cf\u503c\u51c6\u5907", "502");
        createAC(ac0, "\u53ef\u4f9b\u51fa\u552e\u91d1\u878d\u8d44\u4ea7", "503");
        createAC(ac0, "\u957f\u671f\u80a1\u6743\u6295\u8d44", "511");
        createAC(ac0, "\u957f\u671f\u80a1\u6743\u6295\u8d44\u51cf\u503c\u51c6\u5907", "512");
        createAC(ac0, "\u6295\u8d44\u6027\u623f\u5730\u4ea7", "521");
        createAC(ac0, "\u957f\u671f\u5e94\u6536\u6b3e", "531");
        createAC(ac0, "\u672a\u5b9e\u73b0\u878d\u8d44\u6536\u76ca", "532");
        createAC(ac0, "\u56fa\u5b9a\u8d44\u4ea7", "601");
        createAC(ac0, "\u7d2f\u8ba1\u6298\u65e7", "602");
        createAC(ac0, "\u56fa\u5b9a\u8d44\u4ea7\u51cf\u503c\u51c6\u5907", "603");
        createAC(ac0, "\u5728\u5efa\u5de5\u7a0b", "604");
        createAC(ac0, "\u5de5\u7a0b\u7269\u8d44", "605");
        createAC(ac0, "\u56fa\u5b9a\u8d44\u4ea7\u6e05\u7406", "606");
        createAC(ac0, "\u65e0\u5f62\u8d44\u4ea7", "701");
        createAC(ac0, "\u7d2f\u8ba1\u644a\u9500", "702");
        createAC(ac0, "\u65e0\u5f62\u8d44\u4ea7\u51cf\u503c\u51c6\u5907", "703");
        createAC(ac0, "\u5546\u8a89", "711");
        createAC(ac0, "\u957f\u671f\u5f85\u644a\u8d39\u7528", "801");
        createAC(ac0, "\u9012\u5ef6\u6240\u5f97\u7a0e\u8d44\u4ea7", "811");
        createAC(ac0, "\u5f85\u5904\u7406\u8d22\u4ea7\u635f\u76ca", "901");

        AccountClassification ac1 = createAC(ac, "\u8d1f\u503a", "2");
        createAC(ac1, "\u77ed\u671f\u501f\u6b3e", "001");
        createAC(ac1, "\u4ea4\u6613\u6027\u91d1\u878d\u8d1f\u503a", "101");
        createAC(ac1, "\u5e94\u4ed8\u7968\u636e", "201");
        createAC(ac1, "\u5e94\u4ed8\u8d26\u6b3e", "202");
        createAC(ac1, "\u9884\u6536\u8d26\u6b3e", "203");
        createAC(ac1, "\u5e94\u4ed8\u804c\u5de5\u85aa\u916c", "211");
        createAC(ac1, "\u5e94\u4ea4\u7a0e\u8d39", "221");
        createAC(ac1, "\u5e94\u4ed8\u5229\u606f", "231");
        createAC(ac1, "\u5e94\u4ed8\u80a1\u5229", "232");
        createAC(ac1, "\u5176\u5b83\u5e94\u4ed8\u6b3e", "241");
        createAC(ac1, "\u4ee3\u7406\u4e1a\u52a1\u8d1f\u503a", "314");
        createAC(ac1, "\u9012\u5ef6\u6536\u76ca", "401");
        createAC(ac1, "\u957f\u671f\u501f\u6b3e", "501");
        createAC(ac1, "\u5e94\u4ed8\u503a\u5238", "502");
        createAC(ac1, "\u957f\u671f\u5e94\u4ed8\u6b3e", "701");
        createAC(ac1, "\u672a\u786e\u8ba4\u878d\u8d44\u8d39\u7528", "702");
        createAC(ac1, "\u4e13\u9879\u5e94\u4ed8\u6b3e", "711");
        createAC(ac1, "\u9884\u8ba1\u8d1f\u503a", "801");
        createAC(ac1, "\u9012\u5ef6\u6240\u5f97\u7a0e\u8d1f\u503a", "901");

        AccountClassification ac2 = createAC(ac, "\u5171\u540c", "3");
        createAC(ac2, "\u884d\u751f\u5de5\u5177", "101");
        createAC(ac2, "\u5957\u671f\u5de5\u5177", "201");
        createAC(ac2, "\u88ab\u5957\u671f\u9879\u76ee", "202");

        AccountClassification ac3 = createAC(ac, "\u6240\u6709\u8005\u6743\u76ca", "4");
        createAC(ac3, "\u5b9e\u6536\u8d44\u672c", "001");
        createAC(ac3, "\u8d44\u672c\u516c\u79ef", "002");
        createAC(ac3, "\u76c8\u4f59\u516c\u79ef", "101");
        createAC(ac3, "\u672c\u5e74\u5229\u6da6", "103");
        createAC(ac3, "\u5229\u6da6\u5206\u914d", "104");
        createAC(ac3, "\u5e93\u5b58\u80a1", "201");

        AccountClassification ac4 = createAC(ac, "\u6210\u672c", "5");
        createAC(ac4, "\u751f\u4ea7\u6210\u672c", "001");
        createAC(ac4, "\u5236\u9020\u8d39\u7528", "101");
        createAC(ac4, "\u52b3\u52a1\u6210\u672c", "201");
        createAC(ac4, "\u7814\u53d1\u652f\u51fa", "301");

        AccountClassification ac5 = createAC(ac, "\u635f\u76ca", "6");
        createAC(ac5, "\u4e3b\u8425\u4e1a\u52a1\u6536\u5165", "001");
        createAC(ac5, "\u5176\u5b83\u4e1a\u52a1\u6536\u5165", "051");
        createAC(ac5, "\u516c\u5141\u4ef7\u503c\u53d8\u52a8\u635f\u76ca", "101");
        createAC(ac5, "\u6295\u8d44\u6536\u76ca", "111");
        createAC(ac5, "\u8425\u4e1a\u5916\u6536\u5165", "301");
        createAC(ac5, "\u4e3b\u8425\u4e1a\u52a1\u6210\u672c", "401");
        createAC(ac5, "\u5176\u5b83\u4e1a\u52a1\u6210\u672c", "402");
        createAC(ac5, "\u8425\u4e1a\u7a0e\u91d1\u53ca\u9644\u52a0", "403");
        createAC(ac5, "\u9500\u552e\u8d39\u7528", "601");
        createAC(ac5, "\u7ba1\u7406\u8d39\u7528", "602");
        createAC(ac5, "\u8d22\u52a1\u8d39\u7528", "603");
        createAC(ac5, "\u8d44\u4ea7\u51cf\u503c\u635f\u5931", "701");
        createAC(ac5, "\u8425\u4e1a\u5916\u652f\u51fa", "711");
        createAC(ac5, "\u6240\u5f97\u7a0e\u8d39\u7528", "801");
        createAC(ac5, "\u4ee5\u524d\u5e74\u5ea6\u635f\u76ca\u8c03\u6574", "901");

        saveAC(ac);

        Ledger l = new Ledger();
        l.setName(I18NMessage.getMessage("ledger.template1"));
        l.setLevel((short) 2);
        l.setIsTemplate((short) 1);
        l.setC1Part1Len((short) 1);
        l.setC1Part2Len((short) 3);
        l.setAccountClassification(ac);
        getSessionFactory().getCurrentSession().saveOrUpdate(l);
    }

    private void saveAC(AccountClassification ac) {
        getCS().saveOrUpdate(ac);
        for (AccountClassification a : ac.getChildren()) {
            saveAC(a);
        }
    }

}
