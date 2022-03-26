# Mini-SurveyMonkey

<!-- PROJECT SHIELDS -->
![GitHub contributors](https://img.shields.io/github/contributors/SYSC-4806-Proj/Mini-SurveyMonkey?color=%232596be&style=flat-square)
![GitHub issues](https://img.shields.io/github/issues/SYSC-4806-Proj/Mini-SurveyMonkey?color=%23e28743&style=flat-square)

## Content
* [Background](#background)
* [Install](#install)
* [Usage](#usage)
* [Milestones](#Milestones)
* [Future Plan](#future-plan)
* [Contributing](#contributing)
* [Diagrams](#Diagrams)

 <hr>

## Background
This project is a mini survey monkey application. User should be able to create questionnaires and allow others to fill it. The surveyor can close his/her questionnaire at any time. The results of this survey would be shown as automatically generated graphs.

A surveyor can create questionnaire with 3 types of questions. Questions can be open-ended(text), choice question, or asking for a number within a range.

 <hr> 

## Install
clone the project to your local directory
   ```sh
   git clone https://github.com/SYSC-4806-Proj/Mini-SurveyMonkey.git
   ```
   
 <hr>

## Usage
### Run Locally:
1. open the cloned project folder in IntelliJ, clicking "Open", and select the project folder.
2. When the project is open, the workspace should using Maven and Java 8. IntelliJ will automatically help uou to select your sdk and to import Maven.
3. Find the Maven tab on the right, click on SYSC4806->Lifecycle->Package. This will compile a build that is able to run.
4. Now we can run our project by clicking Run->main
### Run Remotely:
Click this URL:
 
  [https://mini-survey-monkey18.herokuapp.com/](https://mini-survey-monkey18.herokuapp.com/)

 
## Milestones

### Milestone 1 (Deadline: March 11th, 2022)

For Milestone 1, our team designed the whole structure of the Mini-SurveyMonkey. According to our discussion, the program 
has been break into four parts: front-end (templates), Controller, Backend(Entity models) and Database. <br>
<br>Entity models contains class: <br>OpenEnd, Questionnaire, Range, Selection and User. <br>(Junit test for Entity models are in the test folder)<br>
<br>For now the fount-end can support the user to create questionnaires and choose which kind of questions they want to have in it (OpenEnd, Range, and Selection)
<br><br>Surveyor can can edit the problems for all questions, the minimum and maximum boundaries for range questions, and the options for selection questions.
<br>The created questions can be saved into database (the Junit test has been made to test create & save questions and write answers fot the questions)
<br><br>All the Java code has been saved on the GitHub (master branch), all database is uploaded to the Heroku and the program is connected to CircleCI  for automatically continuous integration

### Milestone 2 (Deadline: March 25th, 2022)

For Milestone 2, our team developed most of the basic functionalities and features of the Mini-SurveyMonkey. And now, the system has the following features:
<ul>
<li>Users can sign up and login into our system now. And the user management system would ensure the security of each user.</li>
<li>Users can create a questionnaire after login. They can also add three type of questions as many as they need.</li>
<li>Users can explore all questionnaires which are not closed and not created by the current user</li>
<li>Users can search and complete a questionnaire by entering questionnaire id.</li>
<li>Users can check all questionnaires created by themselves and close any of their questionnaire.</li>
<li>After the user closed one of their questionnaire, they can see the results of the questionnaire.</li>
<li>Currently, the results of each survey question are still shown in text format.</li>
<li>Developed css for some pages.</li>
</ul>

### Milestone 3 (Deadline: April 8th, 2022)


 <hr>

## Future Plan
 <ul>
 <li>Generate histograms and pie charts for selection and range questions separately.</li>
 <li>Add more CSS styles to HTML files</li>
 <li>Optimize the HTML redirects</li>
 </ul>

 <hr>

## Contributing
Ruixuan Ni
1. Developed the whole project structure [DONE]
2. Home page : sign up and login. After the user is logged in, the users would be lead to the operation page for further operation. [DONE]
3. Develop the user login management system [DONE]

TianTian Lin
1. Create Function: create questionnaire after login [DONE]
2. Create Function: add three optional questions type buttons. Allow surveyor to create their own questions, have the minimum and maximum for range questions and options for selection questions. [DONE]
3. Create Function: after create, return to home page for further operation. [DONE]
4. Save the three type of questions in each questionnaire into the database. [DONE]

Jiawei Ma
1. Search Function: one user ID only can fill the questionnaire once. [doing]
2. Search Function: after submit the questionnaire, return to home page, user can select the next step. [DONE]
3. Create model objects under Entity folder, and add JUnit tests for models. [DONE]
4. Generate database based on the models. [Re-modified and DONE]
5. Deploy the database to Heroku Postgres, and deploy the system to CircleCI and Heroku.[Re-modified and DONE]

ShiZhong Shang
1. Create Display page and function: surveyors can see the questionnaires created by themselves [DONE]
2. Close Questionnaire Function: Surveyors can close the questionnaire in the Display page at any time. [DONE]
3. Redirect to the result page: once the questionnaire is closed, the display result button would be available. And the result button would redirect the users to the result page. [DONE]

Jiatong Han
1. Generate the result: the function need the question's id in advance. [DONE]
2. Create Result Function: Generate results of the questions' answers from database once the questionnaire is closed. The results are ordered by the question id. [DONE]
3. Find the API to convert the results of selection questions and range questions to diagram and pie chart separately. [Incomplete]

## Diagrams
### Database Schema Diagram
![image](https://user-images.githubusercontent.com/55708400/159991499-85017264-2c00-4b0e-8c0b-ef5863b83d5d.png)
