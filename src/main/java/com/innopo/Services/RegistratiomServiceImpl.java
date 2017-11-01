package com.innopo.Services;

import com.innopo.DB.DAO.UserDAO;
import com.innopo.DB.DAO.exceptions.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistratiomServiceImpl implements RegistrationService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public String regUser(String firstname, String lastName, String bDay, String sex, String nativeLanguage, String login, String password) {

        CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

        if (firstname == null || lastName==null || bDay== null || sex==null || nativeLanguage == null || login == null || password == null) {
            return "Incorrect data";
        }

        try {
            if (userDAO.getByUsername(login).isPresent()){
                return "User exists";
            }
        } catch (DAOException e) {
            e.printStackTrace();
        }

        try {
            userDAO.createUser(firstname, lastName, bDay, sex, Integer.parseInt(nativeLanguage), login, customPasswordEncoder.encode(password));
        } catch (DAOException e) {
            e.printStackTrace();
        }

        return "Ok";
    }

}
