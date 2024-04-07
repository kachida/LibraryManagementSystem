package com.librarymanagementsystem.models;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Address {
    private String streetAddress;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    public Address(Builder builder) {
        this.streetAddress = builder.streetAddress;
        this.city = builder.city;
        this.state = builder.state;
        this.zipcode = builder.zipcode;
        this.country = builder.country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public static class Builder {
        String streetAddress;
        String city;
        String state;
        String zipcode;
        String country;

        public Builder streetAddress(String streetAddress) {
            Objects.requireNonNull(streetAddress, "streetAddress cannot be null");
            this.streetAddress = streetAddress;
            return this;
        }

        public Builder city(String city) {
            Objects.requireNonNull(city, "city cannot be null");
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            Objects.requireNonNull(state, "state cannot be null");
            this.state = state;
            return this;
        }

        public Builder zipcode(String zipcode) {
            Objects.requireNonNull(zipcode, "zipcode cannot be null");
            this.zipcode = zipcode;
            return this;
        }

        public Builder country(String country) {
            Objects.requireNonNull(country, "city cannot be null");
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
