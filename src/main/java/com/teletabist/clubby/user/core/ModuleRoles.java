package com.teletabist.clubby.user.core;

import java.util.ArrayList;

import org.springframework.plugin.core.Plugin;

public interface ClubModuleRoles extends Plugin<String>{
    ArrayList<String> getRoleNames();
    Role getRoleByName(String name);
}