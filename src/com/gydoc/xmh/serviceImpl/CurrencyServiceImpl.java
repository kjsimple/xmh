package com.gydoc.xmh.serviceImpl;

import com.gydoc.xmh.domain.Currency;
import com.gydoc.xmh.service.CurrencyService;
import java.util.Set;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Transactional(propagation=Propagation.REQUIRED)
public class CurrencyServiceImpl extends ServiceBase implements CurrencyService {

    public Set<Currency> addOrUpdateCurrencies(Set<Currency> currencies) {
        for (Currency c : currencies) {
            getSessionFactory().getCurrentSession().saveOrUpdate(c);
        }
        return currencies;
    }

}
