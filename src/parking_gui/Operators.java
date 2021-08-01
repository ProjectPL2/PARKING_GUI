package Parking_GUI;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class Operators extends Station{
    
     static String betterPlace;
    
    public void getFreeSpots(){
        try{
            ArrayList<String> list=new ArrayList();
            connect=security.getConnection();
            query = "select * from totalspots where state = 'true'";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            while(r.next()){
                list.add(r.getString("place"));
              }
               betterPlace=list.get(0);
           }
      
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                connect.close();
                st.close();
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
            
        }
    }
    
    
    public boolean addCustomer(String place,String plateNumber){
        try {
            final int YEAR = Calendar.getInstance().get(Calendar.YEAR);
            final int MONTH = Calendar.getInstance().get(Calendar.MONTH)+1;
            final int DAY = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            String date = YEAR+"-"+MONTH+"-"+DAY;
            int id = getCustomerId();
            connect = security.getConnection();
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
           
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connect.close();
                st.close();
                r.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
        } 
         return false;
 
    }
   public static int getCustomerId(){ 
         int f=0;
        try {
          
               connect = security.getConnection();
               query="select * from totalSpots";
               st=connect.prepareStatement(query);
               r=st.executeQuery(query);
               int size=0;
              while(r.next()){
                  size++;
              }
          for(int i=0;i<size;i++){
            int id = (int)(1+Math.random()*size); 
            query = "select id_customer from customers where id_customer = '" + id+"'";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            if(!r.next()){
              f=id;
            }
            r.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            try {
                connect.close();
                st.close();
                r.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }      
      return f;
    }
}