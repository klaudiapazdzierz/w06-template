package de.tum.aet.devops26.w06;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tum.aet.devops26.w06.entity.UserPreferences;

/*
    Required for interacting with the database.
    A Spring Data JPA interface that provides ready-to-use database operations.
 */

public interface UserPreferenceRepository extends JpaRepository<UserPreferences, String> {

}
