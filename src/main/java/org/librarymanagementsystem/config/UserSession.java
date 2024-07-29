package org.librarymanagementsystem.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserSession {
    private long id;
    private String username;

    public void login(long id ,String username){
        this.id=id;
        this.username=username;
    }
    public void logout(){
        this.id=0;
        this.username=null;
    }

    public boolean isLoggedIn(){
        return id>0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
