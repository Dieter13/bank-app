package bank.app;

import java.util.Date;

public class ClientHistory {

    private String history;
    private Date eventDate;
    private String status;

    public ClientHistory(String history, String status) {
        this.history = history;
        this.status = status;
    }

    public ClientHistory(String history) {
        this.history = history;
        this.status = "STANDARD";
        //status standardowy
    }

    public String getHistory() {
        return history;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public String getStatus() {
        return status;
    }
}
