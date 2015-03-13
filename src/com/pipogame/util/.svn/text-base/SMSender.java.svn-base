package com.pipogame.util;

import com.pipogame.Debug;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * Module gửi tin nhắn.
 * @author HaiNH
 * @company B-Gate
 */
public class SMSender {

    /**
     * Gửi tin nhắn đi.
     * @param message nội dung tin nhắn.
     * @param phoneNumber đầu số cần gửi.
     * @return <code>true</code> nếu gửi thành công, <code>false</code> nếu 
     * không thành công.
     */
    public boolean send(String message, String phoneNumber) {
        StringBuffer addr = new StringBuffer(20);
        addr.append("sms://");
        addr.append(phoneNumber);
        // String address = "sms://8733";
        String address = addr.toString();
        boolean success = false;

        MessageConnection smsconn = null;
        try {
            // Open the message connection.
            smsconn = (MessageConnection) Connector.open(address);
            // Create the message.
            TextMessage txtmessage = (TextMessage) smsconn
                    .newMessage(MessageConnection.TEXT_MESSAGE);
            txtmessage.setAddress(address);// !!
            txtmessage.setPayloadText(message);
            
            smsconn.send(txtmessage);
            success = true;
        } catch (Exception e) {
            if (Debug.ENABLE) {
                e.printStackTrace();
            }
        }

        if (smsconn != null) {
            try {
                smsconn.close();
            } catch (Exception ioe) {
                if (Debug.ENABLE) {
                    ioe.printStackTrace();
                }
            }
        }
        
        return success;
    }
    
    /**
     * Gửi tin nhắn đi.
     * @param message nội dung tin nhắn.
     * @param phoneNumber đầu số cần gửi.
     */
    public boolean send(String message, int phoneNumber) {
        return send(message, Integer.toString(phoneNumber));
    }
}
