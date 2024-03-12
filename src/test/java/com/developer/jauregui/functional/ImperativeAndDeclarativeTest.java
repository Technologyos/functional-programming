package com.developer.jauregui.functional;

import com.developer.jauregui.functional.moduls.User;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ImperativeAndDeclarativeTest {

    @Test
    public void get_list(){
        List<User> expectedListOfUsers = Arrays.asList( new User("Jose", 20),
                new User("Taylor", 19), new User("Carol", 25));
        List<User> currentListOfUsers = ImperativeAndDeclarative.printUsersWithAgeIsGreaterThanEighteenDeclarativeMethod();
        assertNotEquals(expectedListOfUsers, currentListOfUsers);
        //assertArrayEquals(expectedListOfUsers.toArray(), currentListOfUsers.toArray() );
    }
}