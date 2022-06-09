package com.example.proj.action;

import com.opensymphony.xwork2.ActionSupport;  

public class Login extends ActionSupport {

    private String username, password;

    public Login() {
	}

    public String execute() throws Exception {
        /** Add db connection to check for login credentials */
        return SUCCESS;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
