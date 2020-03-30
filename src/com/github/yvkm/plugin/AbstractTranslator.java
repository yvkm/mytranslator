package com.github.yvkm.plugin;


/**
 * @author xie jian xun
 * @since
 */
public abstract class AbstractTranslator implements Translator {

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
        String trimmedText = rawText.trim();
        if(isOneWord(trimmedText)) {
            return  camelCaseToMultiWord(trimmedText);
        }

        String replacedText = trimmedText.replaceAll("\\{|\\}|@[a-zA-Z]+|\\<.*\\>|#|\\*", " ")
                .replaceAll("\\s+", " ");
        return replacedText;
    }

    protected   boolean isOneWord(String text)  {
        return !text.contains(" ");
    }

    protected String camelCaseToMultiWord(String camelCase) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] splitWords = camelCase.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
        for (String splitWord : splitWords) {
            stringBuilder.append(splitWord).append(" ");
        }
        return stringBuilder.toString();
    }
}
