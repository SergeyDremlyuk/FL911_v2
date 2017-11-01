package com.innopo.Services;

import com.innopo.DB.DAO.UserDAO;
import com.innopo.DB.DAO.exceptions.DAOException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    private UserDAO userDAO;

    public CustomUserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return userDAO.getByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Can't find user with specified username"));
        } catch (DAOException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Can't find user with specified username");
        }
    }
}
