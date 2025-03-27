package org.root.immutable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

final public class Employee {
    private final String empName;
    private final int age;
    private final Address address;
    private final List<String> phoneNumbers;
    private final Map<String, String> metadata;
    private final Date mutableDate;

    public Employee(String name, int age, Address address, List<String> phoneNumbers, Map<String, String> metadata, Date mutableDate) {
        super();
        this.empName = name;
        this.age = age;
        this.address = address;
        this.phoneNumbers = phoneNumbers;
        this.metadata = metadata;
        this.mutableDate = mutableDate;
    }

    public String getEmpName() {
        return empName;
    }

    public int getAge() {
        return age;
    }
// Defensive copy of the Address to ensure immutability
    public Address getAddress() {
        return new Address(address.getStreet(), address.getCity());
    }

    public List<String> getPhoneNumbers() {
        return new ArrayList<>(phoneNumbers);
    }

    public Map<String, String> getMetadata() {
        return new HashMap<>(metadata);
    }

    public Date getMutableDate() {
        return new Date(mutableDate.getTime());
    }
}
