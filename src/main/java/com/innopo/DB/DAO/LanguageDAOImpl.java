package com.innopo.DB.DAO;

import com.innopo.DB.ConnectionManagerMSSQL;
import com.innopo.DB.DAO.exceptions.DAOException;
import com.innopo.DB.IConnectionManagerMSSQL;
import com.innopo.POJO.Language;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class LanguageDAOImpl implements LanguageDAO{

//    public static class LanguageDAOException extends Exception {
//
//    }

    private static IConnectionManagerMSSQL manager;

    static {

        manager = ConnectionManagerMSSQL.getInstance();

    }
@Override
    public List<Language> getAll() throws DAOException {

        List<Language> languageList = new ArrayList<>();
        try {
            Statement statement = manager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id, language from languages");

            while (resultSet.next()) {
                Language language = new Language(resultSet.getInt("id"), resultSet.getString("language"));
                languageList.add(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DAOException(e);
        }

        return languageList;
    }

}
