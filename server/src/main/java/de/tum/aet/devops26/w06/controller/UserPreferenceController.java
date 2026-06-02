package de.tum.aet.devops26.w06.controller;

import org.springframework.web.bind.annotation.*;

import de.tum.aet.devops26.w06.entity.UserPreferences;
import de.tum.aet.devops26.w06.service.UserPreferenceService;

@RestController
@RequestMapping("/preferences")
public class UserPreferenceController {

    //private final UserPreferenceRepository repository;
    private final UserPreferenceService userPreferenceService;

    public UserPreferenceController(UserPreferenceService userPreferenceService) {
        this.userPreferenceService = userPreferenceService;
    }

    @GetMapping("/{name}")
    public UserPreferences getPreferences(@PathVariable String name) {
        return userPreferenceService.getPreferences(name);
    }

    @PostMapping("/{name}")
    public UserPreferences addPreference(@PathVariable String name, @RequestParam String meal) {
        return userPreferenceService.addPreferences(name, meal);
    }

    @DeleteMapping("/{name}")
    public UserPreferences removePreference(@PathVariable String name, @RequestParam String meal) {
        return userPreferenceService.removePreference(name, meal);
    }
}
