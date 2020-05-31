# Technical Stack
* Maven 
* Java
* Spring-boot
* Angular
* H2
* Flyway-db ( for migration )

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
