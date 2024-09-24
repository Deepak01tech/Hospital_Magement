package Hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    private Connection connection;
    //private Scanner scanner;
    public Doctor(Connection connection)
    {
        this.connection=connection;
        //this.scanner=scanner;

    }



    public void viewDoctors(){
        String query1 ="select * from doctors";
        try{
            PreparedStatement preparedStatement1= connection.prepareStatement(query1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            System.out.println("doctors: ");
            System.out.println("+------------+--------------+----------+---------+");
            System.out.println("|doctor id  | Name         | Specialization      |");
            System.out.println("+------------+--------------+----------+---------+");

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                //int age = resultSet.getInt("age");
               // String gender= resultSet.getString("gender");
                System.out.printf("|%-12s|%-20|%-10s|%-21s|\n",id,name,specialization);
                System.out.println("+------------+--------------+------------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean getDoctorById(int id)
    {
        String query ="SELECT *from doctors WHERE id= ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                return true;

            } else{
                return false;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
}
