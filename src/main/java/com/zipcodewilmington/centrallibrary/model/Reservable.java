package com.zipcodewilmington.centrallibrary.model;

public interface Reservable {

    void reserve(LibraryMember member);

    void cancelReserve();

    boolean isReserved();

}