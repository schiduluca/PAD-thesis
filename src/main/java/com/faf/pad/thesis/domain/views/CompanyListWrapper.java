package com.faf.pad.thesis.domain.views;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "companies")
public class CompanyListWrapper {
    private List<CompanyView> list;

    public CompanyListWrapper() {
    }

    public CompanyListWrapper(List<CompanyView> list) {
        this.list = list;
    }

    @XmlElement(name = "company")
    public List<CompanyView> getList() {
        return list;
    }
}
