# Online Store-A Java MVC Application 

# Introduction:

JavaRMIOnlineStore builds upon the foundational work done in JavaRMIBasicOnlineStore for the Java RMI-based online store. In this iteration, the focus shifts to implementing Application Control Patterns, including the Model-View-Control architecture, the Front Controller pattern, and the Authorization pattern. Additionally, the assignment requires the integration of Command and Factory/Abstract Factory design patterns, along with the Template design pattern.

## Overview:

The enhanced online store system will feature improved control mechanisms, allowing for better management of user interactions and authorization processes. By implementing Application Control Patterns and integrating various design patterns, the system aims to enhance functionality, maintainability, and reliability.

# Components:

The components of this project include:

- Server-side implementation for managing business logic and data operations.
- Client-side applications for customers and administrators.
- Classes representing items, users, and other entities.
- Application Control Patterns, including the Model-View-Control architecture and the Front Controller pattern.
- Authorization mechanisms to control access for administrators and customers.
- Command and Factory/Abstract Factory design patterns for efficient handling of user requests and object creation.
- Template design pattern for defining the structure of authorization processes.

## Usage

1. Clone the repository.
2. Compile the source code using the provided MAKEFILE.
3. Run the server-side application.
4. Launch the client-side applications for customers and administrators.
5. Follow the prompts to browse, manage, and purchase items from the online store.

## How to RUN
- Make sure you have installed Java before running the code

Server

- Run the program using the "make" command.
- The port number used is 2233
- Later use the command "make server" command.
- To log in as a customer, please use the values: 
	username: customer1, Password: Pass@1234
        Username: customer2, Password: Pass@9090
        Username: customer3, Password: Pass@8080
-To log in as an admin, please use the values:
	username: admin1, password: Pass@1234
        username: admin2 password: Pass@9090
        username: admin3 password: Pass@8080

The client connection must be established once the server connection is established.

Client

- Switch to another terminal for the Client
- Connect with the server using the "make clientPerson" command later.

#Make sure to run the code on WinSCP with the "Test" file

# Observations and Results
- They are mentioned in the Report.pdf and screenshots.docx 
- Also a JAR file is updated which allows easy execution of the code.
