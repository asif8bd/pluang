Step 1: Run the Application. The application will be started on the Tomcat port 8080

Step 2: To get the OAUTH2 token, Open the POSTMAN, select POST method, and set URL: http://localhost:8080/login

Step 3: Add these data in Authorization Section of POSTMAN.
	Type: Choose Basic Auth from the dropdown.
	Username: ashid (The clientId which I have mentioned in ConfigOAuth2.java)
	Password: ashid-secret-key (The clientSecret which I have mentioned in ConfigOAuth2.java)
	
Step 4: Fill the data in Body Section of POSTMAN.
	Under this section select x-www-form-urlencoded. 
	Now add the following key-value pair:
	grant_type: password
	username: ashid
	password: password

Step 5: Send the POST Request of API http://localhost:8080/login from POSTMAN, you will get the OAUTH2 token.

Step 6: Add the JSON Token (access_token) inside the Header Section and also add the Content-Type as application/json in POSTMAN.
Make sure that you should add token after Bearer in the Header section.

Step 7: Go to the Authorization tab in POSTMAN and change the Type from Basic Auth to Bearer Token.

Step 8: Now Hit the API http://localhost:8080/api/name/{name} using GET method to get your required country details. 

Example: Request URL-->  http://localhost:8080/api/name/usa
API Response: 

[
    {
        "fullName": "United States of America",
        "population": "329484123",
        "currencies": [
            {
                "symbol": "$",
                "code": "USD",
                "name": "United States dollar",
                "IDR-rate": "14373.847985"
            }
        ]
    }
]

Step 9: Currency Exchange rate will be store in H2 DB for audit purpose. You can check historical exchange rate using this API:  
http://localhost:8080/api/audit using GET method.

[
    {
        "id": 1,
        "username": "ashid",
        "timestamp": "1643631543",
        "date": "2022-01-31",
        "code": "USD",
        "idrRate": "14373.847985"
    }
]




    


