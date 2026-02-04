# Spring Boot Authentication API

A production-style authentication API built with Spring Boot, designed to be reusable across multiple applications.  
Implements secure user registration and login using JWT-based authentication.

## Features

- User registration and login
- Secure password hashing with BCrypt
- JWT-based stateless authentication
- Request validation with meaningful error responses
- Global exception handling
- CORS configuration for frontend integration
- Designed to be frontend-agnostic (React, Next.js, etc.)

## Tech Stack

- Java 25
- Spring Boot
- Spring Security
- JWT (JSON Web Tokens)
- MySQL
- Hibernate / JPA
- Maven

## Authentication Process

1. Register
   - User submits username & password
   - Password is hashed using BCrypt
   - User is stored securely in the database

2. Login
   - Credentials are verified
   - A signed JWT is returned

3. Authenticated Requests
   - Client sends JWT
   - Token is validated by a security filter
   - Requests proceed without server-side sessions

## API Endpoints

| POST | "/auth/register" | Register a new user |
| POST | "/auth/login" | Login to an existing user |
