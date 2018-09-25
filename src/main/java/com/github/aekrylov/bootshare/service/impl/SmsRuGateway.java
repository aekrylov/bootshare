package com.github.aekrylov.bootshare.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.aekrylov.bootshare.service.OtpException;
import com.github.aekrylov.bootshare.service.SmsGateway;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.MultipartBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * By Anton Krylov (anthony.kryloff@gmail.com)
 * Date: 9/21/18 11:00 PM
 */
@Component
public class SmsRuGateway implements SmsGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger("sms-gateway");
    private static final String BASE_URL = "https://sms.ru/sms/";

    static {
        setupUnirest();
    }

    private final String clientId;
    private final boolean testMode;

    @Autowired
    public SmsRuGateway(OtpProperties otpProperties) {
        this.clientId = otpProperties.getSmsRu().getClientId();
        this.testMode = otpProperties.isTestMode();
    }

    @Override
    public void sendSms(String to, String text) {
        LOGGER.debug("Sending to {}: {}", to, text);

        Map<String, Object> params = new HashMap<>();
        params.put("to", to.replace("+", ""));
        params.put("msg", text);

        try {
            SmsRuResponse response = request("send", params).getBody();
            if(!"OK".equals(response.getStatus())) {
                throw new OtpException("Error sending SMS: SMS.RU returned status" + response.getStatus_code());
            }
        } catch (UnirestException e) {
            throw new OtpException(e);
        }
    }

    private static void setupUnirest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private HttpResponse<SmsRuResponse> request(String method, Map<String, Object> params) throws UnirestException {
        MultipartBody body = Unirest.post(BASE_URL + method)
                .fields(params)
                .field("api_id", clientId)
                .field("json", 1);

        if(testMode) {
            body.field("test", 1);
        }
        
        return body.asObject(SmsRuResponse.class);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class SmsRuResponse {

        private String status;
        private int status_code;
        private double balance;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
