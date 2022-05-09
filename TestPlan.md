# TestPlan

**Author**: Joanne Pyo

## 1 Testing Strategy

### 1.1 Overall strategy

The testing process will follow Unit Testing, Integration Testing, System Testing, and Acceptance Testing. The overall strategies that we would follow for each of our four testing phases are listed below:

#### Unit Testing: 
We will test each piece of the program by writing manual test cases.

```
Unit testing:
    - add a new product (checks if a new product is added)
    - add a new product type (checks if a new product type is added)
    - edit the product (checks if the product is changed)
    - edit the product quantity (checks if the product quantity is changed)
    - delete a product (checks if the product is deleted)
    - search specific product (checks if the specific product is found)
```

#### Integration testing:
we will test the individual units as a group. 

```
Integration testing:
    - create a new product
    - edit the type for the product
    - edit the quantity for the product
    - delete the product
    - Search the product 
```

#### System testing:
We will test and run a complete application to see if it fits all the requirements and runs as expected.

```
System testing:
    - run the application multiple times with different test cases 
```

#### Acceptance Testing:
We will test a complete application system to see if the application is deliverable and acceptable.

```
Acceptance Testing:
    - run the FlooringIconManager application on the Android Emulator or on a smartphone and test to see if it works as expected.
```

### 1.2 Test Selection
#### Black box: 
System Testing will be performed using software specifications by running all the components as a group and testing each of them to ensure the application works as expected.

```
    - create a new product
    - edit the type for the product
    - edit the quantity for the product
    - delete the product
    - Search the product 
```
	
#### White box: 
Unit testing and integration testing will be performed using code. Unit testing is used to test each function individually.
```
    - add product
    - edit product
    - delete product
    - search product
``` 

Integrated testing is used to test related parts of the application together as a group.  

```
    - test the function of the application as a group
```

### 1.3 Adequacy Criterion

Adequacy Criterion is a set of test obligations to check that all the tests are passed correctly. 
Unit Testing: 
```
Statement coverage:
    - execute all statements at least once by giving notation
    - the result is passed or failed
```
System Testing:
```
Decision coverage:
    - execute each decision using for, while, do, or switch
    - the result is true or false
```

### 1.4 Bug Tracking

**Unit testing strategy:** If there is an error when we test each case, we know which part of the code should be fixed. 

**Integration testing strategy:** If there is an error when we connect related components then the application will pass Unit testing, but it will not pass the integration testing.

**System testing strategy:** If unit testing and integrated testing pass, and we still have an error after running the application then we know there is an error with the system.

### 1.5 Technology

We will use JUnit for testing our application. 

## 2 Test Cases

There has two cases. One for user and one for employee.

User's Case:

| Test case ID |                Purpose                |                                                                                               Steps                                                                                                | Pre condition | Input/Search data | Expected result | Actual result | Status |
|:------------ |:-------------------------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:| ------------- | ----------------- | --------------- | ------------- | ------ |
| 1            | check if a product is found by search |                          1. Guest presses the SEARCH button.2. Type it prodcut name. 3. Guest presses SEARCH button. 4. Data is sent from the application to the database.                          | Main Menu     | Tile              | found product   | found product | Pass   |
| 2            | check if a product is found by search |       1. Guest presses the SEARCH button. 2. Click All Store Locations 3. Choose one of Store Location. 4. Guest presses SEARCH button. 5. Data is sent from the application to the database.       | Main Menu     | 1001              | found product   | found produnt | Pass   |
| 3            | check if a product is found by search | 1. Guest presses the SEARCH button. 2. Click All Floors 3. Choose one of floor type. 4. Choose one of the material. 5. Guest presses SEARCH button. 6. Data is sent from the application to the database. | Main Menu     | Tile              | found product   | found produnt | Pass   |



Employee's Case:

| Test case ID | Purpose                                | Steps                                                                                                                                                                                                                                                                                    | Pre condition | Input data                                                                                                                                                                                                             | Expected result        | Actural result         | status |
| ------------ |:-------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |:------------- |:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------- | ---------------------- |:------ |
| 1            | Checks if a new product is added       | 1. Employee presses Add PRODUCT button. 2. Employee inputs new product informations. 3. Employee presses ADD PRODUCT button. 4. Data is sent from the application to the database.                                                                                                       | Login         | Prdouct ID: 103, Add Store: 1003, Product Name: Duravana Hybrid Resilient Flooring, Brand: Duravana, Color: Blonde, Size: 23.92, Qty: 57, Price: 81.09, Add Floor: Wood, Add Wood Types: Hybrid, Add Wood Species: Oak | new product is created | new product is created | Pass   |
| 2            | Checks if a new product is added       | 1. Employee presses Add PRODUCT button. 2. Employee inputs new product informations. 3. Employee presses ADD PRODUCT button. 4. Data is sent from the application to the database.                                                                                                       | Login         | Prdouct ID: 102, Add Store: 1002, Product Name: Bianoco Venato Marble, Brand: Tilefornia, Color: White, Size: 7, Qty: 77, Price: 69.99, Add Floor: Stone, Add stone Materials: Marble.                                 | new product is created | new product is created | Pass   |
| 3            | Checks if the product is edited        | Employee presses the EDIT PRODUCT button. 2. Write down product Name 3. Employee presses EDIT PRODUCT button. 4. Click product to edit 5.Edit information 6. Click UPDATA PRODUCT button 7. Data is sent from the application to the database.                                           | Login         | Product Name: Luminescent Sky Marble Luxury Vinyl Tile -> eidit product Qty: 70 to 65                                                                                                                                  | product is edited      | product is edited      | Pass   |
| 4            | Checks if the product is edited        | 1. Employee presses the EDIT PRODUCT button. 2. Click All Store Location. 3. Choose one of the store location. 4. Employee presses EDIT PRODUCT button. 5. Click product to edit 6.Edit information 7. Click UPDATA PRODUCT button 8. Data is sent from the application to the database. | Login         | Choose store location 1002 -> Click Bianco Venato -> Change Size 7 to 5.                                                                                                                                               | product is edited      | product is edited      | Pass   |
| 5            | Checks if the product is edited        | 1. Employee presses the EDIT PRODUCT button. 2. Click All Floor. 3. Choose one of the floor type. 4. Employee presses EDIT PRODUCT button. 5. Click product to edit 6.Edit information 7. Click UPDATA PRODUCT button 8. Data is sent from the application to the database.              | Login         | Choose floor type" Wood-> Choose Hybrid and Walnut -> Click Duravana -> Change Wood species to Oak.                                                                                                                    | product is edited      | product is edited      | Pass   |
| 6            | Checks if the product is deleted       | 1. Employee presses the DELETE PRODUCT button 2. Write down a Product ID and Store ID 3. Employee presses DELETE PRODUCT button. 4. Data is sent from the application to the database.                                                                                                   | Login         | Product ID: 105, Store ID: 1001                                                                                                                                                                                        | product is deleted     | product is deleted     | Pass   |
| 7            | Checks if a product is found by search | 1. Employee presses the SEARCH PRODUCT button. 2. Type it prodcut name 3. Employee presses SEARCH button. 4. Data is sent from the application to the database.                                                                                                                          | Login         | Graystone Tavertine Porcelain Tile                                                                                                                                                                                     | found product          | found product          | Pass   |
| 8            | Checks if a product is found by search | 1. Employee presses the SEARCH button. 2. Click All Store Locations 3. Choose one of Store Location. 4. Emplotee presses SEARCH button. 5. Data is sent from the application to the database.                                                                                            | Login         | 1001                                                                                                                                                                                                                   | found product          | found product          | Pass   |
| 9            | Checks if a product is found by search | 1. Employee presses the SEARCH button. 2. Click All Floors 3. Choose one of floor type. 4. Choose one of the material. 5. User presses SEARCH button. 6. Data is sent from the application to the database.                                                                              | Login         | Tile, Procelain                                                                                                                                                                                                        | found product          | found product          | Pass   |

Add information:
Emplyee Id and password is admin. 
