# Java JDBC Demo using DAO

This project demonstrates the use of JDBC (Java Database Connectivity) to interact with a database, following the DAO (Data Access Object) pattern. The main goal is to illustrate best practices for data access in Java.

## Implemented Features

### 1. Class Creation

- **`Seller`:** Represents specific information about sellers.
- **`Department`:** Represents specific information about departments.

### 2. Implementation of `SellerDaoJDBC`

- **`findById`:** Searches for sellers by ID.
- **`instantiateSeller` and `instantiateDepartment`:** Private methods for creating instances of sellers and departments.
- **`findByDepartment`:** Searches for sellers by department.
- **`findAll`:** Retrieves all sellers.
- **`insert`:** Inserts new sellers.
- **`update`:** Updates seller information.
- **`delete`:** Deletes sellers.

### 3. Implementation of `DepartmentDaoJDBC`

- **`findById`:** Searches for departments by ID.
- **`instantiateSeller` and `instantiateDepartment`:** Private methods for creating instances of sellers and departments.
- **`findAll`:** Retrieves all departments.
- **`insert`:** Inserts new departments.
- **`update`:** Updates department information.
- **`delete`:** Deletes departments.

## How to Run the Project

1. Clone the repository to your local machine.
2. Ensure you have a configured database and adjust connection information in the code if necessary.
3. Execute the main program, for example, `Program2.java`.
4. Follow the instructions in the console to interact with the system.
