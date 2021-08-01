package Parking_GUI;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class admin_DDL extends Station {
   
    static void insertOperator(String name,int Sshift,int Eshift){
      
        try {
            connect=security.getConnection();
            query="insert into test (username,start_shift,end_shift) values ('"+name+"','"+Sshift+"','"+Eshift+"')";
            st = connect.prepareStatement(query);
            int result=st.executeUpdate(query);
            if (result==1) {
                JOptionPane.showMessageDialog(null, "INSERTED SUCCESSFULLY", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "MySQL Connector Not Found!!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
   
    static void showOperator(DefaultTableModel dtm){
              
        try {
            connect=security.getConnection();
            query="select * from operators";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            while(r.next()){
                dtm.addRow(new Object[] {r.getInt("id"),r.getString("username"),r.getInt("start_shift"),r.getInt("end_shift")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "MySQL Connector Not Found!!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    static void viewParkedCar(DefaultTableModel asd){
        try {
          
          connect=security.getConnection();
          query="select id_customer,plate_number,totalspots.place,start_dateH,start_dateM from customers"
                    + " join totalspots on totalspots.place = customers.place"
                    + " where totalspots.state = 'false'";
              st = connect.prepareStatement(query);
          r=st.executeQuery(query);
            while(r.next()){
                asd.addRow(new Object[] {r.getInt("id_customer"),r.getString("plate_number")
                                    ,r.getString("totalspots.place"),r.getInt("start_dateH"),r.getInt("start_dateM")});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "MySQL Connector Not Found!!","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    
    public void report(int id,float cost){
         try {
            connect=security.getConnection();
            query="select customers.id_operator,username,start_shift,end_shift,customers.plate_number from operators join customers "
                    + " on customers.id_operator=operators.id where id_customer = '"+id+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
           while(r.next()){
             
               query="insert into old_customers(id_operator,username_operator,start_shift,end_shift,plate_number,cost)"
                       + " values('"+r.getInt("customers.id_operator")+"','"+r.getString("username")+"','"+r.getInt("start_shift")+"',"
                       + "'"+r.getInt("end_shift")+"','"+r.getString("customers.plate_number")+"','"+cost+"')";
               st=connect.prepareStatement(query);
               st.execute(query);
               }         
            
         }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                st.close();
                connect.close();
                r.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
         }
   public int addSpots(int nFloor,int nSpots){
       int count=0;
       try{
           int result=0;
         connect=security.getConnection();
         query="select count(1) from totalspots";
         st=connect.prepareStatement(query);
         r=st.executeQuery(query);
         while(r.next())
              result = r.getInt("count(1)");
         if(result!=0){
             count=0;
         }
         else{
             Station.createParking(nFloor, nSpots);
             count=1;
         }
       }
       catch(SQLException ex){
           System.out.println(ex.getMessage());
   }
       return count;
}
   
}


