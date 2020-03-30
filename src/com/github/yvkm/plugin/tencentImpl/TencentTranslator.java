package com.github.yvkm.plugin.tencentImpl;

import com.github.yvkm.plugin.AbstractTranslator;
import com.github.yvkm.plugin.Order;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.tmt.v20180321.TmtClient;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateRequest;
import com.tencentcloudapi.tmt.v20180321.models.TextTranslateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
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
    public String translate(String sText) throws TencentCloudSDKException {
        final String text = process(sText);

        String params = "{\"SourceText\":\"" + text + "\",\"Source\":\"en" +
            "\",\"Target\":\"zh\",\"ProjectId\":0}";
        log.trace("请求参数：{}", params);
        TextTranslateRequest req = TextTranslateRequest.fromJsonString(params, TextTranslateRequest.class);

        TextTranslateResponse resp = client.TextTranslate(req);

        return resp.getTargetText();
    }

    @Override
    public int getOrder() {
        return Order.DEFAULT_PRIORITY;
    }
}
