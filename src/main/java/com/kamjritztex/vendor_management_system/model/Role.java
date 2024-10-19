package com.kamjritztex.vendor_management_system.model;

public enum Role {
    ADMIN("ADMIN"),

    USER("USER"),
    SUPER_ADMIN("SUPER_ADMIN");
    private String value;
    Role(final String value){
        this.value=value;
    }

    }
