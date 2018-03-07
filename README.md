a# TwitterApp
TwitterApp is a coding challenge to develop RestFul APIs for twitter using Spring Boot, H2 In-memory SQL database and Spring Basic authenitcation.

All the below APIs are authenticated using Spring Basic Authentication. 
The handle of the user is used to validate if the user is present in the system or not. 
Since the people table didn't have password field, the handle alone is used to validate the user.
To get the id of the current user for all below APIs, the id corresponding to the handle given in the authorization header is used. 

The entire project is divided into different layers - Entity, DAO, Service, DTO, Controller.

All the models related to the app are created under entity. The DAO layer is used to query from the database and return results to the service layer.
The service layer contains the business logic for the APIs. 
For example, to find the shortest distance between the current user and target user, the BFS logic to find the distance is written in service layer.
The message from DAO layer is not directly passed to controller. 
Instead, for some cases where transformation is required, DTO layer is used to perform transformation from DAO to DTO.
Finally, the DTO object is returned by the controller.

Custom exceptions are created to throw application related exceptions. 
For example, If a user is trying to follow another user whom he already follows, an exception will be thrown.
A Controller Advice is used to handle exceptions where the corresponsing Response status code and message is set for each custom exceptions and returned to the user.

**Testing**

Junit has been used to test all the DAO and services methods.

**Rest API documentation**

To document the REST APIs, Swagger has been used. Swagger generates a user-friendly UI where the users can see all the RestAPI endpoints exposed along with the parameter details.
It also allows users to try out the endpoints and check the results.
 
**API details**

The following APIs have been developed

1. An API to get the message list of current user. This contains both the messages sent by current user and also the messages that have been sent by others, the current user follows.
You can also include a search parameter to filter the messages. If the search parameter is not mentioned it will return all the messages.
The output will have all the user's feeds under "myFeeds" object and the feeds from the users he follows in the "followingFeeds" object.


RestAPI endpoint - http://localhost:8080/twitter/messages?search=do

Method - GET

Sample output
```json
  {
  "myFeeds": [
    {
      "id": 1,
      "user": {
        "id": 1,
        "handle": "batman",
        "name": "Bruce Wayne"
      },
      "content": "It's not who I am underneath, but what I do that defines me."
    },
    {
      "id": 43,
      "user": {
        "id": 1,
        "handle": "batman",
        "name": "Bruce Wayne"
      },
      "content": "venenatis a, magna. Lorem ipsum dolor sit amet, consectetuer adipiscing"
    }
  ],
  "followingFeeds": [
    {
      "id": 4,
      "user": {
        "id": 4,
        "handle": "daredevil",
        "name": "Matt Murdock"
      },
      "content": "Violence doesn't discriminate. It comes as cold and bracing as a winter breeze and it leaves you with a chill you can't shake off."
    },
    {
      "id": 10,
      "user": {
        "id": 10,
        "handle": "profx",
        "name": "Charles Xavier"
      },
      "content": "Just because someone stumbles and loses their path doesn't mean they can't be saved."
    }
  ]
}
```
2. An API to get the list of people the current user is following and also the followers of the current user. The output will have all the user's followers under "followers" object and
the list of users he is following under "following" object.

RestAPI endpoint - http://localhost:8080/twitter/friends

Method - GET

Sample output
```json
  {
  "followers": [
    {
      "id": 10,
      "handle": "profx",
      "name": "Charles Xavier"
    },
    {
      "id": 8,
      "handle": "spiderman",
      "name": "Peter Parker"
    }
  ],
  "following": [
    {
      "id": 8,
      "handle": "spiderman",
      "name": "Peter Parker"
    },
    {
      "id": 10,
      "handle": "profx",
      "name": "Charles Xavier"
    },
    {
      "id": 5,
      "handle": "alfred",
      "name": "Alfred Pennyworth"
    }
  ]
}
```
3. An API to follow another user. This has to be a POST request to the endpoint and the {id} mentioned below is the id of the user the current user wants to follow. 
This will throw exception if a user tries to follow another user whom he already follows.

RestAPI endpoint - http://localhost:8080/twitter/users/{id}/follow

Method - POST

4. An API to unfollow a following user. This has to be a DELETE request to the endpoint and the {id} mentioned below is the id of the user the current user wants to unfollow.
This will throw exception if a user tries to unfollow another user whom he does not follow.

RestAPI endpoint - http://localhost:8080/twitter/users/{id}/follow

Method - DELETE

5. An API to get the shortest distance to another user. The shortest distance is calculated as the number of hops to reach the target user ({id} is used to mention the target user) using the users the currentuser follow.

RestAPI endpoint - http://localhost:8080/twitter/hops/{id}

Method - GET

Sample output
```json
{
  "currentUser": {
    "id": 1,
    "handle": "batman",
    "name": "Bruce Wayne"
  },
  "targetUser": {
    "id": 7,
    "handle": "zod",
    "name": "Dru-Zod"
  },
  "noOfHops": 2
}
```

6. An API to get the list of users alongwith their popular follower. Popular user is one who has maximum followers.

RestAPI endpoint - http://localhost:8080/twitter/popularUsers

Method - GET

Sample output
```json
[
  {
    "user": {
      "id": 1,
      "handle": "batman",
      "name": "Bruce Wayne"
    },
    "popularUser": {
      "id": 5,
      "handle": "alfred",
      "name": "Alfred Pennyworth"
    },
    "followersCount": 6
  },
  {
    "user": {
      "id": 2,
      "handle": "superman",
      "name": "Clark Kent"
    },
    "popularUser": {
      "id": 1,
      "handle": "batman",
      "name": "Bruce Wayne"
    },
    "followersCount": 7
  },
  {
    "user": {
      "id": 3,
      "handle": "catwoman",
      "name": "Selina Kyle"
    },
    "popularUser": {
      "id": 7,
      "handle": "zod",
      "name": "Dru-Zod"
    },
    "followersCount": 7
  }
]
```


