
# MvvmKotlinJetpackCompose


<img src="https://user-images.githubusercontent.com/24357731/143909939-01d0fa4c-47f6-467f-a927-d3fa6e5c2d51.png" alt="alt text" width="300" height="600">   &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143911861-996649a9-3df4-44ff-b000-7300e5085950.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912106-8a5fb378-1f13-4f44-8596-e9740fd460a0.png" alt="alt text" width="300" height="600"> 

<img src="https://user-images.githubusercontent.com/24357731/143912270-ae660f16-0c7f-4d67-8aae-ae34421bf67f.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912389-6ab7063a-fd82-4567-a395-1a3f372ba3f1.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912529-fda38601-0925-4270-995b-f14c6e36d37d.png" alt="alt text" width="300" height="600"> 



## Why do we need an architecture even when you can make an app without it?
 
let's say you created a project without any architecture simply doing all the things in the activity class. ie calling API, database interaction, parsing logic etc 
even though you have created separate classes for those, your Activity class is handling multiple things, which makes it god object, doing all the things alone
now your app is published and users are using it, Suddenly there is a change in your requirement, for example, UI changes,
  
Now your activity is so tightly coupled to other functionality like parsing logic, API calls and database interaction, that changing your UI will take more time, also we have a 
risk of introducing new bugs along the way if we did something wrong, it becomes hard to maintain difficult to track bugs and we can't reuse the activity, also changing activity UI unnecessarily affecting other functionalities, managing codebase becomes a headache.

Architecture Solves this Problem
 

## Architectue

An Architecture Makes your project Robust, Extensible, Maintainable, Scalable, Reusable and Testable(Unit testing).
it's an organizational structure of a system including its decomposition(breaking down a complex problem or system into smaller parts) into parts, their connectivity, interaction mechanisms, and the guiding principles and decisions that you use in the design of a system, architecture reduces chaos


it becomes easy to 
add features,
make changes,
write Unit test cases,
maintain and reuse 

## How?

Because it follows Some Principles known as SOLID. 
A design principle is a technique that can be applied to designing or writing code to make that code more maintainable, flexible and extensible

SOLID is an acronym for.

## S - Single Responsibility Principle(SRP)
A class should have only one reason to change the easiest way to make your software resilient to change is to make each class has only one reason to change.
 
How do you know if your class has only one responsibility to change?
compare Class name with methods(behaviour) look through the methods of your classes do they all relate to the name of your class, if you have a method that looks out of place it might belong to another class

for example -
A car class can have a start method, it can start, it can break, can it change its tires no .. that is the job of another object
In android, we keep our Activity methods related to Ui nothing else it has only one responsibility which is rendering and maintaining the state of the UI 
Another Example can be AppPreferenceHelper class which has only one responsibility interacting with Shared Preferences

## O - Open close Principle(OCP)
A class should be open for extension but closed for modification, OCP lets you extend your working code, without changing that code, lets take an example of BaseActivity
which has ShowLoading() Method that shows a circular progress bar which is used by all other SubActivities, Now in one Activity You don't want to show circular progress, you would
like to show a shimmer so for that we can easily override the method from our BaseActivity and provide specific implementation to our subActivity to show shimmer, OCP at its work, we extended functionality without modifying our BaseClass 

Inheritance is a way to apply the open-closed principle the other way is defining private methods which closed for modification and using it in public methods to extend their behaviour 

## L - Liskov substituion Principle(LSP)
The LSP is all about well-designed inheritance. when you inherit from a base class, you must be able to substitute your subclass for that base class without things going terribly wrong, otherwise, you have used inheritance incorrectly, which means you with LSP you can store Child Object in Parent Class Variable (Animal animal=new Tiger() ) 

Lsp reveals hidden inheritance problem
if subtypes cannot substitute for base type then your inheritance has problems, inheritance (and the LSP) indicate that any method on the Base class should be able to be used on the subclass

it's hard to understand the code that misuses inheritance, if you have used inheritance badly, then you are going to end up with a lot of methods that u dont want because they probably do not make sense to your subclasses following LSP will solve the problem

## I - Interface Segregation Principle(ISP)
A class should not implement an interface that it does not use, A class should not override methods that do nothing or are empty. if that is the case consider creating another interface for that empty methods and using them where it's needed, so the class which does not need to have methods, it does not use.

## D - Dependency Inversion Principle(DIP)
Classes should depend on abstraction not on concreate types, when interacting in between 2 classes or modules (higher-level module and lower-level modules) you should depend on an interface not on a concrete class, with this you reduced the dependencies between your modules or classes and it becomes loosely coupled, loosely couple classes can easily take out from one project and can use in another project without unnecessary changes.

building an app that works but is poorly designed satisfies the customer but will leave you with pain, suffering, and lots of late-night fixing problems.

There are many other Design principles as well
1)favour coding to the interface, not the implementation
2)Do not repeat yourself (DRY)
3)Principle of least knowledge etc

if you want to learn more about these topics i would highly recommend reading these books

1)https://www.amazon.in/Head-First-Object-Oriented-Analysis-Design/dp/8184042213
2)https://www.amazon.in/Head-First-Design-Patterns-Object-Oriented/dp/9385889753/ref=pd_bxgy_img_1/257-3183565-0278961?pd_rd_w=3Nel7&pf_rd_p=e23e9300-1f33-4d32-86fc-f4fcff7c4b49&pf_rd_r=K311FD7EF9DDBZ8NRVGM&pd_rd_r=77d901f5-c7b2-4c83-98a5-d6341d45d494&pd_rd_wg=32sm9&pd_rd_i=9385889753&psc=1

## Jetpack Compose
It's a declarative style API, which means instead of creating the layout and initializing it you tell compose how your UI should look like and compose compiler will create UI for you.
which recompose itself when data changes mean you dont have to explicitly change the UI state like we used to do with the Android view System(XML)

1)state hoisting is a pattern of moving state up to make a component stateless 
i) it's easier to test 
ii)tend to have fewer bugs, open up more opportunities to reuse 

2)Avoid passing ViewModel reference to composing instead pass raw data, helps to render preview(spend a lot of time on this)

3)Pass Child composable in Parent Composable method as the parameter this way you can make the composable stateless

## Unit test
Why do we need a Unit test if we can test it manually on the device, Unit testing has several benefits

1)it enforces you to have a good app architecture without good architecture you can't Unit test
2)let say you have added functionality to a class that already has its unit test in place, now you will verify by running all existing Tests so to check that you did not break anything else when adding new Functionality, it gives you confidence that your code is working.
3)There are  2 cases where a Unit test Fails
i)when it does not meet the requirement (when we write the test the first time)
ii)when the requirement is changed (then you have to change test cases as well)

Don't pass context in viewmodel it makes it easier to test



