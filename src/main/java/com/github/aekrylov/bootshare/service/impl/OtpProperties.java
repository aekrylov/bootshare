package com.github.aekrylov.bootshare.service.impl;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/22/18 12:59 AM
 */
@Component
@ConfigurationProperties(prefix = "otp")
public class OtpProperties {

    private Duration codeTtl;

    private boolean testMode;

    private final SmsRu smsRu = new SmsRu();

    public static class SmsRu {

        private String clientId;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }
    }

    public Duration getCodeTtl() {
        return codeTtl;
    }

    public void setCodeTtl(Duration codeTtl) {
        this.codeTtl = codeTtl;
    }

    public boolean isTestMode() {
        return testMode;
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public SmsRu getSmsRu() {
        return smsRu;
    }
}
