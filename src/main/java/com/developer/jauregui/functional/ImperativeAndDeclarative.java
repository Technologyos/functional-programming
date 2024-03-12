package com.developer.jauregui.functional;

import com.developer.jauregui.functional.moduls.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ImperativeAndDeclarative {

    public static List<User> listOfUsers = Arrays.asList(new User("Armando", 14), new User("Jose", 20),
            new User("Taylor", 19),new User("Danna", 12),new User("Carol", 25));

    public static List<User> printUsersWithAgeIsGreaterThanEighteenImperativeMethod(){
        List<User> newListOfUsers = new ArrayList();
        for(User user: listOfUsers){
            if(user.getAge() > 18){
                newListOfUsers.add(user);
            }
        }

        for(User user: newListOfUsers){
            System.out.println(user.getAge());
        }

        return newListOfUsers;
    }

    public static List<User> printUsersWithAgeIsGreaterThanEighteenDeclarativeMethod(){
        return listOfUsers.stream()
                .filter(user -> user.getAge() > 18)
                .collect(Collectors.toList());
    }
}
