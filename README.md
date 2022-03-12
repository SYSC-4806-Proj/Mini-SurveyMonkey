# Mini-SurveyMonkey

## Content
  * [Background](#background)
  * [Install](#install)
  * [Usage](#usage)
  * [Current State](#current-state)
  * [Future Plan](#future-plan)
  * [Contributing](#contributing)
 
 ## Background
 This project is a mini survey monkey application. User should be able to create questionnaires and allow others to fill it. The surveyor can close his/her questionnaire at any time. The results of this survey would be shown as automatically generated graphs.
 
 A surveyor can create questionnaire with 3 types of questions. Questions can be open-ended(text), choice question, or asking for a number within a range.
 
 ## Install
   clone the project to your local directory
   ```sh
   git clone https://github.com/SYSC-4806-Proj/Mini-SurveyMonkey.git
   ```
 ## Usage
  1. open the cloned project folder in IntelliJ, clicking "Open", and select the project folder.
  2. When the project is open, the workspace should using Maven and Java 8. IntelliJ will automatically help uou to select your sdk and to import Maven.
  3. Find the Maven tab on the right, click on SYSC4806->Lifecycle->Package. This will compile a build that is able to run.
  4. Now we can run our project by clicking Run->main
 
 ## Current State
 For now, the questionnaire can be collected from back end, user are able to create the questionnaires and allow users to fill it, there are three kinds of questions, they are selection questions, choose the number with a range, and open-ending text questions.
 The surveyor can close the questionnaire anytime, and the results for these questionnaires can be displayed.
 There is a Github repo, and the app can running on Heroku.

 ## Future Plan
 Add user authentication process (login, sign up, logout)
 Add dropdown questions type to allow user to set survey questions with a list of options.
 Allow user to delete the surveys.

 ## Contributing
Nikki
1. basic structure build (raw create function implementation)

TianTian Lin
1. Create Function: create questionnaire after login
2. Create Function: add three optional questions type buttons 
3. Crete Function: after create, return to id, from database to front-end

Mario
1. Search Function: one user ID only can fill the questionnaire once,
2. Search Function: after submit the questionnaire, return to user page, user can select the next step.

ShiZhong Shang
1. Create Display Function: user can see the questionnaires created by himself, user can close the questionnaire and generate the result, user need the surveyor id in advance.

Jiatong Han
1. Create Result Function: Generate results according to the results stored in the database, in order of the title number.
