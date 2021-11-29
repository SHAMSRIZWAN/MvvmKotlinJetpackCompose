
# MvvmKotlinJetpackCompose


<img src="https://user-images.githubusercontent.com/24357731/143909939-01d0fa4c-47f6-467f-a927-d3fa6e5c2d51.png" alt="alt text" width="300" height="600">   &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143911861-996649a9-3df4-44ff-b000-7300e5085950.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912106-8a5fb378-1f13-4f44-8596-e9740fd460a0.png" alt="alt text" width="300" height="600"> 

<img src="https://user-images.githubusercontent.com/24357731/143912270-ae660f16-0c7f-4d67-8aae-ae34421bf67f.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912389-6ab7063a-fd82-4567-a395-1a3f372ba3f1.png" alt="alt text" width="300" height="600"> &nbsp;&nbsp;&nbsp;&nbsp;    <img src="https://user-images.githubusercontent.com/24357731/143912529-fda38601-0925-4270-995b-f14c6e36d37d.png" alt="alt text" width="300" height="600"> 



## Why we need an architecture even when you can make an app without it?
 
let's say you created a project without any architecture simply doing all the things in activity class. ie calling api, database interaction , parsing logic etc 
even though you have created sepearate classes for those, your Activity class is handling multiple things , that makes it god object, doing all the things alone
now your app is published and user are using it , now there is a change in your requirement , for example UI changes ,
  
Now your activity is so tightly coupled to other functionality like parsing logic, api calls and database interaction , that changing your UI will take more time ,also we have a 
risk of introducing new bugs along the way if we did something wrong, it becomes hard to maintain and we can't reuse the activity also changing activity UI unnecessarily affecting  other functionalities, managing code base is a head ache. Architecture Solves this Problem
 

## Architectue

An Architecture Makes your project Robust, Extendible, Maintainable, Scalable, Reusable and Testable(Unit testing)

it becomes easy to 
add features,
make changes,
write Unit test cases,
maintain and reuse 

## How?

Because it follows Some Principles known as SOLID. its an acronym for..

## S - Single class responsibilty 
A class should have only one reason to change 



 
