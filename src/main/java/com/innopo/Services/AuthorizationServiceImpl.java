package com.innopo.Services;

import com.innopo.DB.DAO.UserDAO;
import com.innopo.DB.DAO.UserDAOImpl;
import com.innopo.DB.DAO.exceptions.DAOException;

//import static com.innopo.Services.CustomPasswordEncoder.encode;

public class AuthorizationServiceImpl implements AuthorizationService {

    private static UserDAO userDAO = new UserDAOImpl();

    @Override
    public boolean auth(String login, String password) {

        if(login==null || password==null){
            return false;
        }

//        try {
//            if (userDAO.getUserByLoginAndPassword(login, encode(password))){
//                return true;
//            }
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        return false;
    }
}
