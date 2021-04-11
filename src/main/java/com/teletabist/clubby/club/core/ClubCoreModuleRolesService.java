package com.teletabist.clubby.club.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

@Service
public class ClubCoreModuleRolesService {
    private final PluginRegistry<ClubCoreModuleRoles, String> registry;

    @Autowired
    public ClubCoreModuleRolesService(List<ClubCoreModuleRoles> modules){
        this.registry = PluginRegistry.of(modules);
    }

    public Optional<ClubCoreModuleRoles> getModuleRoles(String name){
        return registry.getPluginFor(name);
    }
}
