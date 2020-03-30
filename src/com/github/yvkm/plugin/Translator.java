package com.github.yvkm.plugin;


/**
 * @author xie jian xun
 * @since
 */
public interface Translator extends Order{

    /**
     * 翻译指定的文本
     * @param rawText
     * @return
     */
    String translate(String rawText) throws Exception;


}
