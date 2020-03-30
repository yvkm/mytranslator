package com.github.yvkm.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;


/**
 * @author xie jian xun
 * @since 1.0
 */
public class Bootstrap extends AnAction {
    private static final Logger log = LoggerFactory.getLogger(Bootstrap.class);
    private Translator translator;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Editor editor = anActionEvent.getData(PlatformDataKeys.EDITOR);
        if (editor == null) {
            log.error("Cannot get IDEA build-in object: PlatformDataKeys" +
                ".EDITOR");
            return;

        }
        String sText = editor.getSelectionModel().getSelectedText();

        log.trace("Request translate text: {}", sText);

        try {
            if(translator == null) {
               translator = TranslatorFactory.getTranslator();
            }
            String translated = translator.translate(sText);

            showTip(translated, editor);
        } catch (Exception e) {
            showTip("Translate error :" + e.getMessage(), editor);
        }
    }

    private void showTip(String msg, Editor editor) {
        ApplicationManager.getApplication().invokeLater(() -> JBPopupFactory.getInstance()
            .createHtmlTextBalloonBuilder(msg, Messages.getInformationIcon(),
                new JBColor(new Color(214, 241, 255), Gray._43)
                , null)
            .setFadeoutTime(60000)  // 自动退出时间
            .setHideOnAction(true)  // 离开后退出
            .createBalloon()
            .show(JBPopupFactory.getInstance()
                .guessBestPopupLocation(editor), Balloon.Position.below));
    }
}
