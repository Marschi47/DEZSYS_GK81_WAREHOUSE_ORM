# DEZSYS_GK862_DATAWAREHOUSE_ORM

## Introduction
This project demonstrates the interaction between a Java Spring Boot application and a MySQL database using Object-Relational Mapping (ORM) and Spring Data JPA. It is based on the Spring tutorial "Accessing data with MySQL" and has been extended to simulate a simple Data Warehouse system.

## Project Protocol & Implementation Steps
1. **Database Setup:** Started a local MySQL service and created a database named `example`.
2. **Project Configuration:** Configured the `application.properties` file in `src/main/resources` to connect to the local MySQL database using the root user.
3. **Data Model Creation:** * Created two entities: `DataWarehouse` and `Product`.
   * Established a **One-to-Many** relationship between them (`@OneToMany` in DataWarehouse and `@ManyToOne` in Product) so that one warehouse can contain multiple products.
   * Used the `@JsonIgnore` annotation on the warehouse reference in the Product class to prevent infinite recursion during JSON serialization.
4. **Repositories:** Implemented `DataWarehouseRepository` and `ProductRepository` by extending Spring's `CrudRepository`.
5. **REST Controller:** Created a `WarehouseController` with two main endpoints:
   * `POST /warehouse/setup`: Automatically generates and inserts 2 data warehouse records and 10 product records into the database.
   * `GET /warehouse/all`: Retrieves and displays all warehouses and their assigned products in JSON format.

## How to Run

**1. Database Preparation:**

Connect to MySQL Shell:
```bash
docker exec -it mysql-db mysql -u root -prootpassword
```

Make sure your MySQL server is running and create the required database:
```sql
CREATE DATABASE example;
```

**2. Build and Run:**
Clean and start the Spring Boot application:

```bash
./gradlew clean
./gradlew bootRun

```

**3. Test the Application:**
Once Tomcat is running on port 8080, open a new terminal and insert the initial data:

```bash
curl -X POST http://localhost:8080/warehouse/setup

```

To view the data, run:

```bash
curl http://localhost:8080/warehouse/all

```

---

## Questions

**What is ORM and how is JPA used?**
ORM (Object-Relational Mapping) is a programming technique that maps application domain model objects (like Java classes) to tables in a relational database. It eliminates the need to write complex SQL queries manually. JPA (Jakarta Persistence API) is the standard API specification in Java for ORM. It provides standard annotations (like `@Entity` or `@Id`) and interfaces, which are implemented by frameworks like Hibernate (used by default in Spring Boot).

**What is the application.properties used for and where must it be stored?**
The `application.properties` (or `application.yml`) file is used to store configuration settings for the Spring Boot application. This includes database connection properties (URL, username, password), server port configurations, and JPA/Hibernate settings (e.g., auto-updating the database schema). It must be stored in the `src/main/resources` directory.

**Which annotations are frequently used for entity types? Which key points must be observed?**

* `@Entity`: Marks the class as a JPA entity mapped to a database table.
* `@Id`: Specifies the primary key of the entity.
* `@GeneratedValue`: Defines the generation strategy for the primary key (e.g., auto-increment).
* `@OneToMany` / `@ManyToOne`: Defines the relationships between different entities.
* **Key points to observe:** Every entity must have an `@Id` (primary key). The class must have a public or protected no-argument constructor and cannot be declared as `final`.

**What methods do you need for CRUD operations?**
When using Spring Data's `CrudRepository`, basic CRUD methods are provided out of the box:

* **C**reate: `save(entity)` or `saveAll(entities)`
* **R**ead: `findById(id)`, `findAll()`
* **U**pdate: `save(entity)` (if the entity has an existing ID, it gets updated instead of newly created)
* **D**elete: `deleteById(id)`, `delete(entity)`, or `deleteAll()`