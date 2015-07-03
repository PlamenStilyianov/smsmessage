package com.fingenius.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Message implements SmsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Override
    public Long getId() {
        return id;
    }

    private String messageSid;
    private String smsMessageSid;
    private String smsStatus;
    private String smsSid;
    private String Body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSend;
    private String fromNumber;
    private String toNumber;

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageSid() {
        return messageSid;
    }

    public void setMessageSid(String messageSid) {
        this.messageSid = messageSid;
    }

    public String getSmsMessageSid() {
        return smsMessageSid;
    }

    public void setSmsMessageSid(String smsMessageSid) {
        this.smsMessageSid = smsMessageSid;
    }

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
    }

    public String getSmsSid() {
        return smsSid;
    }

    public void setSmsSid(String smsSid) {
        this.smsSid = smsSid;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String body) {
        Body = body;
    }

    public Date getDateSend() {
        return dateSend;
    }

    public void setDateSend(Date dateSend) {
        this.dateSend = dateSend;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }
}
