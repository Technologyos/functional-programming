package com.developer.jauregui.functional.moduls;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Information:
 * 1.- no one can extend from it
 * 2.- once created an object cannot mutate
 * 3.- The constructor requires all properties to generate an object
 */
public final class ImmutableUser implements Serializable {
    private final String fullName;
    private final String age;
    private final String email;
    private final List<String> friends;

    public ImmutableUser(String fullName, String age, String email, List<String> friends) {
        this.fullName = fullName;
        this.age = age;
        this.email = email;
        this.friends = friends;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getFriends() {
        return new LinkedList<>(friends);
    }

    @Override
    public String toString() {
        return "ImmutableUser{" +
            "fullName='" + fullName + '\'' +
            ", age='" + age + '\'' +
            ", email='" + email + '\'' +
            ", friends=" + friends +
            '}';
    }
}
