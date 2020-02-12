# Spring-boot-API-in-Memory-Database-with-Spring-Data

This is a h Spring boot Appusing an in memory database h2 and the spring-data library, there are a set of endpoints with different behaviors, the code base was built using java 8.



The definitions and requirements are listed as follow:

Each event data is a JSON entry with the following keys:

• id: This is the event unique ID.

• type: This is the event type.

• actor: The actor responsible for the event. The actor itself is a JSON entry consisting of following fields:

id: This is the actor unique ID.
login: This is the actor unique login ID.
avatar_url This is the actor avatar URL.

•repo: The repository to which this event is associated with. The repo itself is a JSON entry consisting of following fields:

id: This is the repo unique ID.

name: This is the repo name.

url: This is the repo URL.

•created_at: This is the timestamp for the event creation given in the format yyyy-MM-dd HH:mm: ss. The timezone is UTC +0.

The REST service should implement the following functionalities:



1.Erasing all the events: The service should be able to erase all the events by the DELETE request at /erase. The HTTP response code should be 200.

2.Adding new events: The service should be able to add a new event by the POST request at /events. The event JSON is sent in the 
request body. If an event with the same id already exists then the HITP response code should be 400, otherwise, the response code should be 207.

3.Returning all the events: The service should be able to return the JSON array of all the events by the GET request at /events. The HTTP response code should be 200. The JSON array should be sorted in ascending order by event ID.

4.Returning the event records filtered by the actor ID:
The service should be able to return the J.SON array of all the events which are performed by the actor ID by the GET request at /events/actors/{actorID}.
If the requested actor does not exist then HTTP response code should be 404, otherwise, the response code should be 200. The JSON array should be sorted in ascending order by event ID.

5.Updating the avatar URL of the actor: The service should be able to update the avatar URL of the actor by the PUT request at /actors.
The actor JSON is sent in the request body. If the actor with the id does not exist then the response code should be 404, or if there are other fields being updated for the actor then the HTTP response code should be 400, otherwise, the response code should be 200.

6.Returning the actor records ordered by the total number of events:
The service should be able to return the JSON array of all the actors sorted by the total number of associated events with each actor in
descending order by the GET request at /actors. If there are more than one actors with the same number of events, then order them
by the timestamp of the latest event in the descending order. If more than one actors have the same timestamp for the latest event,
then order them by the alphabetical order of login. The HTTP response code should be 200.

7.Returning the actor records ordered by the maximum streak: The service should be able to return the JSON array of all the actors
sorted by the maximum streak (i.e., the total number of consecutive days actor has pushed an event to the system) in descending order
by the GET request at /actors/streak. If there are more than one actors with the same maximum streak, then order them by the timestampof the latest event in the descending order. If more than one actors have the some timestamp for the latest event, then
order them by the alphabetical order of login. The HTTP response code should be 200.

To create new data you can use the post endpoint: localhost:8000/events, and use the next BodyRequest example:

```
{
    "id": "334",
    "type": "test",
    "actor":   { "id":123, "login":30, "avatar_url":"http://www.google.com" },
    "repo": { "id":123, "name":30, "url":"http://www.google.com" },
    "created_at":"2019-05-17 14:20:20"
}
```

## TO_DO

Implement Swagger documentation in each Endpoint.
