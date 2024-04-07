package com.librarymanagementsystem.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Library {
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public Library(Builder builder) {
        this.name = builder.name;
        this.address = builder.address;
    }

    public static class Builder {
        private String name;
        private Address address;

        public Builder name(String name) {
            Objects.requireNonNull(name, "Library name cannot be null");
            this.name = name;
            return this;
        }

        public Builder address(Address address) {
            Objects.requireNonNull(address, "Address cannot be null");
            this.address = address;
            return this;
        }

        public Library build() {
            return new Library(this);
        }

    }
}
