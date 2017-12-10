package com.faf.pad.thesis.services.impl;

import com.faf.pad.thesis.controllers.CompanyController;
import com.faf.pad.thesis.services.HATEOASService;
import org.springframework.hateoas.ResourceSupport;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class HATEOASServiceImpl implements HATEOASService {

    @Override
    public <T extends ResourceSupport> List<T> getLinksForList(List<T> list) {
        return list.stream()
                .peek(element -> element.add(linkTo(methodOn(CompanyController.class).getAll("")).withSelfRel()))
                .collect(Collectors.toList());
    }

    @Override
    public <T extends ResourceSupport> T getLinksForEntity(T element, long id) {
        element.add(linkTo(methodOn(CompanyController.class).getById(id, "")).withSelfRel());
        return element;
    }
}
