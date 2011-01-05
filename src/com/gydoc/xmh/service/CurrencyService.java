package com.gydoc.xmh.service;

import com.gydoc.xmh.domain.Currency;
import java.util.Set;

/**
 *
 */
public interface CurrencyService {

    Set<Currency> addOrUpdateCurrencies(Set<Currency> currencies);

}
