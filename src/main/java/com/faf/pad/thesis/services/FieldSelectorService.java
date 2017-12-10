package com.faf.pad.thesis.services;

public interface FieldSelectorService {

    <T> T selectFields(T element, String query);
}
