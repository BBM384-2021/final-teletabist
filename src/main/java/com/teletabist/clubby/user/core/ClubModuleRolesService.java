package com.teletabist.clubby.user.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

@Service
public class ClubModuleRolesService {
    private final PluginRegistry<ClubModuleRoles, String> registry;

    @Autowired
    public ClubModuleRolesService(List<ClubModuleRoles> modules){
        this.registry = PluginRegistry.of(modules);
    }

    public Optional<ClubModuleRoles> getModuleRoles(String name){
        return registry.getPluginFor(name);
    }
}