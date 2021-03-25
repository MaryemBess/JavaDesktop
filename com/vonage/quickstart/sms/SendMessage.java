package com.vonage.quickstart.sms;


import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;

import static com.vonage.quickstart.sms.Util.configureLogging;
import static com.vonage.quickstart.sms.Util.envVar;

public class SendMessage {

    public void sms() throws Exception {
        configureLogging();

        String VONAGE_API_KEY = envVar("6fb6fad4");
        String VONAGE_API_SECRET = envVar("Li3JSkaeVSNcN3oE");
        String TO_NUMBER = envVar("21624669743");
        String VONAGE_BRAND_NAME = envVar("Vonage APIs");

        VonageClient client = VonageClient.builder().apiKey(VONAGE_API_KEY).apiSecret(VONAGE_API_SECRET).build();

        TextMessage message = new TextMessage(VONAGE_BRAND_NAME,
                TO_NUMBER,
                "i love you"
        );

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }
    }
}