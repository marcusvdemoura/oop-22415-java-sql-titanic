package com.company;


import com.company.models.Passenger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TitanicQueries {
    final String DB_DATABASE = "titanicmanifest";
    final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_DATABASE;
    final String DB_USER = "root";
    final String DB_PASSWORD = "M@rcus2020";


    private PreparedStatement getPeopleByName;
    private PreparedStatement getPeopleByGender;

    public TitanicQueries() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            String sqlQueryGender = "select name, gender, age from titanic WHERE gender like ?";
            getPeopleByGender = con.prepareStatement(sqlQueryGender);

            // select people by name
            String sqlQueryName = "select name, gender, age from titanic WHERE name like ?";
            getPeopleByName = con.prepareStatement(sqlQueryName);

        } catch (SQLException e) {


        } catch (Exception e) {


        }

    }

    public List<Passenger> getPassengersByName(String name) {
        ResultSet resultSet = null;
        List<Passenger> results = null;

        try {
            getPeopleByName.setString(1, "%" + name + "%");

            resultSet = getPeopleByName.executeQuery();

            results = new ArrayList<Passenger>();

            while (resultSet.next()) {
                Passenger newPassenger = new Passenger();
                newPassenger.name = resultSet.getString("name");
                newPassenger.gender = resultSet.getString("gender");
                newPassenger.age = resultSet.getInt("age");
                results.add(newPassenger);
            }


        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return results;
    }

    public List<Passenger> getPassengersByGender(String gender) {

        ResultSet resultSet = null;
        List<Passenger> results = null;

        try {

            getPeopleByGender.setString(1, "%" + gender + "%");

            resultSet = getPeopleByGender.executeQuery();

            results = new ArrayList<Passenger>();

            while (resultSet.next()) {
                Passenger newPassenger = new Passenger();
                newPassenger.name = resultSet.getString("name");
                newPassenger.gender = resultSet.getString("gender");
                newPassenger.age = resultSet.getInt("age");
                results.add(newPassenger);
            }


        } catch (SQLException e) {

        } catch (Exception e) {

        }

        return results;
    }
}