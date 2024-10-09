package com.example.Activities;

import com.example.Activities.model.Activities;
import com.example.Activities.service.ActivitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivitiesServiceTest {

	private ActivitiesService activitiesService;

	@BeforeEach
	void setUp() {
		activitiesService = new ActivitiesService();
	}

	@Test
	void testAddActivity() {
		Activities activity = activitiesService.addActivity("Test Activity 1");
		assertNotNull(activity);
		assertEquals("Test Activity 1", activity.getDescription());
		assertTrue(activity.getId() > 0);
	}

	@Test
	void testEditActivity() {
		Activities addedActivity = activitiesService.addActivity("Test Activity 2");
		Activities editedActivity = activitiesService.editActivity(addedActivity.getId(), "Updated Description", true);
		assertNotNull(editedActivity);
		assertEquals("Updated Description", editedActivity.getDescription());
		assertTrue(editedActivity.isComplete());
	}

	@Test
	void testListAllActivities() {
		activitiesService.addActivity("Activity 1");
		activitiesService.addActivity("Activity 2");
		List<Activities> activities = activitiesService.listAllActivities();
		assertEquals(2, activities.size());
	}
}