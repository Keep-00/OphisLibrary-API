<div align="center">

# 📚 Ophis Library API

<p align="center">
  <b>REST API for manga, manhwa, and manhua reading platforms.</b>
</p>

[![Java](https://img.shields.io/badge/Java-27%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker_Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)](https://flywaydb.org/)
[![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)](https://swagger.io/)

---

</div>

## 📌 About the Project

**Ophis Library API** is the backend service responsible for managing the entire ecosystem of an online reading platform for Asian comics (manga, manhwa, manhua, and webtoons).

---

## 🛠 Architecture & Bounded Contexts

The application is structured into the following core business domains:

- **🎨 Catalog:** Management of series, chapters, authors, artists, genres, and tags.
- **👤 Users & Authentication:** Profile management, permissions, and user authentication.
- **📖 Reading & Progress:** Reading history, bookmarks, and personal library tracking.

---

## 📄 Interactive Documentation (Swagger UI)

The API features interactive documentation using **SpringDoc OpenAPI (Swagger UI)**. Endpoint specifications, request/response models, and interactive testing are available directly in your browser:

- 🌐 **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- 📄 **OpenAPI Spec (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🚀 Tech Stack

- **Language:** Java 27+
- **Main Framework:** Spring Boot 4+
  - **Spring WebMVC:** RESTful API framework
  - **Spring Data JPA:** Data persistence and ORM mapping
  - **Spring Security:** Authentication, authorization, and access control
  - **Spring Validation:** Declarative DTO validation
  - **Spring Actuator:** Operational monitoring and metrics
  - **Spring Boot Docker Compose:** Automatic container management during development
- **Database:** PostgreSQL
- **Database Migrations:** Flyway DB
- **Documentation:** SpringDoc OpenAPI / Swagger UI
- **Dependency Management:** Maven

---

## ⚙️ Getting Started

### Prerequisites

Make sure you have the following installed on your machine:
- **JDK 27** or higher
- **Docker Engine** & **Docker Compose**
- **Maven** *(optional, as the repository includes the `./mvnw` wrapper)*

### 1. Clone the repository

```bash
git clone https://github.com/Keep-00/OphisLibrary-API.git
cd OphisLibrary-API

```

### 2. Run the application

Thanks to `spring-boot-docker-compose`, the **PostgreSQL** container configured in `compose.yaml` will automatically start alongside the application.

```bash
./mvnw spring-boot:run

```

> ⚠️️ **Note:** Ensure the Docker daemon is running before executing the command above.

---

## 🧪 Running Tests

To execute unit and integration tests:

```bash
./mvnw test

```

---

## 📄 License

This project is licensed under the [MIT License](https://www.google.com/search?q=LICENSE). See the license file for details.

---
