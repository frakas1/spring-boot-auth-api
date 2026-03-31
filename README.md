# Spring Boot Authentication API

A production-style authentication API built with Spring Boot, designed to be
reusable across multiple applications. Implements secure user registration and
login using JWT-based stateless authentication.

## Features
- User registration and login
- Secure password hashing with BCrypt
- JWT-based stateless authentication
- Request validation with meaningful error responses
- Global exception handling
- Designed to be frontend-agnostic (React, Next.js, etc.)

## Tech Stack
- Java 21 (LTS)
- Spring Boot 4.0
- Spring Security
- JWT (jjwt 0.12.6)
- MySQL
- Hibernate / JPA
- Maven

## Authentication Flow
1. **Register** — User submits credentials, password is hashed with BCrypt and stored
2. **Login** — Credentials are verified, a signed JWT is returned
3. **Authenticated Requests** — Client attaches JWT to the `Authorization` header,
   validated by a security filter on every request, no server-side sessions

## API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive a JWT |
| GET | `/auth/me` | Get current authenticated user |

## Example

**Request** `POST /auth/login`
```json
{
  "username": "frakas",
  "password": "secret123"
}
```

**Response**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

## Future Improvements
- Refresh token support
- Role-based access control (RBAC)
- Rate limiting on login endpoint
- Docker support