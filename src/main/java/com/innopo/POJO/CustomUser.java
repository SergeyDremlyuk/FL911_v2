package com.innopo.POJO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Collection;

public class CustomUser extends User{

        private int id;
        private String firstName;
        private String lastName;

        public enum Sex {male, female};
        private LocalDate bDay;
        private String sex;
//        private String login;
//        private String password;
        private int nativeLanguageId;

//        public User() {
//
//        }

        public CustomUser(int id, String firstName, String lastName, LocalDate bDay, String sex, String login, String password, int nativeLanguageId, Collection<? extends GrantedAuthority> authorities) {
            super(login, password, authorities);
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.bDay = bDay;
            this.sex = sex;
//            this.login = login;
//            this.password = password;
            this.nativeLanguageId = nativeLanguageId;
        }
//
//        public String getLogin() {
//            return login;
//        }

//        @XmlElement
//        public void setLogin(String login) {
//            this.login = login;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        @XmlElement
//        public void setPassword(String password) {
//            this.password = password;
//        }

        public int getNativeLanguage() {
            return nativeLanguageId;
        }

        @XmlElement
        public void setNativeLanguageId(int nativeLanguageId) {

            this.nativeLanguageId = nativeLanguageId;
        }

        public int getId() {
            return id;
        }

        @XmlAttribute
        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        @XmlElement
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @XmlElement
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public LocalDate getbDay() {
            return bDay;
        }

        @XmlElement
        @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
        public void setbDay(LocalDate bDay) {
            this.bDay = bDay;
        }

        public String getSex() {
            return sex;
        }

        @XmlElement
        public void setSex(String sex) {
            this.sex = sex;
        }



    }


