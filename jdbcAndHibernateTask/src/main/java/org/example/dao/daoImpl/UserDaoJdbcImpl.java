package org.example.dao.daoImpl;

import org.example.dao.UserDao;
import org.example.model.User;
import org.example.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {
    @Override
    public void createUsersTable() {
        String sqlQuery="create table if not exists users(" +
                "id serial primary key," +
                "name varchar," +
                "last_name varchar," +
                "age int);";
        try(
            Connection connection= Util.getConnectionToDatabase();
            Statement statement=connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            System.out.println("Successfully created ...");
            }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropUsersTable() {
        String sqlQuery = "drop table if exists users;";
        try(Connection connection = Util.getConnectionToDatabase();
        Statement statement=connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            System.out.println("Table successfully dropped...");
        }catch (SQLException s){
            System.out.println(s.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sqlQuery = "insert into users (" +
                "name,last_name,age)" +
                "values(?,?,?);";
        try(Connection connection = Util.getConnectionToDatabase();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("user successfully saved!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public void removeUserById(Long id) {
        String  sqlQuery="delete from users where id=?;";
        try(Connection connection=Util.getConnectionToDatabase();
        PreparedStatement preparedStatement =
                connection.prepareStatement(sqlQuery)){
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
            System.out.println("user successfully deleted...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public List<User> getAllUsers() {
        List<User>users=new ArrayList<>();
        String sqlQuery ="select * from users;";
        try(Connection connection=Util.getConnectionToDatabase();
        PreparedStatement preparedStatement =connection.prepareStatement(sqlQuery)){
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                users.add(new User(
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getByte("age")
                  )
                );
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String sqlQuery ="truncate users;";
        try(Connection connection=Util.getConnectionToDatabase();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sqlQuery);
            System.out.println("Table successfully cleaned...");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
