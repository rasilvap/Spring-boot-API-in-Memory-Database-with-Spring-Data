# Spring-boot-API-in-Memory-Database-with-Spring-Data

This is a standAlone builded with Spring boot using an in memory database h2 and the spring-data library to manage the databse opertations.

To create new data you can use the post endpoint: localhost:8000/events, and use the next BodyRequest example:


{
	"id": "334",
    "type": "test",
    "actor":   { "id":123, "login":30, "avatar_url":"http://www.google.com" },
    "repo": { "id":123, "name":30, "url":"http://www.google.com" },
    "created_at":"2019-05-17 14:20:20"
}
