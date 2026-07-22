package com.zipcodewilmington.centrallibrary.model;

public class LibraryMember extends Person {

    private String memberId;
    private String membershipDate;
    private double outstandingFees;
    private Address address;

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
        
    public String getMemberId() {
    return memberId;
}

public void setMemberId(String memberId) {
    this.memberId = memberId;
}

public String getMembershipDate() {
    return membershipDate;
}

public void setMembershipDate(String membershipDate) {
    this.membershipDate = membershipDate;
}

public double getOutstandingFees() {
    return outstandingFees;
}

public void setOutstandingFees(double outstandingFees) {
    this.outstandingFees = outstandingFees;
}

public Address getAddress() {
    return address;
}

public void setAddress(Address address) {
    this.address = address;           

            }
        }