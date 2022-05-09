# Design Document

## 1 Design Considerations

### 1.1 Assumptions

The application would be dependent on the database to pull information about each store and their inventory. The API level should be at least 21 to ensure it runs on a variety of android devices. An issue that can occur is properly pulling information from the database. Log in authentication might be another issue for employees and customers.

### 1.2 Constraints

Resolution of different devices might affect the users UI and viewability. Older devices might run into issues running this application. Internet connectivity would have to be required to access the database.

### 1.3 System Environment

The application must run on a smart device running android OS at API level 21.



## 2 Architectural Design

### 2.1 Component Diagram

![img](https://cdn.discordapp.com/attachments/960582007258230824/960587991598895104/Component_Diagram.PNG)

The customer and employee have access to a home interface. This is where the employee can access the log in interface where they can enter their username and password. Once logged in, the employees can access more features of the inventory management interface such as edit product, add product and delete product. The customer cannot access these features and are limited by the inventory manager to only search products. From there any changes an employee makes gets updated to the database.

### 2.2 Deployment Diagram

![img](https://cdn.discordapp.com/attachments/960582007258230824/960587991376617502/Deployment_Diagram.PNG)

The smart device contains the FlooringIconManager which has a connection to the application server where you can log in, access the inventory manager,  search product, add  product, delete product and edit products based on your privilege. The application then uses the JDBC Library to connect to the stores inventory, it also holds the employee's account details.

## 3 Low-Level Design

### 3.1 Class Diagram

![img](https://cdn.discordapp.com/attachments/960582007258230824/960705525031522304/umlclass.png)



### 3.2 Other Diagrams



![img](https://cdn.discordapp.com/attachments/960582007258230824/960704970515185724/statediagram.png)

The state diagram as shown above has the user in the home state. If the user has appropriate credentials they can log in and be in the inventory manager with extended features state. There they can add product, edit product, delete product and search product indefinitely. They can then log out and go back to the home state. If a user is not an employee and does not have the appropriate credentials, they can still continue as guest and be inside the inventory manager with limited features. Here they can search products indefinitely or go back to the home state.



![img](https://cdn.discordapp.com/attachments/960582007258230824/960966629016100874/unknown.png)

Here is the sequence diagram for the customer. The customer will click guest, search product in the inventory manager which looks in the database and when the product is found it will be returned to the customer.

![img](https://cdn.discordapp.com/attachments/960582007258230824/960969071283818566/unknown.png)

The employees sequence diagram acts in a similar manner but with extra features and the ability to log in to access those features. When the employee clicks login and enters the inventory manager, they can search, delete, edit or add a product. The database then gets updated and the employee has the option to log out when they are done.



![img](https://cdn.discordapp.com/attachments/960582007258230824/970104184684806154/db_updated.png)

Above are the tables that are currently in the database and has been updated. An employee table for storing log in credentials and a stock table to show all the inventory across all stores.



## 4 User Interface Design
![img](https://media.discordapp.net/attachments/956602047074029621/970423499573301350/UIDesign.png?width=933&height=584)

The top row of the application mock ups are all activities that any user regardless of permission can access. A customer or employee will be able to search by either store or the product itself. When an employee logs in they have 1 out of the 4 options to choose from which will take them to the appropriate activity when pressed. It wont be possible for a customer to access an employee screen without credentials so editing the inventory will only be available to the employee.
