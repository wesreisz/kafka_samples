package com.wesleyreisz.samples.kafka;

/**
 * Created by wesleyreisz on 10/7/15.
 */
public class MyMessage {
    private String ip;
    private String message;

    public MyMessage(String ip, String message) {
        this.ip = ip;
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyMessage myMessage = (MyMessage) o;

        if (ip != null ? !ip.equals(myMessage.ip) : myMessage.ip != null) return false;
        return !(message != null ? !message.equals(myMessage.message) : myMessage.message != null);

    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "ip='" + ip + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
