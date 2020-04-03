package com.github.yvkm.plugin.test;

import com.github.yvkm.plugin.AbstractTranslator;
import com.github.yvkm.plugin.ServiceLoader;
import com.github.yvkm.plugin.TextProcessUtil;
import com.github.yvkm.plugin.Translator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.ServiceNotFoundException;
import java.util.List;


/**
 * @author Xie Jian Xun
 */
public class MainTest {
    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    public static void main(String[] args) throws ServiceNotFoundException {
//        testPattern();
//        testServerLoader();
        testIsChinese();
    }

    public static void testServerLoader() throws ServiceNotFoundException {
        List<Translator> load = ServiceLoader.load(Translator.class);
        assert load.size() > 0;
    }

    public static void testPattern() {

        String[] str = {
            "/* this is a multiline comment. */",
            "// this is a line comment.",
            "**/ this is a \"java\" document! * another line. */",
            "{@code ThreadLocal#get}",
            "<pre> words. </pre>"
        };

        for (String s : str) {
            String replace = TextProcessUtil.replace(s, AbstractTranslator.REPLACE_PATTERN, " ");
            System.out.println(replace);
            assert !replace.matches("[/*<>@{}\"#]");
        }
    }

    public static void testIsChinese() {
        assert !TextProcessUtil.isChinese("hello");
        assert TextProcessUtil.isChinese("中国");
    }
}
