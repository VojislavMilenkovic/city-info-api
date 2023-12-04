# city-info-api
City Info API challenge

## Setup Instructions

1. Clone the repository

   git clone https://github.com/VojislavMilenkovic/city-info-api.git

2. In your PostgreSql run the following SQL query:
  CREATE SCHEMA cityapi;
  CREATE TABLE cityapi.cities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    state VARCHAR(100) NOT NULL,
    region VARCHAR(100) NOT NULL,
    population VARCHAR(100) NOT NULL
);

3. Inside of your application.yml file replace the placeholders ${} with your actual config values:
   
    url: jdbc:postgresql://localhost:5432/${Database_Name}

    username: ${Database_Username}

    password: ${Database_Password}

4. After running the application you can use Postman (or similar) in order to test the endpoints.

5. To Create User in the db run the following SQL query:
      drop table cityapi.users;

      CREATE TABLE cityapi.users (
	      id SERIAL PRIMARY KEY,
         username VARCHAR(20) NOT NULL,
         password VARCHAR(20) NOT NULL
      );

6. When you inserting user, password must be encrypted. For encription you can use https://bcrypt-generator.com/.

7. Use /api/auth/login to generate token.
