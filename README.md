# MOVIE RENTAL SYSTEM PROJECT

A Java console application for managing customers, movies, and rentals with JSON file storage.

---

## OVERVIEW
This project is a **Movie Rental Management System** built using **Object-Oriented Programming (OOP)** principles in Java.  
It allows users to:

- Add new members (Students/External Member) 
- Add movies  
- Start rentals  
- Return rentals  
- Calculate rental fees  
- Save and load data using **Gson JSON serialization** (with LocalDate support)

---

## JSON
Date is read and loaded when starting the application.
When exiting system, data is saved into .json files and into a folder.

Custom `LocalDateAdapter` is used to ensure `LocalDate` is written/read properly.

```java
private static final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .setPrettyPrinting()
        .create();

```

---

## REQUIREMENTS

1. Install Java 8+
2. Add the Gson (2.10.1 or higher) library to your project
3. Execute Main class/drive

---

## NOTICE
* JavaDoc can be found in the code.
* GUI coming soon!
