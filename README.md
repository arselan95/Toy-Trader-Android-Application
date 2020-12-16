# ToyTraderAndroidApplication

# High level architecture

![Screenshot](1.png =100x)

<img src="1.png" alt="drawing" style="width:100px;"/>


# Database architecture

![Screenshot](2.png)

```
● Firebase - Document based NoSQL DB
● Firebase Authentication - Email - Password
● Collections
○ Toys
○ Trades
○ Users
○ Toy-Frauds
```


# Functional Requirements

```
● User can Register
● User can Login
● User can change personal data
● User can view Categories
● User can view Toys
● User can Buy/Rent toys
● User can report Frauds
● User can return a rented toy
● Admin can view users
● Admin can view toys and issues with them
```

# Register

```
● Enter All user details
● Details uploaded to Firebase
● User registered using Firebase Authentication
```
![Screenshot](3.png)

# Login

```
● Enter email and password
● User Authenticated with Firebase Auth
● Securely log into the Application
```
![Screenshot](4.png)

# Home Screen

```
● Home screen for the application
● Select from multiple categories
```
![Screenshot](5.png)

# Upload toy

```
● User can opt to upload their own toys
● Add a few details with the image and you’re good to go
```
![Screenshot](6.png)

# Categories

```
● User can select any category
● Toys related to that category will be shown here
● Images are downloaded from firebase storage
```
![Screenshot](7.png)

# Toy Description

```
● Selecting any toy opens up detail view
● All the details related to that Toy are shown here
● User can
○ Add the toy to cart
○ Report the toy
○ View Toy location on Map
```
![Screenshot](8.png)

# Map

```
● Map view to show the location on the map
```
![Screenshot](9.png)

# Cart

```
● After the Item is added to cart user can
○ Buy the item
○ Rent the item
○ Remove the item
```
![Screenshot](10.png)

# Orders

```
● Orders page to provide user history
● User can view all the items that he has rented or bought
```
![Screenshot](11.png)

# Profile

```
● Profile page for the logged in user
● View all user details
● Can edit details
● Can change Password
```
![Screenshot](12.png)

# Admin

```
● Admin portal is used to manage
○ Toys
○ Users
○ Issues reported by Users
```
![Screenshot](13.png)

# Admin -

# Manage Users

```
● Admin Can Block the user
● Admin can Delete the user
```
![Screenshot](14.png)

![Screenshot](15.png)

# Admin -

# Manage Toys

```
● Admin can view list of toys
● Admin can delete the toy
```
![Screenshot](16.png)

# Admin -

# Manage Issues

```
● Admin can view the reported issues
```
![Screenshot](17.png)

![Screenshot](18.png)

