# Use Case Model

## 1 Use Case Diagram
![image](https://user-images.githubusercontent.com/66324119/161481399-06567f91-1add-4ade-a30d-cbfe1ca09f8a.png)

## 2 Use Case Description
### Requirements:
The Customer and Employee must be able to search the Store's DB through a search functionaliy. They will be able to search with the name, floor, and the type of the product as the search parameters. An examplpe of such is the user searching for the name white and with the floor category as tiles. This will result in white tiles to be shown as a result.

### Pre-Conditions:
- Customer/Employee must have the app installed in order to search the DB
- Employee must login to access and CRUD functionalities.

### Post-Conditions:
- Customer/Employee is able to search for anything in the DB through the app
- Employee is able to Add, Delete, and Edit products within the Stores' DB

### Scenarios:
#### Normal:
- Customer and Employee can search through the store's inventory based on whatever they are looking for. They will use the search bar and search for 2x4 wood in the wood floor is they are looking for lumbar. If they are looking for tiles, they would search in the tiles floor.
- Employee can add, delete, and edit products in the store's inventory after they log in. They can do all this by searching with name of the product, ID, type, color, size, and etc. For example, if the Employee is trying to remove a 2x4 wood with the ID of 475-968. They will enter the ID of the product with the delete function, hence removing the product from the store's inventory

### Alternate:
- Customer or Employee can search with just a general name such as "wood" or "paint". By not adding any other information within the name or adding any floor or category inputs, these general searches will provide a broad result of items that they may not be looking for. Such as "paint" for example, paint can result in paint brushes, paint container, paint removers, paint, paint drip cloth, and many more. Despite it giving results of the Customer/Employee's input, it will not be as efficient in narrowing the results down.

- Employee adding products with the wrong information. When an Employee adds products to the Store's inventory, they have an option of selecting the flooring of the product with many other descriptions of the product. If the Employee is adding a white door into the Store's DB, and he/she inputs it as a black colored door, this will be the wrong information. Even though it's wrong, the system will intake the data and display it into the Store's inventory. 

### Exception:
- Customer or Employee searching for items but inputting completely wrong floors or categories for their search. For example, if the Customer/Employee is trying to find windows and they input in the search floor as Tile and category as Porcelain, no results will show. There is no such windows within Tiles made of Porcelain. This will display no results for the User, as there are no windows within that category.

- Employee deleting items from the store's inventory that does not exist. If a Employee enters a Product ID that is not in the Store's DB, then it will give an error. As the Employee is trying to delete something that never existed in the DB to begin with.
