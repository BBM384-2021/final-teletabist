package com.teletabist.clubby.club.core;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

public abstract class Role implements Comparable<Role>{

    private final String name;
    private final Set<String> permisssons;

    public Role(String name, String... permissions) {
        this.name = name;
        this.permisssons = Collections.synchronizedSet(Sets.newHashSet(permissions));;
    }

    public final Set<String> getPermissions() {
        return this.permisssons;
    }

    public final String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Role o) {
        return this.name.compareTo(o.getName());
    }
    
}
