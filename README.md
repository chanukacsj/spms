# 🚗 Smart Parking Management System (SPMS)

Final examination project for the **Graduate Diploma in Software Engineering – ITS 1018: Software Architectures & Design Patterns II**.

---

## 📘 Project Overview

The **Smart Parking Management System (SPMS)** is a microservice-based application built using **Spring Boot**. It offers a smart and scalable solution for urban parking by enabling users to search, reserve, and manage parking spaces in real time.

---

## 🎯 Key Features

- 📍 Real-time parking space availability and reservation  
- 🚘 Vehicle registration, tracking, and exit management  
- 👤 User and owner registration with role-based access  
- 💳 Simulated digital payments with receipt generation  
- 📡 Microservices discovery via Eureka  
- ⚙️ Centralized configuration using Spring Cloud Config  
- 🚪 Single entry point through Spring Cloud Gateway  

---

## 🛠️ Technologies Used

| Technology              | Purpose                               |
|-------------------------|---------------------------------------|
| Java 17                 | Programming language                  |
| Spring Boot             | Backend framework                     |
| Spring Cloud (Eureka, Config, Gateway) | Microservices orchestration |
| Spring Data JPA         | Database access (MySQL)               |
| Maven                   | Build & dependency management         |
| Postman                 | API testing                           |

---
## 📄 Resources

![eureka_dashboard](https://github.com/user-attachments/assets/953b26ef-0af1-4269-88f8-a23074b5b394)

---
## 🧩 Microservices Architecture

- 🧾 **Config Server** – Centralized config management  
- 📘 **Eureka Server** – Service discovery & registry  
- 🚪 **API Gateway** – Routes and filters requests  
- 👥 **User Service** – Manages users and owners  
- 🚗 **Vehicle Service** – Handles vehicle-related data  
- 🅿️ **Parking Service** – Manages parking spaces and reservations  
- 💰 **Payment Service** – Handles payment simulation and receipts  

---

## 📈 Functional Highlights

- 🔍 Search & reserve parking spaces in your area  
- 💸 Make digital payments and receive receipts  
- ⌛ Simulate vehicle entry and exit  
- 🧾 Access transaction history and usage logs  
- 📊 Analytics for zones, cities, and owners  

---

## ✅ How to Run

1. Start services in the following order:
   - `config-server`
   - `eureka-server`
   - `api-gateway`

2. Then run microservices:
   - `user-service`
   - `vehicle-service`
   - `parking-service`
   - `payment-service`

3. Import `smart parking management system.postman_collection.json` into Postman

4. Use Postman to test the APIs (`register`, `login`, `add vehicle`, `reserve parking`, `make payment`, etc.)

---

## 📄 API Testing

- Postman Collection: ✅ Available  
- Authentication: 🔐 JWT token-based  
- Roles: `USER`, `OWNER` supported  
- Sample endpoints:
  - `POST /api/v1/users/register`
  - `POST /api/v1/vehicles/add`
  - `GET /api/v1/parking/available`
  - `POST /api/v1/payments/create`

---

## 👨‍💻 Author

**Chanu csj**  
Graduate Diploma in Software Engineering – IJSE  
📧 Email: [chanucsj@gmail.com]  
🔗 GitHub: [github.com/your-username](https://github.com/chanucsj)

---

> Designed with 💡 microservices principles and real-world scalability in mind.
