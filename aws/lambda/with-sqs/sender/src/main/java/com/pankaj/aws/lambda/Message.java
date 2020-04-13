package com.pankaj.aws.lambda;

/**
 * Created by pankaj on 4/13/2020.
 */
public class Message {
    private String txtMessage;
    private String subject;

    public String getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Message(String txtMessage, String subject) {
        this.txtMessage = txtMessage;
        this.subject = subject;
    }
}
