package com.yedy.chat_app.consts;


public class RegexConst {
    public static final String userNameRegex = "^[a-z][a-z1-9]*$";
    public static final String passwordRegex = "^[a-zA-Z0-9][a-zA-Z0-9!@#$%^&*()-_+=<>?.,:;{}[\\\\]\\\\/]*$";
    public static final String emailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b";
    public static final String telNumberRegex = "^[0-9]{10,10}$";

}