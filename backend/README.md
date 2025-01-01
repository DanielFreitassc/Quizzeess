# API Documentation

## Endpoints

### POST /user

**Description**: Create a new user.

**Request Body:**
```json
{
    "fullName": "Fulano de Tal",
    "username": "fulano03",
    "image": "foto",
    "password": "Senha12345",
    "language": "PORTUGUESE",
    "birthDate": "07/02/2003"
}
```

**Response:**
- **Status Code**: `201 Created`
- **Response Body:**
```json
{
    "id": 2,
    "fullName": "Fulano de Tal",
    "username": "fulano03",
    "image": "foto",
    "birthDate": "2003-02-07",
    "language": "PORTUGUESE"
}
```

---

### GET /user

**Description**: Retrieve a paginated list of users.

**Query Parameters:**
- `page` (integer, optional): The page number to retrieve. Default is `0`.
- `size` (integer, optional): The number of users per page. Default is `10`.

**Example Request:**
```
GET /user?page=0&size=10
```

**Response:**
- **Status Code**: `200 OK`
- **Response Body:**
```json
{
    "content": [
        {
            "id": 2,
            "fullName": "Fulano de Tal",
            "username": "fulano03",
            "image": "foto",
            "birthDate": "2003-02-07",
            "language": "PORTUGUESE"
        },
        {
            "id": 1,
            "fullName": "admin",
            "username": "admin",
            "image": "foto",
            "birthDate": "2003-02-07",
            "language": "PORTUGUESE"
        }
    ],
    "pageable": {
        "pageNumber": 0,
        "pageSize": 20,
        "sort": {
            "empty": true,
            "sorted": false,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "first": true,
    "size": 20,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "numberOfElements": 2,
    "empty": false
}
```

---

### GET /user/{id}

**Description**: Retrieve a user by their ID.

**Path Parameters:**
- `id` (integer, required): The ID of the user to retrieve.

**Example Request:**
```
GET /user/2
```

**Response:**
- **Status Code**: `200 OK`
- **Response Body:**
```json
{
    "id": 2,
    "fullName": "Fulano de Tal",
    "username": "fulano03",
    "image": "foto",
    "birthDate": "2003-02-07",
    "language": "PORTUGUESE"
}
```

---

### POST /auth/login

**Description**: Authenticate a user and generate a token.

**Request Body:**
```json
{
    "username": "fulano03",
    "password": "Senha12345"
}
```

**Response:**
- **Status Code**: `200 OK`
- **Response Body:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI..."
}
```

---

### DELETE /user/{id}

**Description**: Delete a user by their ID.

**Path Parameters:**
- `id` (integer, required): The ID of the user to delete.

**Example Request:**
```
DELETE /user/2
```

**Response:**
- **Status Code**: `200 OK`
- **Response Body:**
```json
{
    "id": 2,
    "fullName": "Fulano de Tal",
    "username": "fulano03",
    "image": "foto",
    "birthDate": "2003-02-07",
    "language": "PORTUGUESE"
}
```

---

### GET /validation

**Description**: Validate the current session or token.

**Response:**
- **Status Code**: `200 OK`
- **Response Body:**
```json
{
    "message": "Autorizado",
    "role": "USER"
}
```

---

## Authorization

All endpoints, except `/auth/login`, require a Bearer token for authorization. The token is returned in the response of the `/auth/login` endpoint and must be included in the `Authorization` header of subsequent requests:

```
Authorization: Bearer <token>
```

