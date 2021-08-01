package Parking_GUI;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public abstract class Station {
   
    
    static Connection connect;
    static PreparedStatement st;
    static String query;
    static ResultSet r;
    private static final ArrayList<String> spots = new ArrayList(); 
    
    static boolean checkUsernameAdmin(String username){
       boolean check;
       try{
         connect= security.getConnection();
            query="select username_admin from admin where username_admin='"+username+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
         
            check = r.next();
          
       }catch(SQLException ex){
         check= false;
       }
         finally{
            try{
                connect.close();
                st.close();
                r.close();
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
       return check;
   }
   static boolean checkUsernameOperator(String username){
       boolean check;
       try{
         connect= security.getConnection();
            query="select username from operators where username='"+username+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
            check = r.next();
       }catch(SQLException ex){
         check= false;
       }
         finally{
            try{
                connect.close();
                st.close();
                r.close();
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
       return check;
   }
   static boolean checkIdAdmin(int id,String username){
       boolean check;
       try{
         connect= security.getConnection();
            query="select id_admin from admin where id_admin='"+id+"' AND username_admin='"+username+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
         
            check = r.next();
          
       }catch(SQLException ex){
         check= false;
       }
       return check;
   }
   static boolean checkIdOperator(int id,String username){
       boolean check;
       try{
         connect= security.getConnection();
            query="select id from operators where id='"+id+"' AND username='"+username+"'";
            st=connect.prepareStatement(query);
            r=st.executeQuery(query);
            check = r.next();
       }catch(SQLException ex){
         check= false;
       }
         finally{
            try{
                connect.close();
                st.close();
                r.close();
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
       return check;
   }
    protected static  void createParking(int numberOfFloors,int spotsInFloor){
        
        for (int i = 0; i < numberOfFloors; i++) {
            for (int j = 1; j <= spotsInFloor; j++) {
                spots.add((char)(i+65)+""+j);
            }
        }
        createDb();
      
    }      
    public static void createDb()
    {
        try{
            connect=security.getConnection();
            for(int i=0;i<spots.size();i++)
            {
                query="insert into totalspots values('"+spots.get(i)+"','true')";
                st=connect.prepareStatement(query);
                st.execute(query);
            }
           
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        finally{
            try{
                connect.close();
                st.close();
                r.close();
                
            }
            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
    }  
    

		public int totalParkingHours(int id){
      int result=0;
        try{
        int endDateH = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int endDateM = Calendar.getInstance().get(Calendar.MINUTE);
        connect=security.getConnection();
         query = "select start_dateH from customers where id_customer = '"+id+"'";
          st = connect.prepareStatement(query);
           r= st.executeQuery(query);
           r.next();
           int startDateH=r.getInt("start_dateH");
          if (endDateM >= 30)
           result =endDateH-startDateH+1;
          else
            result =endDateH-startDateH;
       
     }catch(SQLException ex){
            System.out.println(ex.getNextException());
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
       
       return result;
    }

}
