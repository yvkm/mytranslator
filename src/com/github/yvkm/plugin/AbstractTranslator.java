package com.github.yvkm.plugin;


import com.github.yvkm.plugin.util.TextProcessUtil;

/**
 * @author xie jian xun
 * @since
 */
public abstract class AbstractTranslator implements Translator {

    private static final String COMMENT = "/|\\*";
    private static final String JAVADOC =
        "\\#|\\{@[a-zA-Z]+|\\}|</?[a-z]+?>";
    private static final String OTHER = "\"|_";
    public static final String REPLACE_PATTERN =
        COMMENT + "|" + JAVADOC + "|" + OTHER;

    /**
     * 将指定的英文翻译成中文
     *
     * @param text
     * @return
     * @throws Exception
     */
    public abstract String translate(String text) throws Exception;

    /**
     * 对要进行翻译的文本进行处理
     *
     * @param rawText
     * @return
     */
    protected String process(String rawText) {
        return TextProcessUtil.replace(rawText, REPLACE_PATTERN,
            " ");
    }


}
