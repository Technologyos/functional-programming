package com.developer.jauregui.functional.utils;

public class EmptyValueException extends IllegalArgumentException {
    public EmptyValueException(){

    }

    public EmptyValueException(String message){
        super(message);
    }
}
