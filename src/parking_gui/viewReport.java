package parking_gui;
public class viewReport {
    int id_operator,start_shift,end_shift ,id_customer;
    String username_operator,plate_number;
    int cost;
    viewReport(int id_operator,String username_operator,int start_shift,int end_shift ,int id_customer,String plate_number,int cost)
    {
        this.id_operator=id_operator;
        this.start_shift=start_shift;
        this.end_shift=end_shift; 
        this.id_customer=id_customer;
        this.plate_number=plate_number;
        this.username_operator=username_operator;
        this.cost =cost;
    }
}