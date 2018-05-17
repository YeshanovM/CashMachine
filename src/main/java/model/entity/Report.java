package model.entity;

import java.util.*;

public class Report implements Entity {
    private int id;
    private double incomes, expenses;
    private boolean isClosed;
    private Date startTime, endTime;

    public Report() {
    }

    public Report(int id, double incomes, double expenses, boolean isClosed, Date startTime, Date endTime) {
        this.id = id;
        this.incomes = incomes;
        this.expenses = expenses;
        this.isClosed = isClosed;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(double incomes) {
        this.incomes = incomes;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Report)) return false;
        Report report = (Report) o;
        return id == report.id &&
                Double.compare(report.incomes, incomes) == 0 &&
                Double.compare(report.expenses, expenses) == 0 &&
                isClosed == report.isClosed &&
                Objects.equals(startTime, report.startTime) &&
                Objects.equals(endTime, report.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, incomes, expenses, isClosed, startTime, endTime);
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", incomes=" + incomes +
                ", expenses=" + expenses +
                ", isClosed=" + isClosed +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
