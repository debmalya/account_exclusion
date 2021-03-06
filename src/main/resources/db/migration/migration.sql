CREATE TABLE IF NOT EXISTS `EXCLUSION_ACCOUNTS` (
    `ACCOUNT_NUMBER` varchar(20)

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS `USER_REQUESTS` (
    `REQUEST_ID` varchar(20) primary key,
    `SUBMITTED_BY` varchar(50) not null,
    `SUBMITTED_DATE` datetime not null,
    `APPROVED_BY` varchar(50),
    `APPROVAL_DATE` datetime not null,
    `STATUS` varchar(10)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
