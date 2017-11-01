package com.innopo.DB.DAO;

import com.innopo.DB.DAO.exceptions.DAOException;
import com.innopo.POJO.CustomUser;

import java.util.Optional;

public interface UserDAO {

   // boolean getUserByLoginAndPassword(String login, String password) throws DAOException;

    boolean createUser(String firstname, String lastName, String bDay, String sex, int nativeLanguage, String login, String password) throws DAOException;

    Optional<CustomUser> getByUsername(String login) throws DAOException;


}
