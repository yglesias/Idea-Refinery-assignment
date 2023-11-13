# Idea-Refinery-assignment
Idea Refinery assignment

The assignment is based on these simple requirements:

Please create an empty Spring Boot project.

Add the following REST API endpoints

GET /tasks <- Returns all tasks
GET /tasks/:id <- Returns Task by ID

POST/tasks <- Creates a Task
PUT /tasks/:id <- Updates a Task by ID

Task model should include:

title: String
description: String
completed: Boolean
createDate: Date
completed: Date

Store the task data in memory for retrieval and updates, no database required.

The requirements were very lose, so I took the liberty to add edits making title and description required fields.  

The project was compiled and run using Java 17.  To run the project, you can use the following maven command:

mvn spring-boot:run

To exercise the endpoints you can use a rest client such as postman or use the following curl commands:

To create a task:
curl --location --request POST 'http://localhost:8080/tasks' \
--header 'Content-Type: application/json' \
--data '{
    "title":"First Task",
    "description": "This is the first Task"
}'


To update a task by ID:
curl --location --request PUT 'http://localhost:8080/tasks/:id' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1,
    "task": {
        "title":"Update of the first task",
        "description": "This Task was updated to complete",
        "completed": true
    }
}'


To list all tasks:
curl --location --request GET 'http://localhost:8080/tasks' \
--header 'Content-Type: application/json' \
--data ''


To list a task by ID:
curl --location --request GET 'http://localhost:8080/tasks/:id' \
--header 'Content-Type: application/json' \
--data '{
    "id": 1
}'



