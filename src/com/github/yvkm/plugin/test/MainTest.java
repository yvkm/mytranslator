package com.github.yvkm.plugin.test;

import com.github.yvkm.plugin.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ServiceLoader;

/**
 * @author Xie Jian Xun
 */
public class MainTest {
    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) {
        ServiceLoader<Translator> serviceLoader =
            ServiceLoader.load(Translator.class);

            log.trace("test");
        serviceLoader.iterator().forEachRemaining(System.out::println);
    }
}
