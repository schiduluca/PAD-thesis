package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.services.FieldSelectorService;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class FieldSelectorServiceImpl implements FieldSelectorService {

    @Override
    public <T> T selectFields(T element, String query) {

        if(query == null || query.isEmpty()) {
            return element;
        }

        Set<String> collect = Arrays.stream(query.split(",")).collect(Collectors.toSet());

        Arrays.stream(element.getClass().getDeclaredFields())
                .forEach(field -> {
                    field.setAccessible(true);
                    if(!collect.contains(field.getName())) {
                        try {
                            field.set(element, null);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                });

        return element;

    }
}
