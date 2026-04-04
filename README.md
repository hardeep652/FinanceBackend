i# 💰 Finance Backend System (Zorvyn Assignment)

## 📌 Overview

This project is a backend system for managing financial records with role-based access control. It allows users to track income and expenses, generate dashboard insights, and enforce permissions based on user roles.

The system is built using **Spring Boot**, follows clean architecture principles, and is deployed using **Docker + Render** with a **PostgreSQL database**.

---

## 🚀 Features

### 1. User & Role Management

* Create and manage users
* Roles:

  * **ADMIN** → Full access (users + records)
  * **ANALYST** → View records + insights
  * **VIEWER** → View dashboard only
* User status:

  * ACTIVE / INACTIVE

---

### 2. Financial Records Management

* Create, update, delete financial records
* Fields:

  * Amount
  * Type (INCOME / EXPENSE)
  * Category
  * Date
  * Notes
* Filtering:

  * By type
  * By category
  * By date range

---

### 3. Dashboard APIs

* Total Income
* Total Expense
* Net Balance
* Category-wise breakdown
* Recent activity

---

### 4. Authentication & RBAC

* JWT-based authentication
* Role-based authorization enforced at service layer
* Active user validation

---

### 5. Pagination

* Paginated record listing
* Page + size parameters supported

---

### 6. Error Handling

* Centralized exception handling
* Proper HTTP status codes
* Clean error responses

---

### 7. Data Persistence

* PostgreSQL database (Render hosted)
* Hibernate ORM
* Automatic schema generation

---

## 🛠 Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA (Hibernate)
* PostgreSQL
* Docker
* Render (Deployment)

---

## ⚙️ Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/hardeep652/FinanceBackend.git
cd Financebackend
```

---

### 2. Configure Environment Variables

Create environment variables:

```env
DB_URL=jdbc:postgresql://<host>:5432/<db>
DB_USERNAME=<username>
DB_PASSWORD=<password>
JWT_SECRET=<your_secret>
PORT=8080
```

---

### 3. Run using Docker

```bash
docker build -t finance-backend .
docker run -p 8080:8080 finance-backend
```

---

### 4. Access API

```
http://localhost:8080
```

---

## 🔐 Authentication Flow

1. Login:

```http
POST /api/auth/login
```

```json
{
  "email": "admin@example.com"
}
```

2. Use token:

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 API Endpoints

### 🔹 User APIs (ADMIN only)

* `POST /api/users/create`
* `GET /api/users`
* `PUT /api/users/{id}/role`
* `PUT /api/users/{id}/status`

---

### 🔹 Financial Records

* `POST /api/records` (ADMIN)
* `GET /api/records` (ADMIN + ANALYST)
* `PUT /api/records/{id}` (ADMIN)
* `DELETE /api/records/{id}` (ADMIN)

---

### 🔹 Dashboard

* `GET /api/dashboard/summary`
* `GET /api/dashboard/category`
* `GET /api/dashboard/recent`

---

## 👤 Default Admin

A default admin user is auto-created at startup

---

## 📥 Sample JSON Requests

### 🔐 Login
**Endpoint:** `POST /api/auth/login`
**Request Body:**
```json
{
  "email": "admin@example.com"
}
```

### 👤 Create User (ADMIN)
**Endpoint:** `POST /api/users/create`
**Request Body:**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "role": "ANALYST",
  "status": "ACTIVE"
}
```

###Get All Users(ADMIN)
**Endpoint**: `GET api/users`

###UPDATE USER Role(ADMIN)
**Endpoint**:`PUT/api/users/{id}/role
```json
{
  "role":"VIEWER"
}
```

###UPDATE USER Status
**Endpoint**:`PUT/api/users/{id}/status
```json
{
  "status":"INACTIVE"
}
```


### 💰 Create Financial Record (ADMIN)
**Endpoint:** `POST /api/records`
**Request Body:**
```json
{
  "amount": 5000,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-01",
  "notes": "Monthly salary"
}
```

### ✏️ Update Financial Record (ADMIN)
**Endpoint:** `PUT /api/records/{id}`
**Request Body:**
```json
{
  "amount": 6000,
  "category": "Updated Salary"
}
```

###GET Records(ADMIN,ANALYST)
**Endpoint**:GET/api/records?page=0&size=5

### 🔍 Filter Records
- By Type: `GET /api/records?type=INCOME`
- By Category: `GET /api/records?category=Food`
- By Date Range: `GET /api/records?startDate=2026-04-01&endDate=2026-04-30`


## 🧠 Design Decisions

### ✔ Service-layer RBAC

Instead of relying only on annotations, role validation is done in service layer for better control and clarity.

### ✔ JWT via Filter + ThreadLocal

User identity is extracted from JWT and stored per request for easy access across services.

### ✔ Aggregation Logic in Backend

Dashboard computations are handled server-side to demonstrate backend data processing capability.

---

## ⚖️ Trade-offs

* No password-based auth (simplified for assignment)
* Filtering logic is basic (not combined queries)
* No caching layer (can be added for scalability)
* No refresh tokens (kept minimal)

---

## 🔧 Optional Enhancements (Future Scope)

* Full Spring Security integration
* Refresh tokens
* Rate limiting
* Search APIs
* Soft delete
* Swagger documentation
* Unit & integration tests

---

## 🌐 Deployment

* Backend deployed on Render using Docker
* PostgreSQL database hosted on Render

---

## 📬 Final Note

This project focuses on:

* Clean backend architecture
* Real-world API design
* Security (RBAC + JWT)
* Deployment readiness

---

**Built with focus on real backend engineering practices 🚀**
