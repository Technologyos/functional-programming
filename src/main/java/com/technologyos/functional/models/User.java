package com.technologyos.functional.models;

import java.io.Serializable;

public record User(String name, Integer age) implements Serializable {}
