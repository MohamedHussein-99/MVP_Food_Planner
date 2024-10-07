# MVP Food Planner Application
Creating a Android Java Application to Display Food Recipes and dishes , also a Planner for a week for each day.
Using MVP Architicture , Room for DataBase , Retrofit for Connection


## Technologies and Algorithms:
- ***MVP*** Architicture Design Pattern, to separate Logic part in `Presenter` from the `View` and `Model`.
- Using ***Retrofit*** from Jetpack for Network Connection.
- Using ***Room for Local Database*** to store desired meals into database, to be used in Offline Mode.
- ***Connectivity Manager*** for handling Scenarios if no network connection.

## Features:
- ***Splash Screen*** at getting start with the application
- ***Home Screen*** to display random meals with new ideas every login in the application, and a sample filter from Countries and Cuisines.
- ***Detailes Screen*** Contain details of selected meal: Image , Country , Cuisine, Ingredient , Steps and even a Video for that Meal, with the ability to add this meal to `Favorite` or `Planned` into a desired day.
- ***Favourite Screen*** Saved meal into a database so it can work `Offline` , and `easy access` to that meal.
- ***Planned Screen*** Contain a `Calender`, each day contain a selected meal to do in that day.
- ***Filtered Screen*** Navigate between 3 Filters Categouries with ability to `Search` for any Meal , Country or even Ingredients you have in Kitchen.
- ***Offline Meals*** Ability to Store meals in Favorite or Planned Meals to be displayed if there is no `Network Connectivity`


## Simple Demo for the Application:

https://github.com/user-attachments/assets/38baab67-4cfb-42c4-919d-2bcd36642240

