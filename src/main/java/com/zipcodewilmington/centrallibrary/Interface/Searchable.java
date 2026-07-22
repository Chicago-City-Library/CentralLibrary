package com.zipcodewilmington.centrallibrary.Interface;

import java.util.List;

public interface Searchable {

        List<String> getSearchableFields();

        default boolean matchesKeyword(String keyword){
            if (keyword == null || keyword.trim().isEmpty()) {
                return false;
            }
            for (String field : getSearchableFields()) {
                if (field != null && field.toLowerCase().contains(keyword.toLowerCase())) {
                    return true;
                }
            }
            return false;
        }
    
}