package com.github.yvkm.plugin.tencentImpl;

import com.github.yvkm.plugin.AbstractTranslator;
import com.github.yvkm.plugin.Order;
import com.github.yvkm.plugin.util.TextProcessUtil;
import com.google.gson.Gson;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * https://cloud.tencent.com/document/api/551/15619
 *
 * @author xie jian xun
 * @since
 */
public class TencentTranslator extends AbstractTranslator {

    private static final Logger log = LoggerFactory.getLogger(TencentTranslator.class);

    private static TmtClient client;

    static {
        String secretId = PropertyUtil.getProperty("tencent.secretId");
        String secretKey = PropertyUtil.getProperty("tencent.secretKey");
        Credential cred = new Credential(secretId, secretKey);

        HttpProfile httpProfile = new HttpProfile();
        httpProfile.setEndpoint("tmt.tencentcloudapi.com");

        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setHttpProfile(httpProfile);
        client = new TmtClient(cred, "ap-guangzhou-open", clientProfile);
    }

    @Override
    public String translate(String rawText) throws TencentCloudSDKException {
        final String text = process(rawText);

        boolean isChinese = TextProcessUtil.isChinese(text);
        String source = isChinese ? "zh" : "en";
        String target = isChinese ? "en" : "zh";
        HashMap<String, Object> map = new HashMap<>();
        map.put("SourceText", text);
        map.put("Source", source);
        map.put("Target", target);
        map.put("ProjectId",0);

        String params = new Gson().toJson(map);

        TextTranslateRequest req = TextTranslateRequest.fromJsonString(params, TextTranslateRequest.class);
        TextTranslateResponse resp = client.TextTranslate(req);

        return resp.getTargetText();
    }

    @Override
    public int getOrder() {
        return Order.DEFAULT_PRIORITY;
    }
}
