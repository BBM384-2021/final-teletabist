package com.teletabist.clubby.club.core;

import java.util.ArrayList;

import org.springframework.plugin.core.Plugin;

public interface ModuleRoles extends Plugin<String> {
    ArrayList<String> getRoleNames();
    Role getRoleByName(String name);
}
