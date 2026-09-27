# RentVideo — Video Rental REST API

Spring Boot 3 REST API with MySQL persistence, HTTP Basic Authentication, BCrypt password hashing, and CUSTOMER/ADMIN authorization.

## Run
Install Java 17+, Maven, and MySQL. Set `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` if needed (defaults in application.properties). Run `mvn spring-boot:run`.

## Register
`POST /api/auth/register` (public)
```json
{"email":"customer@example.com","password":"Pass123!","firstName":"Sam","lastName":"Kumar"}
```
Role defaults to CUSTOMER. The take-home asks for role input, so this implementation accepts CUSTOMER or ADMIN in registration. In a real public production application, ADMIN assignment should be restricted to trusted provisioning.

## Basic Auth / Login
Use email as Basic Auth username and the registered password. `GET /api/auth/login` returns success when credentials are valid.

## Video APIs
Authenticated CUSTOMER or ADMIN:
- `GET /api/videos/available`
- `GET /api/videos`
- `GET /api/videos/{id}`

ADMIN only:
- `POST /api/videos`
- `PUT /api/videos/{id}`
- `DELETE /api/videos/{id}`

Video JSON: `{"title":"Inception","director":"Christopher Nolan","genre":"Sci-Fi","available":true}`

Status codes: 201 created, 200 success, 204 deleted, 400 validation, 401 unauthenticated, 403 forbidden, 404 missing video, 409 duplicate email.

This assignment focuses on auth and video catalog CRUD; checkout/return workflows are outside the stated requirements.
