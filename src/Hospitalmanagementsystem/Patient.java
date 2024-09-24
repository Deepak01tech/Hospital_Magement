package Hospitalmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;
    public Patient(Connection connection,Scanner scanner)
    {
        this.connection=connection;
        this.scanner=scanner;

    }

    public void addPatient()
    {
        System.out.println("enter the patient name : ");
        String name =scanner.next();
        System.out.println("enter the patient age: ");
        int age =scanner.nextInt();
        System.out.println("enter the patient gender: ");
        String gender =scanner.next();

        try
        {


            String query = "insert into patients(name,age,gender) VALUES(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,age);
            preparedStatement.setString(3,gender);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0)

            {
                System.out.println("patient added sucessfully");

            }
            else {
                System.out.println("failed to add patient");
            }






        } catch (SQLException e){

            e.printStackTrace();

        }



    }

    public void viewPatients(){
        String query1 ="select * from patients";
        try{
            PreparedStatement preparedStatement1= connection.prepareStatement(query1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            System.out.println("patients: ");
            System.out.println("+------------+--------------+----------+---------+");
            System.out.println("|patient id  | Name         | Age      |  Gender |");
            System.out.println("+------------+--------------+----------+---------+");

            while(resultSet.next())
            {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender= resultSet.getString("gender");
                System.out.printf("|%-12s|%-14|%-10s|%-10s|\n",id,name,age,gender);
                System.out.println("+------------+--------------+----------+---------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public boolean getPatientById(int id)
    {
        String query ="SELECT *from patients WHERE id= ?";
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
