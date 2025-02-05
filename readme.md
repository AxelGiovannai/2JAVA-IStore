# 2JAVA-IStore

## Description
2JAVA-IStore is a Java-based application that manages a whitelist of email addresses. It uses Hibernate for ORM, MySQL as the database, and Maven for project management.

## Features
- Add email to whitelist
- Check if an email is whitelisted
- Retrieve all whitelisted emails
- Delete email from whitelist

## Technologies Used
- Java
- Hibernate
- MySQL
- Maven

## Setup Instructions


### Prerequisites
- Java 11 or higher
- Maven
- MySQL

### Steps
1. Clone the repository:
    ```sh
    git clone https://github.com/AxelGiovannai/2JAVA-IStore.git
    cd 2JAVA-IStore
    ```

2. Docker:
    - Container name : 2java-Isotre
    - Image : mysql: 9.1.0
    - Port : 3306
    - MYSQL_DATABASE : Istore
    - User : root
    - MYSQL_ROOT_PASSWORD: root
    - MYSQL_USER : user
    - MYSQL_PASSWORD : user

3. Configure the database:
    - Create a MySQL database named `whitelist_db`.
    - Update the database connection settings in `src/main/resources/hibernate.cfg.xml`.

## Usage
- SuperAdmin connection :  email: admin@admin.com password: AdminPassword
- To add an email to the whitelist, use the `addEmailToWhitelist` method in `WhitelistService`.
- To check if an email is whitelisted, use the `isEmailWhitelisted` method in `WhitelistService`.
- To retrieve all whitelisted emails, use the `getAllEmails` method in `WhitelistService`.
- To delete an email from the whitelist, use the `deleteEmailFromWhitelist` method in `WhitelistService`.

## License
This project is licensed under the MIT License.