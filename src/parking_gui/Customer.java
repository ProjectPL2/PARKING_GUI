package Parking_GUI;

import java.sql.*;

public class Customer extends Station{
     static float cost;
     static int result;
     static boolean IsExist;
    public void printTicket(int id){
         try {
             connect = security.getConnection();
             query = "select count(1) from customers where id_customer = "+id+"";
             st = connect.prepareStatement(query);
             r = st.executeQuery(query);
             while (r.next()) {
                 result = r.getInt("count(1)");
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
    }
    public void payment(int id){
      
         try{
         
             admin_DDL d=new admin_DDL();
             connect=security.getConnection();
             query = "select id_customer from customers where id_customer = '"+id+"'";
             st = connect.prepareStatement(query);
             r= st.executeQuery(query);
               if(r.next()){
                  cost=10*totalParkingHours(id);
                  d.report(id,cost);
                  removeCustomer(id);
                  IsExist=true;
                } 
               else{
                  IsExist=false;
               }
         }
         
         catch (SQLException ex){
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
    
   }
    public void removeCustomer(int id){
        String place = null;
        try {
            connect = security.getConnection();
            query = "select place from customers where id_customer = '"+id+"'";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            while (r.next()) {                
                place = r.getString("place");
            }
            query = "delete from customers where id_customer = '"+id+"'";
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
}
