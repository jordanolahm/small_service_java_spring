package com.example.Activities;

import com.example.Activities.controller.ActivitiesController;
import com.example.Activities.model.Activities;
import com.example.Activities.service.ActivitiesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(ActivitiesController.class)
class ActivitiesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivitiesService activitiesService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddNewActivity() throws Exception {
        Activities activity = new Activities(1L, "New Activity", false);
        when(activitiesService.addActivity(anyString())).thenReturn(activity);

        mockMvc.perform(post("/api/activities/add")
                        .param("description", "New Activity"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("New Activity"));
    }

    @Test
    void testEditActivity() throws Exception {
        Activities activity = new Activities(1L, "Edited Activity", true);
        when(activitiesService.editActivity(anyLong(), anyString(), anyBoolean())).thenReturn(activity);

        mockMvc.perform(put("/api/activities/edit/1")
                        .param("description", "Edited Activity")
                        .param("complete", "true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Edited Activity"));
    }

    @Test
    void testRemoveActivity() throws Exception {
        when(activitiesService.removeActivity(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/api/activities/remover/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Activity successfully removed"));
    }

    @Test
    void testListActivities() throws Exception {
        // Create mock activities
        Activities activity1 = new Activities(1L, "Activity 1", false);
        Activities activity2 = new Activities(2L, "Activity 2", true);
        when(activitiesService.listAllActivities()).thenReturn(List.of(activity1, activity2));

        mockMvc.perform(get("/api/activities/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].description").value("Activity 1"))
                .andExpect(jsonPath("$[1].description").value("Activity 2"));
    }
}