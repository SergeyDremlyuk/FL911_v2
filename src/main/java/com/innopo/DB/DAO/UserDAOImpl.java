package com.innopo.DB.DAO;

import com.innopo.DB.ConnectionManagerMSSQL;
import com.innopo.DB.DAO.exceptions.DAOException;
import com.innopo.DB.IConnectionManagerMSSQL;
import com.innopo.POJO.CustomUser;
import com.innopo.POJO.Language;
import com.innopo.POJO.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserDAOImpl implements UserDAO{

//    public static class UserDAOException extends Exception {
//
//    }


    private static IConnectionManagerMSSQL manager;

    static {

        manager = ConnectionManagerMSSQL.getInstance();

    }

    @Override
    public  boolean createUser(String firstname, String lastName, String bDay, String sex, int nativeLanguage, String login, String password) throws DAOException {
        try {
            PreparedStatement statement = manager.getConnection().prepareStatement("INSERT INTO users(" +
                    "first_name, last_name, birth_date, sex, login, password, native_language_id)  values (?,?,?,?,?,?,?)");
            statement.setString(1, firstname);
            statement.setString(2, lastName);
            statement.setDate(3, java.sql.Date.valueOf(bDay));
            statement.setString(4, sex);
            statement.setString(5, login);
            statement.setString(6, password);
            statement.setInt(7, nativeLanguage);
            if (statement.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return false;
    }

    public boolean getUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        try {
            PreparedStatement statement = manager.getConnection().
                    prepareStatement("SELECT * FROM users WHERE login = ? AND  password = ?");
            statement.setString(1, login);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
           return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
        return false;
    }

    public static List<User> getAll() throws DAOException {

        List<User> usersList = new ArrayList<>();
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT users.id, users.first_name, users.last_name, users.birth_date, users.sex, users.login, users.password, users.native_language_id, languages.language " +
                    "FROM users LEFT JOIN " +
                    "languages ON users.native_language_id = languages.id");

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate(),
                        User.Sex.valueOf(resultSet.getString("sex")),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        new Language(resultSet.getInt("native_language_id"), resultSet.getString("language")));
                usersList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }

        return usersList;
    }

    public static void deleteAll() throws DAOException {

        try {
            Statement statement = manager.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException();
        }

    }

    public static void insertAll(List<User> users ) throws DAOException {

        try {

            PreparedStatement preparedStatement = manager.getConnection().prepareStatement("SET IDENTITY_INSERT users ON; INSERT INTO users(" +
                    "id, first_name, last_name, birth_date, sex, login, password, native_language_id)  values (?,?,?,?,?,?,?,?)");
            for(User user : users){
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setString(2, user.getFirstName());
                preparedStatement.setString(3, user.getLastName());
                preparedStatement.setDate(4, java.sql.Date.valueOf(user.getbDay()));
                preparedStatement.setString(5, user.getSex().toString());
                preparedStatement.setString(6, user.getLogin());
                preparedStatement.setString(7, user.getPassword());
                preparedStatement.setInt(8, user.getNativeLanguage().getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
           throw   new DAOException();
        }

    }

    @Override
    public Optional<CustomUser> getByUsername(String login)  throws DAOException {

        try {
            PreparedStatement statement = manager.getConnection().
                    prepareStatement("SELECT users.*, roles.role FROM users INNER JOIN " +
                            "roles ON users.role_id = roles.id " +
                            "where users.login = ?");
            statement.setString(1, login);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CustomUser customUser = new CustomUser(
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getDate("birth_date").toLocalDate(),
                        resultSet.getString("sex"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getInt("native_language_id"),
                        Collections.singleton(new SimpleGrantedAuthority(resultSet.getString("role"))));

                return Optional.of(customUser);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }
    }
}

