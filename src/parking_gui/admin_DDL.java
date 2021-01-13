package Parking_GUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class admin_DDL extends Station {
   
    
    Scanner input = new Scanner(System.in);
    public void insertOperator(){
        try{
            connect=security.getConnection();           
            System.out.println("Enter name: ");
            setOperatorUsername(input.next());
            System.out.println("Enter Start work shift: ");
            setStartShift(input.nextInt());
            System.out.println("Enter End work shift: ");
            setEndShift(input.nextInt());
            query = "insert into operators (username ,start_shift ,end_shift) values ('"+getOperatorUsername()+
                    "','"+ getStartShift()+"','"+ getEndShift()+"')";
            st = connect.prepareStatement(query);
            st.execute(query);
            System.out.println("INSERTED");
        } catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                connect.close();
                st.close();
                }
            catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
  
   void updateOperator(){
        try{
            ArrayList<element1> list=new ArrayList();
            connect=security.getConnection();
            System.out.println("Enter ID to Update: ");
            int id = input.nextInt();
            query="select * from operators where id = '"+id+"'";
              st= connect.prepareStatement(query);
            r=st.executeQuery(query);
        
             if(r.next()){
            System.out.println("Enter new start shift: ");
            setStartShift(input.nextInt());
            System.out.println("Enter new end shift: ");
           setEndShift(input.nextInt());
          
            while(r.next()){
                list.add(new element1(r.getInt("id"),r.getString("username"),r.getInt("start_shift"),r.getInt("end_shift")));
            }
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).id==id)
                {
                    query = "update operators set start_shift ='"+getStartShift()+"',end_shift ='"+getEndShift()+"' where id=('"+id+"')";
                    st = connect.prepareStatement(query);
                    st.execute(query);
                    System.out.println("UPDATED");
                }
            }
        } 
        else
            System.out.println("This Id Not Exist");
        }
        catch(SQLException ex){
        System.out.println(ex.getMessage());
        } 
        finally{
        try{
            connect.close();
           st.close();
            }
        catch(SQLException ex){
        System.out.println(ex.getMessage());
            }
        }
    }
   public void deleteOperator(){
        try{
            ArrayList<element1> list=new ArrayList();
            connect=security.getConnection();
            System.out.println("Enter ID to Delete: ");
            int id = input.nextInt();
            query="select* from operators where id = '"+id+"'";
            st= connect.prepareStatement(query);
            r=st.executeQuery(query);
        
            if(r.next()){
            while(r.next()){
                list.add(new element1(r.getInt("id"),r.getString("username"),r.getInt("start_shift"),r.getInt("end_shift")));
            }
            for(int i=0;i<list.size();i++)
            {
                if(list.get(i).id==id)
                {
                    query = "delete from operators where id ='"+id+"'";
                    st = connect.prepareStatement(query);
                    st.execute(query);
                    System.out.println("DELETED");
                }
                
            }
               
        } 
        else
            System.out.println("This Id Not Exist");
        }
        catch(SQLException ex){
        System.out.println(ex.getMessage());
        } 
        finally{
        try{
            connect.close();
           st.close();
           r.close();
            }
        catch(SQLException ex){
        System.out.println(ex.getMessage());
            }
        }
       
   }
    public void viewAllSpots()
    {  
        int size=spots.size();       
        ArrayList<String> key = new ArrayList<>(spots.keySet()); 
        Collections.sort(key);  
        ArrayList<String> free =new ArrayList<>();
        ArrayList<String> busy =new ArrayList<>();
        
        int flagFree=0,flagBusy=0;
   
        for(int i=0;i<size;i++)
        {
            if(spots.get(key.get(i)) == true)  
            {
                 free.add(key.get(i));  
                 flagFree=1;
            }   
            else
            {              
                busy.add(key.get(i));
                flagBusy=1;
            }
        }        
        if(flagFree==0)
            System.out.println("there are no free spots");
        else
        {
            System.out.println("the free spots are :");
            for (int i = 0; i < free.size(); i++)  
            {
                System.out.print(free.get(i) + " ");
            }          
        }
        System.out.println("\n------------------------------------");
        if(flagBusy==0)   
            System.out.println("there are no busy spots");
        else
        {
            System.out.println("the busy spots are :");
            for (int i = 0; i < busy.size(); i++)  
            {
                System.out.print(busy.get(i) + " ");
            }
           System.out.print("\n");
        }                     
    }
    void viewParkedCar(){
        try{
            ArrayList<element> list=new ArrayList();
            connect=security.getConnection();
            query = "select id_customer,plate_number,totalspots.place,start_dateH,start_dateM from customers"
                    + " join totalspots on totalspots.place = customers.place"
                    + " where totalspots.state = 'false'";
            st = connect.prepareStatement(query);
            r=st.executeQuery(query);
            if(r.next()){
            while(r.next()){
                list.add(new element(r.getInt("id_customer"),r.getString("plate_number")
                                    ,r.getString("totalspots.place"),r.getInt("start_dateH"),r.getInt("start_dateM")));
            }
            for(int i=0;i<list.size();i++)
            {
                System.out.print(list.get(i).username+"|" + list.get(i).id_customer+"|"+list.get(i).plate_number+"|"+
                                 list.get(i).place+"|" +list.get(i).start_dateH+"|"+list.get(i).start_dateM+"|"  );
            }
            }
            else
                System.out.println("There Isn't Parked Cars");
            
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        } 
        finally{
            try{
            connect.close();
            st.close();
        }
            catch(SQLException ex){
               System.out.println(ex.getMessage());
            }
        }
    }
    public void report(Customer e){
         try {
          Operators o=new Operators();
            connect=security.getConnection();
            query="select operators.id,username,start_shift,end_shift,plate_number from customers join operators "
                    + " on customers.id_operator=operators.id where id_customer='"+e.getId()+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
            if(r.next()){
            while(r.next()){
            
                   query="insert into old_customers values(id_operator='"+r.getInt("operators.id")+"',"
                           + " username_operator='"+r.getString("username")+"',start_shift='"+r.getInt("start_shift")+"',"
                           + " end_shift='"+r.getInt("end_shift")+"',plate_number='"+r.getString("plate_number")+"',cost='"+10*o.totalParkingHours(e)+"'";
               }         
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
    
}


