package model.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Check implements Entity {
    private int id;
    private String cashierId;
    private boolean isCanceled;
    private Timestamp timestamp;

    public Check() {
    }

    public Check(int id, String cashierId, boolean isCanceled, Timestamp timestamp) {
        this.id = id;
        this.cashierId = cashierId;
        this.isCanceled = isCanceled;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled(boolean canceled) {
        isCanceled = canceled;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Check)) return false;
        Check check = (Check) o;
        return id == check.id &&
                isCanceled == check.isCanceled &&
                Objects.equals(cashierId, check.cashierId) &&
                Objects.equals(timestamp, check.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cashierId, isCanceled, timestamp);
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", cashierId='" + cashierId + '\'' +
                ", isCanceled=" + isCanceled +
                ", timestamp=" + timestamp +
                '}';
    }
}
