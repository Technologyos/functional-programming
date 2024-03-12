package com.developer.jauregui.functional;

import com.developer.jauregui.functional.moduls.User;

import java.util.List;

public class PureAndImpureFunction {

    /**
     * <p>
     * A pure function does not depend on external states (properties, objects, variables external to its definition, etc.)
     * nor is its result affected by external agents.
     * his means that every time you call the function with the same arguments, you will always get the same result.
     * A function can be a pure function provided it should not have any external variable which will alter the behaviour of that variable.
     */
    public static int sum(int x, int y){return x + y;}

    public static boolean hasAvailableFunds(double funds) {
        return funds > 0.0;
    }

    /**
     * <p>
     * Impure function is a function that contains one or more side effects,
     * the result of executing it can be observed from outside the code. For instance in a console.
     */
    public void printSomething(){
        System.out.println("Print Something");
    }

    public List<User> getListOfUsers(){
        //get the users from the database
        //Execute a query from the database
        //get the response
        //do something with that list
        return null;
    }
}
