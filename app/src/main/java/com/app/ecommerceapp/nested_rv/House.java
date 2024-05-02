package com.app.ecommerceapp.nested_rv;

import java.util.List;

public class House {
    private String slug;
    private String name;
    private List<Member> members;

    public House(String slug, String name, List<Member> members) {
        this.slug = slug;
        this.name = name;
        this.members = members;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
