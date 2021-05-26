package com.teletabist.clubby.user.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * This code is written by Bugra.
 * Because of merge issues, fixed by Yigit
 */
public interface ProfileRepository extends JpaRepository<Profile, Integer>, JpaSpecificationExecutor<Profile> {
}