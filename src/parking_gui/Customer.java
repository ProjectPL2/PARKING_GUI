package Parking_GUI;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Customer extends Station{
    private int id;
    private String plateNumber;
    private String place;
    private int startDateH;
    private int startDateM;
    private int endDateH;
    private int endDateM;

    
     Scanner input =new Scanner(System.in).useLocale(Locale.US);
    
    public void setId(int id) {
        this.id = id;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setStartDateH(int startDateH) {
        this.startDateH = startDateH;
    }

    public void setEndDateH(int endDateH) {
        this.endDateH = endDateH;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }
    
    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getStartDate() {
        return startDateH;
    }

    public int getEndDate() {
        return endDateH;
    }       

    public int getStartDateH() {
        return startDateH;
    }

    public int getStartDateM() {
        return startDateM;
    }

    public int getEndDateH() {
        return endDateH;
    }

    public int getEndDateM() {
        return endDateM;
    }

    public void setStartDateM(int startDateM) {
        this.startDateM = startDateM;
    }

    public void setEndDateM(int endDateM) {
        this.endDateM = endDateM;
    }
    
  

    public void getTicket(){
        System.out.println("------------------------------------------------------");
        System.out.println("|                                                    |");
        System.out.println("|            |-------------------------|             |");
        System.out.println("|            |WELCOM TO PARKING STATION|             |");
        System.out.println("|            |-------------------------|             |");        
        System.out.println("|                                                    |");
        System.out.println("|     Customer Id : "+this.id+"                       \t\t|");
        System.out.println("|     Plate Number : "+this.plateNumber+"             \t\t|");
        System.out.println("|     Date : "+this.startDateH+":"+this.startDateM+
                           "             \t\t|");
        System.out.println("|                                                    |");
        System.out.println("-----------------------------------------------------");
    }
  
    /*
     public void payment(int id){
        try {
            Operators o=new Operators();
            admin_DDL d=new  admin_DDL();
            connect = security.getConnection();
            query = "select * from customers where id_customer = '"+id+"'";
            Customer c = new Customer();
            st = connect.prepareStatement(query);
            r = st.executeQuery(query);
            while(r.next()){                
                if(r.next()){
                    c.setId(id);
                    c.setStartDateH(r.getInt("start_dateH"));
                    c.setStartDateM(r.getInt("start_dateM"));
                    c.setEndDateH(r.getInt("end_dateH"));
                    c.setEndDateM(r.getInt("end_dateM"));
                    double payment,exchange;
                    double cost=10*o.totalParkingHours(c);
                    System.out.println("Enter the Payment: ");
                    payment=input.nextDouble();
                    if(payment==cost){
                        System.out.println("Payment Successfully");
                    }else{
                        exchange=payment-cost;
                        System.out.println("Payment Successfully");
                        System.out.println("Entered:"+payment);
                        System.out.println("The Exchange is:"+exchange);
                    } 
                    d.report(c);
                    o.removeCustomer(c);
                    
                }
                    else
                        System.out.println("This Id Not Found ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
}
