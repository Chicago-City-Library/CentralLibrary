package com.zipcodewilmington.centrallibrary.Interface;

public interface Searchable {

    String[] getSearchableFields();

    default boolean matchesKeyword(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }

        for (String field : getSearchableFields()) {

            if (field != null
                    && field.toLowerCase().contains(keyword.toLowerCase())) {

                return true;
            }
        }

        return false;
    }
    boolean matchesField(String fieldName, String keyword);
}
