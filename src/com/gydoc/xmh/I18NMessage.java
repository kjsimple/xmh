package com.gydoc.xmh;

import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 */
public class I18NMessage {

    private static Object[] empty = new Object[0];

    public static String getMessage(String key) {
        return getMessageSource().getMessage(key, empty, Locale.getDefault());
    }

    public static String getMessage(String key, Object[] args) {
        return getMessageSource().getMessage(key, args, Locale.getDefault());
    }

    private static synchronized MessageSource getMessageSource() {
        return (MessageSource) SpringUtil.getBean("messageSource");
    }

}
