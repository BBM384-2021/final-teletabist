package com.teletabist.clubby.user.core;

import java.util.Collections;
import java.util.Set;

import com.google.common.collect.Sets;

public abstract class Role implements Comparable<Role>{
    private final String name;
    private final Set<String> permissions;
    public Role(String name, String... permissions){
        this.name = name;
        this.permissions = Collections.synchronizedSet(Sets.newHashSet(permissions)); 
    }
    
    public final Set<String> getPermissions(){
        return this.permissions;
    }

    public final String getName(){
        return this.name;
    }

    @Override
    public int compareTo(Role o) {
        return this.name.compareTo(o.getName());
    }
}