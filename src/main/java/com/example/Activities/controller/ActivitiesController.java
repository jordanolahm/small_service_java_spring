package com.example.Activities.controller;

import com.example.Activities.model.Activities;
import com.example.Activities.service.ActivitiesService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activities")
public class ActivitiesController {

    @Autowired
    private ActivitiesService activitiesService;

    // Add new activity
    @PostMapping("/add")
    public Activities addNewActivity(@RequestParam String description) {
        return activitiesService.addActivity(description);
    }

    // Edit new activity
    @PutMapping("/edit/{id}")
    public Activities editActivity(@PathVariable Long id,
                                     @RequestParam String description,
                                     @RequestParam boolean complete) {
        return activitiesService.editActivity(id, description, complete);
    }

    // List all activities
    @GetMapping("/list")
    public List<Activities> listActivities() {
        return activitiesService.listAllActivities();
    }

    //filter map
    @GetMapping("/filter")
    public List<Activities> filterActivitiesByDescription(@RequestParam String description) {
        return activitiesService.filterActivitiesByDescription(description);
    }

    // get activities by completion status
    @GetMapping("/status/{complete}")
    public List<Activities> getActivitiesByCompletionStatus(@PathVariable boolean complete) {
        return activitiesService.getActivitiesByCompletionStatus(complete);
    }

    // Remove activity
    @DeleteMapping("/remover/{id}")
    public String removeActivity(@PathVariable Long id) {
        boolean hasRemoved = activitiesService.removeActivity(id);
        return hasRemoved ? "Activity successfully removed" : "Activity not found";
    }

    // remove all activities
    @DeleteMapping("/remove/all")
    public String removeAllActivities() {
        activitiesService.removeAllActivities();
        return "All activities have been successfully removed";
    }

    @PutMapping("/update/{currentId}/{newId}")
    public Activities updateActivityId(@PathVariable Long currentId, @PathVariable Long newId) {
        return activitiesService.updateActivityId(currentId, newId);
    }

    // Convert list in map
    @GetMapping("/convert")
    public Map<Long, String> converterListToMap() {
        List<Activities> activitiesToConvert = activitiesService.listAllActivities();
        return activitiesService.convertListToMap(activitiesToConvert);
    }
}
