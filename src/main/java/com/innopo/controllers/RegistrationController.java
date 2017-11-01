package com.innopo.controllers;

import com.innopo.DB.DAO.LanguageDAO;
import com.innopo.DB.DAO.LanguageDAOImpl;
import com.innopo.DB.DAO.UserDAO;
import com.innopo.DB.DAO.exceptions.DAOException;
import com.innopo.POJO.Language;
import com.innopo.POJO.User;
import com.innopo.Services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {

    public RegistrationController() {
    }

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private LanguageDAO languageDAO;


    //   List<Language> languageList = new ArrayList<>();


    //     languageList = languageDAO.getAll();


//
// @RequestMapping(value = "/registration", method = RequestMethod.GET)
//   public String showRegistrationForm() {
//        return "registrationForm";
//    }


    @RequestMapping(value = "/registration", method = {RequestMethod.POST, RequestMethod.GET})
    public String registerUser(
            @RequestParam(name = "mainForm", required = false) String mainForm,
            @RequestParam(name = "registerForm", required = false) String registerForm,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "bday", required = false) String bday,
            @RequestParam(name = "sex", required = false) String sex,
            @RequestParam(name = "nativeLanguage", required = false) String nativeLanguage,
            @RequestParam(name = "login", required = false) String login,
            @RequestParam(name = "password", required = false) String password,
            // @ModelAttribute("firstName") String firstName,
//            @ModelAttribute("lastName") String lastName,
//            @ModelAttribute("bday") String bday,
//            @ModelAttribute("sex") String sex,
//            @ModelAttribute("nativeLanguage") String nativeLanguage,
//            @ModelAttribute("login") String login,
//            @ModelAttribute("password") String password ,
            //   @ModelAttribute User user,
            Model model
    ) {

        if (mainForm != null) {
            password = "";
            login = "";
        }

        List<Language> languageList = new ArrayList<>();
        try {
            languageList = languageDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();

        }
         model.addAttribute("languageList", languageList);
          //
//
// ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registrationForm");

//        model.addAttribute("userForm", user);
        if (registerForm != null) {
            String result = registrationService.regUser(firstName, lastName, bday, sex, nativeLanguage, login, password);
            if (result.equals("User exists")) {
                model.addAttribute("message", "User with such login already exists");
            }else if(result.equals("Incorrect data")){
                model.addAttribute("message", "Incorrect data");
            } else {
                return "redirect:/";
            }
            model.addAttribute("firstName", firstName);

            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            model.addAttribute("bday", bday);
            model.addAttribute("sex", sex);

            model.addAttribute("nativeLanguage", (nativeLanguage != null && !nativeLanguage.equals("")) ? Integer.parseInt(nativeLanguage) : nativeLanguage);
            model.addAttribute("languageList", languageList);
            model.addAttribute("login", login);
//        } else {

        }
            //   model.addAttribute("password", password);
            //modelAndView.addObject("languageList", languageList);
//        }
//        return modelAndView;
//        }
            return "registrationForm";
        }
    }
