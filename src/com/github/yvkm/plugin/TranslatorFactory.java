package com.github.yvkm.plugin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.ServiceNotFoundException;
import java.util.Comparator;
import java.util.List;

/**
 * 发现并返回Translator实现类。
 * @author xie jian xun
 * @since
 */
public final class TranslatorFactory {
    private static final Logger log = LoggerFactory.getLogger(TranslatorFactory.class);
    public static Translator getTranslator() throws ServiceNotFoundException {
        // 返回优先级别最高的translator
        Translator translator = getTranslators().get(0);
        log.trace("Return translator: {}",translator);
        return translator;
    }

    public static List<Translator> getTranslators() throws ServiceNotFoundException {
        List<Translator> translatorList = ServiceLoader.load(Translator.class);
        translatorList.sort(Comparator.comparing(Translator::getOrder));
        log.trace("Discover translator implements ：{}",translatorList);
        return translatorList;
    }

}
