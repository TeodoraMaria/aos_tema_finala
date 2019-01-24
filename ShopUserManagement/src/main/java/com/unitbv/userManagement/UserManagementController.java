package com.unitbv.userManagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import com.unitbv.userManagement.bl.AccountBl;
import com.unitbv.userManagement.dao.Person;


@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class UserManagementController {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementController.class, args);
	}

    @RequestMapping("/register")
    @ResponseBody
    public String register(String userDtoJson ) {
        AccountBl accountBl = new AccountBl();
        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(userDtoJson,Person.class);
        boolean response = accountBl.insert(person);
        return String.valueOf(response);
    }
    
   @RequestMapping("/login")
   @ResponseBody
   public String login(String userDtoJson ) {
       AccountBl accountBl = new AccountBl();
       Gson gson = new GsonBuilder().create();
       Person person = gson.fromJson(userDtoJson,Person.class);
       Person response = accountBl.login(person);
       return gson.toJson(response);
   }
}
