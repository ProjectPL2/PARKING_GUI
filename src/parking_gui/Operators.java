package Parking_GUI;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;

public class Operators extends Station{
    
    
    private final Scanner input = new Scanner(System.in);
    
    
    public void getFreeSpots(){
        int size=spots.size();
        
        ArrayList<String> key = new ArrayList<>(spots.keySet()); 
        Collections.sort(key); 
        ArrayList<String> free =new ArrayList<>();
        
        int flag =0; //to check if there are free spots or not
        for(int i=0;i<size;i++)
        {
            if(spots.get(key.get(i)) == true)
            {
                flag=1;
                free.add(key.get(i));
            }        
        }   
        if(flag==0)
            System.out.println("sorry there is no free spot");
        else
        {
            System.out.println("the free spots are :");
            for (int i = 0; i < free.size(); i++)  
            {
                System.out.print(free.get(i) + " ");
            }
              
           System.out.println("\nwhat do you think of the place (\"" + free.get(0) +"\")? " + "I think it's better");
        }      
           
    }
    
    public boolean addCustomer(String place,String plateNumber){
        try {
            final int YEAR = Calendar.getInstance().get(Calendar.YEAR);
            final int MONTH = Calendar.getInstance().get(Calendar.MONTH)+1;
            final int DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            String date = YEAR+"-"+MONTH+"-"+DAY;
            connect = security.getConnection();
            int id = getCustomerId();
            query = "INSERT INTO customers(id_operator,id_customer,plate_number,place,start_dateH,start_dateM,date)"
                    + " VALUES ('"+Login.OperatorId+"','"+id+"','"+plateNumber+"','"+place+"','"+Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                    +"','"+Calendar.getInstance().get(Calendar.MINUTE)+"','"+date+"')";
            st = connect.prepareStatement(query);
            if(!st.execute(query)){
                query = "UPDATE totalspots SET state='false' WHERE place='"+place+"'";
                st = connect.prepareStatement(query);
                st.execute(query);
                return true;
            } 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }
    public void removeCustomer(Customer c){
        c.setEndDateH(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
        c.setEndDateM(Calendar.getInstance().get(Calendar.MINUTE));
        String place = null;
        try {
            connect = security.getConnection();
            query = "select place from customers where id_customer = '"+c.getId()+"'";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            while (r.next()) {                
                place = r.getString("place");
            }
            spots.replace(place, Boolean.TRUE);
            query = "delete from customers where id_customer = '"+c.getId()+"'";
            st.execute(query);
            query="update totalspots set state = 'true' where place = '"+place+"'";
            st.execute(query); 

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                connect.close();
                st.close();
                r.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
  
        
    }
    
    
    public int totalParkingHours(Customer c){
        if (c.getEndDateM() >= 30)
            return (c.getEndDateH()-c.getStartDateH()+1);
        else
            return (c.getEndDateH()-c.getStartDateH());

    }    
 
   public static int getCustomerId(){ 
         int f=0;
        try {
          
               connect = security.getConnection();
               query="select * from totalSpots";
               st=connect.createStatement();
               r=st.executeQuery(query);
               int size=0;
              while(r.next()){
                  size++;
              }
              System.out.println(size);
          for(int i=0;i<size;i++){
            int id = (int)(1+Math.random()*size); 
            query = "select id_customer from customers where id_customer = '" + id+"'";
            st = connect.createStatement();
            r=st.executeQuery(query);
            if(!r.next()){
              f=id;
            }
            r.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
   
        }
      return f;
    }
}
