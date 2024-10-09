package com.example.Activities.service;

import org.springframework.stereotype.Service;
import com.example.Activities.model.Activities;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivitiesService {
    private final Map<Long, Activities> activitiesMap = new HashMap<>();
    private Long counterId = 1L;

    // get along Map activities
    public Activities getActivityById(Long id) {
        return activitiesMap.get(id);
    }

    // Adding new activity
    public Activities addActivity(String describe) {
        Activities activity = new Activities(counterId++, describe, false);
        activitiesMap.put(activity.getId(), activity);
        return activity;
    }

    // Edit activity
    public Activities editActivity(Long id, String newDescribe, boolean complete) {
        Activities activity = activitiesMap.get(id);
        if (activity != null) {
            activity.setDescription(newDescribe);
            activity.setComplete(complete);
        }
        return activity;
    }

    // Remove activity
    public boolean removeActivity(Long id) {
        Activities activity = activitiesMap.get(id);
        if (activity != null && activity.isComplete()) {
            activitiesMap.remove(id);
            return true;
        }

        return false;
    }

    // Return all activities
    public List<Activities> listAllActivities() {
        List<Activities> activitiesList = new ArrayList<>(activitiesMap.values());
        return activitiesList.isEmpty() ? Collections.emptyList() : activitiesList;
    }

    // remove all activities
    public void removeAllActivities() {
        activitiesMap.clear();
    }

    // Duplicate activity
    public Activities duplicateActivity(Long id) {
        Activities originalActivity = activitiesMap.get(id);
        if (originalActivity != null) {
            Activities duplicatedActivity = new Activities(counterId++, originalActivity.getDescription(), originalActivity.isComplete());
            activitiesMap.put(duplicatedActivity.getId(), duplicatedActivity);
            return duplicatedActivity;
        }
        return null;
    }

    // Mark activity as complete
    public boolean markActivityAsComplete(Long id) {
        Activities activity = activitiesMap.get(id);
        if (activity != null) {
            activity.setComplete(true);
            return true;
        }
        return false;
    }

    // Get activities by completion status
    public List<Activities> getActivitiesByCompletionStatus(boolean complete) {
        return activitiesMap.values().stream()
                .filter(activity -> activity.isComplete() == complete)
                .collect(Collectors.toList());
    }

    // Update activity ID
    public Activities updateActivityId(Long currentId, Long newId) {
        Activities activity = getActivityById(currentId);
        if (activity != null) {
            // Remove the current activity from the map
            activitiesMap.remove(currentId);

            // Set the new ID
            activity.setId(newId);
            activitiesMap.put(newId, activity);

            return activity;
        }
        return null;
    }


    // Utility Map<Long, activities> to filter elements with description value
    public List<Activities> filterActivitiesByDescription(String description) {
        return activitiesMap.values().stream()
                .filter(activity -> activity.getDescription().contains(description))
                .collect(Collectors.toList());
    }

    // Utility to convert list of activities to map
    public Map<Long, String> convertListToMap(List<Activities> activities) {
        return activities.stream()
                .collect(Collectors.toMap(Activities::getId, Activities::getDescription));
    }
}
