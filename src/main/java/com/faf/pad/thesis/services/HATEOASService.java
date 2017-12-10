package com.faf.pad.thesis.services;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public interface HATEOASService {

    <T extends ResourceSupport> List<T> getLinksForList(List<T> list);

    <T extends ResourceSupport> T getLinksForEntity(T element, long id);
}
