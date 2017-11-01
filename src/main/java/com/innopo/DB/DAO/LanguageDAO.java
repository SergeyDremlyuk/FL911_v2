package com.innopo.DB.DAO;

import com.innopo.DB.DAO.exceptions.DAOException;
import com.innopo.POJO.Language;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface LanguageDAO {
    List<Language> getAll() throws DAOException;

}