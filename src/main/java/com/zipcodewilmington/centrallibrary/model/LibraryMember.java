package com.zipcodewilmington.centrallibrary.model;

public class LibraryMember extends Person {

    // Instance Variables

    private String memberId;
    private String membershipDate;
    private double outstandingFees;
    private Address address;


    // No-Argument Constructor (for Jackson)

    public LibraryMember() {
        super();
    }


    // Constructor

    public LibraryMember(
            String name,
            int age,
            String email,
            String phoneNumber,
            String memberId,
            String membershipDate,
            double outstandingFees,
            Address address) {

        super(name, age, email, phoneNumber);

        this.memberId = memberId;
        this.membershipDate = membershipDate;
        this.outstandingFees = outstandingFees;
        this.address = address;
    }


    // Getters & Setters

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        if (memberId != null && !memberId.isBlank()) {
            this.memberId = memberId;
        }
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        if (membershipDate != null && !membershipDate.isBlank()) {
            this.membershipDate = membershipDate;
        }
    }

    public double getOutstandingFees() {
        return outstandingFees;
    }

    public void setOutstandingFees(double outstandingFees) {
        if (outstandingFees >= 0) {
            this.outstandingFees = outstandingFees;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        if (address != null) {
            this.address = address;
        }
    }


    // Display Method

    @Override
    public String toString() {
        return "\n------------------------------------"
                + "\nMember ID: " + memberId
                + "\nName: " + getName()
                + "\nAge: " + getAge()
                + "\nEmail: " + getEmail()
                + "\nPhone: " + getPhoneNumber()
                + "\nMember Since: " + membershipDate
                + "\nOutstanding Fees: $" + String.format("%.2f", outstandingFees)
                + "\nAddress: " + address
                + "\n------------------------------------";
    }
}