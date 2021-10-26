# simple-REST-app

```
Some API endpoints:

   Getting all rooms:
     /rooms
     Optional params: roomType (NORMAL or PREMIUM) and roomStatus (FREE or TAKEN),
     e.g /rooms?roomType=NORMAL&roomStatus=TAKEN
    
   Getting all clients (requires employee authorization):
     /clients
     Required params: userLogin and accessCode
    
   Getting all employess (requires employee authorization):
     /employees
     Required params: userLogin and accessCode
    
   Adding employee (requires employee authorization and admin status):
     /add-employee
     Required params: userLogin, accessCode
     Example request body: 
    	{
	    "firstName": "Robert",
	    "lastName": "Smith",
	    "employeeLogin": "rSmith",
	    "employeeAccessCode": "rSmith1234",
	    "isAdmin": false
    	}
    
Default employee access data:
  admin:
    userLogin: "jsmith", accessCode: "1234"
  normal employee:
    userLogin: "asmith", accessCode: "5678"
 ```
