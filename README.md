# MvvmKotlinJetpackCompose

## Why we need an architecture even when you can make an app without it?
- let's say you created a project without any architecture simply doing all the things in activity class. ie calling api, database interaction , parsing logic etc 
  even though you have created sepearate classes for those, your Activity class is handling multiple things , that makes it god object, doing all the things alone
  now your app is ready and user are using it , now there is a change in your requirement , for example UI changes ,
  
  Now your activity is so tightly coupled to other functionality like api calls and database interaction , that changing your UI will take more time , also we have a risk of         introducing new bugs along the way if we did something wrong. means changing activity UI unnecessarily affecting other functionalities, managing code base is a head ache also 
  because our app is already live client will want it to get done quickly 
