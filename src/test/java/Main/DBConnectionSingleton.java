package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




    public class DBConnectionSingleton
    {
        private static Connection con;



        public static Connection getConnectionInstance() throws SQLException
        {



            if(con == null)
            {
                con = DriverManager.getConnection(Constants.URL, Constants.username, Constants.password);


            }

            return con;
        }
    }



