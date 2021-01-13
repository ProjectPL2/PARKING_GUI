package Parking_GUI;
import java.sql.*;

public class Parking  {
     static PreparedStatement p,p1;
     static ResultSet r;
     static Connection c;
     static String query;
   static boolean checkUsernameAdmin(String username){
       boolean check;
       try{
         c= security.getConnection();
            query="select username_admin from admin where username_admin='"+username+"'";
            p=c.prepareStatement(query);
            r=p.executeQuery(query);
         
            check = r.next();
          
       }catch(SQLException ex){
         check= false;
       }
       return check;
   }
   static boolean checkUsernameOperator(String username){
       boolean check;
       try{
         c= security.getConnection();
            query="select username from operators where username='"+username+"'";
            p=c.prepareStatement(query);
            r=p.executeQuery(query);
            check = r.next();
       }catch(SQLException ex){
         check= false;
       }
       return check;
   }
   static boolean checkIdAdmin(int id){
       boolean check;
       try{
         c= security.getConnection();
            query="select id_admin from admin where id_admin='"+id+"'";
            p=c.prepareStatement(query);
            r=p.executeQuery(query);
         
            check = r.next();
          
       }catch(SQLException ex){
         check= false;
       }
       return check;
   }
   static boolean checkIdOperator(int id){
       boolean check;
       try{
         c= security.getConnection();
            query="select id from operators where id='"+id+"'";
            p=c.prepareStatement(query);
            r=p.executeQuery(query);
            check = r.next();
       }catch(SQLException ex){
         check= false;
       }
       return check;
   }
    
    public static void main(String[] args){
           Login l=new Login();
           l.setTitle("Parking");
           l.setSize(550,350);
           l.setVisible(true);
           l.setLocation(400,150);
           l.setResizable(false);
 }
}