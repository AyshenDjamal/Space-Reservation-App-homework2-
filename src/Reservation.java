import java.io.Serializable;

public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    public int bookingID;
    public String customerName;
    public String date;
    public String startTime;
    public String endTime;

    public Reservation(int bookingID, String customerName, String date, String startTime, String endTime){
        this.bookingID = bookingID;
        this.customerName = customerName;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

