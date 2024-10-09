# Activities API

This is a simple RESTful API for managing activities using Spring Boot. It allows users to add, edit, list, filter, and remove activities, as well as update their IDs.

## Server Port
[http://localhost:8080/api/activities/](http://localhost:8080/api/activities/)

## Endpoints

### 1. Add New Activity
- **Method:** `POST`
- **Endpoint:** `/add`
- **Description:** Creates a new activity with a specified description.
- **Parameters:** 
  - `description` (String): The description of the activity.
- **Request Example:**
  ```bash
  curl -X POST "http://localhost:8080/api/activities/add?description=New%20Activity"
  ```

### 2. Edit Existing Activity 
- **Method:** `PUT`
- **Endpoint:** `/edit/{id}`
- **Description:** Updates the details of an existing activity based on its ID.
- **Parameters:** 
  - `id` (Long): The ID of the activity to edit.
  - `description` (String): The new description of the activity.
  - `complete` (boolean): The completion status of the activity.
- **Request Example:**
  ```bash
  curl -X PUT "http://localhost:8080/api/activities/edit/1?description=Edited%20Activity&complete=true"
  ```

### 3. List All Activities 
- **Method:** `GET`
- **Endpoint:** `/list`
- **Description:** Retrieves a list of all activities.
- **Request Example:**
  ```bash
  curl -X GET "http://localhost:8080/api/activities/list"
  ```

### 4. Filter Activities by Description
- **Method:** `GET`
- **Endpoint:** `/filter`
- **Description:** Filters activities based on a description keyword.
- **Parameters:** 
  - `description` (String): The description keyword to filter activities.
- **Request Example:**
  ```bash
  curl -X GET "http://localhost:8080/api/activities/filter?description=keyword"
  ```

### 5. Get Activities by Completion Status
- **Method:** `GET`
- **Endpoint:** `/status/{complete}`
- **Description:** Retrieves activities based on their completion status (complete or incomplete).
- **Parameters:** 
  - `complete` (boolean): The completion status to filter activities.
- **Request Example:**
  ```bash
  curl -X GET "http://localhost:8080/api/activities/status/true"
  ```

### 6. Remove Activity 
- **Method:** `DELETE`
- **Endpoint:** `/remove/{id}`
- **Description:** Deletes an activity based on its ID if it is marked as complete.
- **Parameters:** 
  - `id` (Long): The ID of the activity to remove.
- **Request Example:**
  ```bash
  curl -X DELETE "http://localhost:8080/api/activities/remove/1"
  ```

### 7. Update Activity ID 
- **Method:** `PUT`
- **Endpoint:** `/update/{currentId}/{newId}`
- **Description:** Updates the ID of a specified activity. The old ID will be replaced by the new ID.
- **Parameters:** 
  - `currentId` (Long): The current ID of the activity.
  - `newId` (Long): The new ID to assign to the activity.
- **Request Example:**
  ```bash
  curl -X PUT "http://localhost:8080/api/activities/update/1/100"
  ```

### 8. Remove All Activities 
- **Method:** `DELETE`
- **Endpoint:** `/remove/all`
- **Description:** Deletes all activities from the list.
- **Request Example:**
  ```bash
  curl -X DELETE "http://localhost:8080/api/activities/remove/all"
  ```

### 9. Convert List of Activities to Map
- **Method:** `GET`
- **Endpoint:** `/convert`
- **Description:** Converts the list of activities into a map, with IDs as keys and descriptions as values.
- **Request Example:**
  ```bash
  curl -X GET "http://localhost:8080/api/activities/convert"
  ```

### Notes:
- This structure was built with [Spring Initializr](https://start.spring.io/). 
- The Java version is 17.
- Using Maven to manage libraries and dependencies.
