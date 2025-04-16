package pojo;

import java.time.LocalTime;

public class CallLog {
    private String phone;
    private LocalTime startTime;
    private int duration;

    public String getPhone() {
        return phone;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public CallLog(String phone, LocalTime startTime, int duration) {
        this.phone = phone;
        this.startTime = startTime;
        this.duration = duration;
    }
}