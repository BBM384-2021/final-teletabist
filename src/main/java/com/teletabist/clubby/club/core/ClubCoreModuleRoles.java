package com.teletabist.clubby.club.core;

import java.util.ArrayList;

import org.springframework.plugin.core.Plugin;

public interface ClubCoreModuleRoles extends Plugin<String> {
    ArrayList<String> getRoleNames();
    ClubRole getRoleByName(String name);
}
