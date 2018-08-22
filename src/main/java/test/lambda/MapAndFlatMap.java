package test.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MapAndFlatMap {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        list.stream().map(str -> str.split("")).flatMap(str -> Arrays.stream(str))
                .collect(Collectors.toList()).forEach(System.out::println);;

        list.stream().map(str -> str.split("")).map(str -> Arrays.stream(str))
                .collect(Collectors.toList()).forEach(System.out::println);

        User user = new User();
        String result = Optional.ofNullable(user).flatMap(User::getAddress)
                .flatMap(Address::getCountry).map(Country::getCode)
                .orElseThrow(() -> new IllegalArgumentException("sas"));
        System.out.println(result);
    }
}

class User {

    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

class Address {

    private Country country;

    public Optional<Country> getCountry() {
        return Optional.ofNullable(country);
    }
}

class Country {

    String code = "aa";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
