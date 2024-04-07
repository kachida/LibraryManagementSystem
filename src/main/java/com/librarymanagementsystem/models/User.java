package com.librarymanagementsystem.models;

import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Objects;

@Component
public class User {
    private BigInteger userId;
    private String name;
    private String email;
    private String phone;

    public User() {}
    public User(Builder builder) {
        this.userId = builder.userId;
        this.name = builder.name;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + phone + '\'' +
                '}';
    }

    public BigInteger getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


    public static class Builder {
        private BigInteger userId;
        private String name;
        private String email;
        private String phone;

        public Builder userId(BigInteger userId) {
            Objects.requireNonNull(userId, "userId cannot be null");
            this.userId = userId;
            return this;
        }

        public Builder name(String name) {
            Objects.requireNonNull(name, "name cannot be null");
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            Objects.requireNonNull(email, "email cannot be null");
            this.email = email;
            return this;
        }

        public Builder phone(String phone) {
            Objects.requireNonNull(phone, "password cannot be null");
            this.phone = phone;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
