# Technical Stack
* Maven 
* Java
* Spring-boot (spring-boot-starter-data-jpa, spring-boot-starter-data-rest, spring-data-rest-webmvc, spring-boot-starter-security)
* Angular
* H2
* Flyway-db ( for migration )
* JWT 
* lombok 
* jcasbin
* frontend-maven-plugin

# How to run the application
```
mvn spring-boot:run
```
The above one will do the following things :
* It will install Node v12.16.3 if it is not already installed.
* It will install NPM 6.14.4 if it is not already installed.
* npm install
* ng build
* compile java code
* run the application at port 5000
* after successful running of the `mvn sprint-boot:run`. Please open http://localhost:5000


#### Use case: Given Tom login to the system,  when Tom Add, Modify, Remove account number , then it will be  reflected as Pending approval in USER_REQUEST table

##### Tom logged in
![Login Screen](./docs/LoginScreen.png)
##### Add accounts to be excluded
![Add accounts](./docs/AccountSubmission.png)

#### Use case: Given Tom login to the system , when Tom  search list of account numbers , then system will display all accounts from EXCLUSION_ACCOUNTS
![Search Accounts](./docs/SearchAccount.png)
![Search Account Result](./docs/AccountSearchResult.png)

#### Given John login to the system, when John approve user submitted request ,  then it will persist in EXCLUSION_ACCOUNTS
#### John logged in and get the list of pending requests in a paginated way.
![Approve or reject pending requests](./docs/ApproveReject.png)
#### After taking action on pending requests it will not show any pending requests.
![Pending requests](./docs/NoPendingRequests.png)
