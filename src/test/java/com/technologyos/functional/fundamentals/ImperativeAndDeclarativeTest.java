package com.technologyos.functional;

import com.technologyos.functional.fundamentals.ImperativeAndDeclarative;
import com.technologyos.functional.models.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
