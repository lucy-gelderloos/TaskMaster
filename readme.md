# TaskMaster

A task management app

## Day 10

- Implemented Amplify Storage
  - Users can add an image to a task
  - The image is saved to Amplify S3
  - The image's S3 key is associated with the task when it is saved to the database

![Add Task page day 10](./screenshots/add-task-day-10.png)
![Add Image page day 10](./screenshots/add-task-day-10.png)

## Day 9

- Implemented authentication with Amplify Cognito
  - Added Log In, Sign Up, and Log Out buttons to main activity
  - Added Log In, Sign Up, and Verification activities
  - New users are added to Cognito user pool

![Main page day 9 (logged out view)](./screenshots/main-day-9-logged-out.png)
![Main page day 9 (logged in view)](./screenshots/main-day-9-logged-in.png)
![Signup page](./screenshots/sign-up-day-9.png)
![Verification page](./screenshots/verification-day-9.png)
![Login page](./screenshots/login-day-9.png)

## Day 8

- Final styling
- All tasks page displays all tasks for user's team
- Prepared for publication
  - .aab generated
  - [link to privacy policy](https://www.termsfeed.com/live/b44d830a-83ce-496f-86b1-bb88a979efc1) (contact email for privacy policy: lucygel@live.com)
  - Play store icon & feature graphic assets created
  - ALl other initial publication steps completed
- Waiting on identity verification to publish app

![Main page day 8](./screenshots/main-day-8.png)
![Settings page day 8](./screenshots/settings-day-8.png)
![Add task page day 8](./screenshots/add-task-day-8.png)
![All tasks page day 8](./screenshots/add-task-day-8.png)
![Task detail page day 8](./screenshots/task-detail-day-8.png)

### Play Store Assets

![App Icon](./screenshots/icon.png)
![Feature Graphic](./screenshots/feature-image.png)

## Day 7

- Added Team model
- User can change their team in the Settings page
- Team can be associated with task on creation
- Only tasks associated with the user's team will display on the main page

![Main page day 7](./screenshots/main-day-7.png)
![Settings page day 7](./screenshots/settings-day-7.png)
![Add task page day 7](./screenshots/add-task-day-7.png)
![Task detail page day 7](./screenshots/task-detail-day-7.png)

## Day 6

- Replaced Room database with AWS Amplify; all functions work
- Tests do not work - they keep crashing the emulator's system. I think it's related to some of the new dependencies.

![Main page day 6](./screenshots/main-day-6.png)
![Add task page day 6](./screenshots/add-task-day-6.png)
![Task detail page day 6](./screenshots/task-detail-day-6.png)

## Day 5

- Styled app
- Added tests for main page UI elements, change username function, and task creation and detail page
- Added state to task detail view

![Main page day 5](./screenshots/main-day-5.png)
![Add task page day 5](./screenshots/add-task-day-5.png)
![Settings page day 5](./screenshots/settings-day-5.png)
![Task detail page day 5](./screenshots/task-detail-day-5.png)


## Day 4

- Implemented Room database to store tasks
- "Add Task" button on main page adds task to database
- Recycler view on main page displays all tasks in database

![Main page day 4](./screenshots/main-day-4.png)
![Task detail page day 4](./screenshots/task-detail-day-4.png)

## Day 3

- Main page uses recycler view to list all tasks on scroll
- Clicking on any task in the recycler view leads to the task detail page with that task's details filled in

![Main page day 3](./screenshots/main-day-3.png)
![Task detail page day 3](./screenshots/task-detail-day-3.png)

## Day 2

- Main page title updates dynamically with new username
- Create task detail page with dynamic title and placeholder description
- Create settings page where user can change username

![Main page day 2](./screenshots/main-day-2.png)
![Task detail page day 2](./screenshots/task-detail-day-2.png)
![Settings page day 2](./screenshots/settings-day-2.png)

## Day 1

Create main page with add task button that leads to add task page and all tasks button that leads to all tasks page.

![Main page day 1](./screenshots/main-day-1.png)
![Add task page day 1](./screenshots/add-task-day-1.png)
![All tasks page day 1](./screenshots/all-tasks-day-1.png)
