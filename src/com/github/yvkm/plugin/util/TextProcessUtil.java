package com.github.yvkm.plugin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Xie Jian Xun
 */
public class TextProcessUtil {

    /**
     * 对指定的文本进行替换
     *
     * @return 返回替换后的字符串
     */
    public static String replace(String source, String pattern, String replacement) {
        String trimmedText = source.trim();
        String replacedText =
            trimmedText.replaceAll(pattern, " ")
                .replaceAll("\\s+", " ");
        if (isCamelCase(replacedText)) {
            return camelCaseToMultiWord(trimmedText);
        }
        return replacedText.toLowerCase();

    }

    public static boolean isCamelCase(String text) {
        return !text.contains(" ");
    }

    /**
     * 分割camelCase类型的字符串
     *
     * @param camelCase 驼峰式字符串
     * @return 分割后的驼峰字符串
     */
    public static String camelCaseToMultiWord(String camelCase) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitWords = camelCase.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String splitWord : splitWords) {
            stringBuilder.append(splitWord).append(" ");
        }
        return stringBuilder.toString().toLowerCase();
    }

    /**
     * 判断字符串是否为中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]+";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find())
            return true;
        else
            return false;
    }
}
