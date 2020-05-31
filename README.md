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
