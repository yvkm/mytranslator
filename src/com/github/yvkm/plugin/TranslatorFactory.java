package com.github.yvkm.plugin;


import javax.management.ServiceNotFoundException;
import java.util.Comparator;
import java.util.List;

/**
 * 发现并返回Translator实现类。
 * @author xie jian xun
 * @since
 */
public final class TranslatorFactory {
    public static Translator getTranslator() throws ServiceNotFoundException {
        // 返回优先级别最高的translator
        return getTranslators().get(0);
    }

    public static List<Translator> getTranslators() throws ServiceNotFoundException {
        List<Translator> translatorList = ServiceLoader.load(Translator.class);
        translatorList.sort(Comparator.comparing(Translator::getOrder));
        return translatorList;
    }

}
