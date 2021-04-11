package com.teletabist.clubby.user.core;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.plugin.core.PluginRegistry;
import org.springframework.stereotype.Service;

@Service
public class ModuleRolesService {
    private final PluginRegistry<ModuleRoles, String> registry;

    @Autowired
    public ModuleRolesService(List<ModuleRoles> modules){
        this.registry = PluginRegistry.of(modules);
    }

    public Optional<ModuleRoles> getModuleRoles(String name){
        return registry.getPluginFor(name);
    }
}