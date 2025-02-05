# SSO System API test by Spring boot 3.4.2
This API is for testing create SSO user to the database
## Run project
Run the following command in a terminal window [ Reminder: This project uses Maven to run, so you need to have the Maven on your PC ]
```
mvn spring-boot:run
```
(Optional) : Import the project, then you can run the project though the main file named 'SpringTestApplication'. if your IDE has a shortcut button for running the project
```
src/main/java/com/dornor/spring_test/SpringTestApplication.java
```

## Testing steps
STEP 1 : Use this link to test which is generate from swagger ui
```
http://localhost:8080/apitest/swagger-ui.html
```
STEP 2 : There are two functions that you can access, /getAll and /gentoken
![image](https://github.com/user-attachments/assets/715f5071-8964-4d66-9f4f-84e61856f1cc)

STEP 3 : Create the SSO user within /gentoken, what you need to do is clicking on the ' Try it out ' button and sending the request body within the template below
```
{
  "request_date": "string",
  "ssoType": "string",
  "systemId": "string",
  "systemName": "string",
  "systemTransactions": "string",
  "systemPrivileges": "string",
  "systemUserGroup": "string",
  "systemLocationGroup": "string",
  "userId": "string",
  "userFullName": "string",
  "userRdOfficeCode": "string",
  "userOfficeCode": "string",
  "clientLocation": "string",
  "locationMachineNumber": "string",
  "tokenId": "string"
}
```
Then you will receive the response.
If you sent the data successfully, it will show the response status which have 3 response you can receive.
```
response 200 : ทำรายการเรียบร้อย
response 404 : ไม่พบข้อมูล
response 500 : ไม่พบฐานข้อมูลทำรายการ
```

