package Parking_GUI;

public class element {
 
    public String username;
    public int id_customer;
    public String plate_number;
    public String place;
    public int start_dateH;
    public int start_dateM;

    public element(int id_customer, String plate_number, String place, int start_dateH, int start_dateM) {
        this.id_customer = id_customer;
        this.plate_number = plate_number;
        this.place = place;
        this.start_dateH = start_dateH;
        this.start_dateM = start_dateM;
    }
 
}
