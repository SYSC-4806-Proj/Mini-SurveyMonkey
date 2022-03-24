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

 <hr style="border-top: dotted 2px;" />

### Milestone 3 (Deadline: April 8th, 2022)


 <hr>

## Future Plan
 <ul>
 <li>Complete the templates and the functionality</li>
 <li>Add user authentication process (login, sign up, logout)</li>
 <li>Add ability to delete the surveys.</li>
 <li>Add rendering effects on each template, includes applying CSS and JavaScript to the templates.</li>
 </ul>

 <hr>

## Contributing
Ruixuan Ni
1. Developed the whole project structure
2. Home page : sign up and login. After the user is logged in, the users would be lead to the operation page for further operation.
3. Develop the user login management system

TianTian Lin
1. Create Function: create questionnaire after login
2. Create Function: add three optional questions type buttons. Allow surveyor to create their own questions, have the minimum and maximum for range questions and options for selection questions.
3. Create Function: after create, return to the display page with the generated questionnaire id.
4. Save the three type of questions in each questionnaire into the database.

Jiawei Ma
1. Search Function: one user ID only can fill the questionnaire once,
2. Search Function: after submit the questionnaire, return to user page, user can select the next step.
3. Create model objects under Entity folder, and add JUnit tests for models.
4. Generate database based on the models.
5. Deploy the database to Heroku Postgres, and deploy the system to CircleCI and Heroku.

ShiZhong Shang
1. Create Display page and function: surveyors can see the questionnaires created by themselves
2. Close Questionnaire Function: Surveyors can close the questionnaire in the Display page at any time.
3. Redirect to the result page: once the questionnaire is closed, the display result button would be available. And the result button would redirect the users to the result page.

Jiatong Han
1. Generate the result: the function need the surveyor's id in advance.
2. Create Result Function: Generate results of the questions' answers from database once the questionnaire is closed. The results are ordered by the question id.
3. Find the API to convert the results of selection questions and range questions to diagram and pie chart separately.

## Diagrams
### Database Schema Diagram
![schema](https://user-images.githubusercontent.com/55708400/158025998-f0325043-ec94-4f13-8496-68bc3f7acdd2.png)
