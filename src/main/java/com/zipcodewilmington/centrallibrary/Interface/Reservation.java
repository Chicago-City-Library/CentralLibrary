package com.zipcodewilmington.centrallibrary.Interface

import com.zipcodewilmington.centrallibrary.model.LibraryMember;

public interface Reservation {

    boolean isReserved();
    LibraryMember getReservedBy();
    void reserve(LibraryMember member);
    void cancelReserve();

}