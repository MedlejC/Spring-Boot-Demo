# Spring Boot Management System

<p align="center"> 
<img src="https://skillicons.dev/icons?i=java,spring,postgresql,postman,idea"/>
</p>


## Overview
This project demonstrates a management system for products, clients and sales. The system is built with Spring Boot, integrating CRUD operations and entity relationships. Designed to mimic real-world applications, this system provides a comprehensive example of how to implement and manage a database-driven application using Spring Boot and PostgreSQL.

## Project Description
This Spring Boot application is designed to manage products within a retail context. It includes:
- RESTful API endpoints for creating, retrieving, updating, and deleting products, clients & sales.
- Management of relationships between entities such as products, clients, and sales.
- Utilization of PostgreSQL for data persistence, showcasing real-world database integration with Spring Boot.

## Class Diagram

![SpringDemo](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/ba93fc06-d247-4148-b2a8-340ca1cf66a7)

- **Client** has many **Sales** (one-to-many):  
  One client can have multiple sales & many sales can be related to one client.
- **Sale** has many **Products** (one-to-many):
  One sale can include multiple products.
- **Product** belongs to one **Sale** (many-to-one): 
  Many products can be part of one sale.

**Note:** 
- PK: Primary Key
- FK: Foreign Key
- Auto-Inc. : Auto-Increment


## Tech Stack
-  **Backend:**  
  ![Static Badge](https://img.shields.io/badge/Spring-%236DB33F?style=for-the-badge&logo=spring&labelColor=black)

	![Static Badge](https://img.shields.io/badge/postgresql-%234169E1?style=for-the-badge&logo=postgresql&labelColor=black)

	 **Java**: Primary programming language.  
	 **Spring Boot**: Simplifies the development of new Spring applications through various helpful configurations.  
	 **PostgreSQL**: Open source relational database, used for all data storage needs.  
 
- **API Testing:**   
 ![Static Badge](https://img.shields.io/badge/postman-%23FF6C37?style=for-the-badge&logo=postman&labelColor=black)

  **Postman**: Used to test and document the RESTful API endpoints provided by the application.

## Development Environment Setup:
**System Specifications**
-   **Machine:** MacBook Air M1
-   **Operating System:** macOS
- **IDE Used:** IntelliJ Community Edition

**Setting Up Spring Boot:**
-   Spring Boot setup involved initializing a new Spring Boot project via the Spring Initializr, available at [https://start.spring.io/](https://start.spring.io/). The project was configured as follows:
<img width="468" alt="image" src="https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/3ab48cd3-abae-494d-90c2-2435a60ab9b6">

**Configuring PostgreSQL on macOS:**

-   PostgreSQL was installed using [postgres.app](https://postgres.app/).
-   Commands Executed:
	```bash
	sudo mkdir -p /etc/paths.d && 
	echo /Applications/Postgres.app/Contents/Versions/latest/bin | sudo tee /etc/paths.d/postgresapp 
	echo  'export PATH=$PATH:/Applications/Postgres.app/Contents/Versions/latest/bin' >> ~/.zshrc 
	source ~/.zshrc
These commands were used to add PostgreSQL commands to the system path, enabling the use of PostgreSQL's command line tools directly from the terminal. 

**Examples:**
- ```bash
  psql
&emsp;&emsp;![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/cae2140d-10ee-4ad7-ba76-b1dc1e9b034d)
- List the databases
  ```bash
  \l
&emsp;![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/2eb5e03d-22dd-470c-b9b7-63d985ca37a5)
- Connect to medlejstore (the database used for this project)
  ```bash
  \c medlejstore
&emsp;&emsp;![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/20e5581e-39c0-4919-bebe-4427ad1cb5fa)
- List the relations
  ```bash
  \d
&emsp;&emsp;![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/df8da8f9-b50a-420e-950d-d572b9e5eac0)
- Write SQL queries
  ```bash
  SELECT * FROM product;
&emsp;&emsp;![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/a67827f7-56af-4b20-b92c-42eae5604a7a)




## Getting Started
To get a local copy up and running follow these simple steps:

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MedlejC/Spring-Boot-Demo.git
2. **Navigate into the project directory**
	```bash
	cd Spring-Boot-Demo
3. **Install PostgreSQL and set up the database:**
	Follow the instructions [here](https://postgresapp.com/) to download and install PostgreSQL. Set up the
	database with: 
	```bash
	CREATE DATABASE medlejstore;
	GRANT ALL PRIVILEGES ON DATABASE “medlejstore” TO {yourusername};
	GRANT ALL PRIVILEGES ON DATABASE “medlejstore” TO postgres;
4. **Run the application:**
	Use your favorite IDE to run the application, or execute:
	```bash
	./mvnw spring-boot:run
 
## Usage
**Interacting with the Application via API Endpoints**

The application offers RESTful API endpoints grouped by functionality for managing products, clients, and sales. Below is an overview of the endpoints available at `localhost:8080`, including examples of how to interact with each endpoint:

**Products:**

-   **Create a Product:** `POST /api/product`
     Adds a new product.
-   **Retrieve All Products:** `GET /api/product`
    Lists all products.
-   **Update a Product:** `PUT /api/product/{id}`
    Modifies an existing product by ID.
-   **Delete a Product:** `DELETE /api/product/{id}`
    Removes a product by ID.
    
**Clients:**

-   **Create a Client:** `POST /api/client`
	Registers a new client.
-   **Retrieve All Clients:** `GET /api/client`
    Lists all clients.
-   **Update a Client:** `PUT /api/client/{id}`
    Updates client details by ID.
-   **Delete a Client:** `DELETE /api/client/{id}`
    Deletes a client by ID.

**Sales:**

-   **Create a Sale:** `POST /api/sale`
    Initiates a new sale.
-   **Retrieve All Sales:** `GET /api/sale`
    Retrieves all sales.
-   **Update a Sale:** `PUT /api/sale/{id}`
    Modifies details of an existing sale.
-   **Delete a Sale:** `DELETE /api/sale/{id}`
    Removes a sale by ID.

**Example Usage with Postman:**
1.  Creating a Product
![image](https://github.com/MedlejC/Spring-Boot-Demo/assets/127014173/244d842a-0240-4cc2-b130-90f4cfeb43f3)
Changes are immediatly reflected on the endpoints.

## Screenshots and Demos



  

## Challenges and Accomplishments

## Future Work
