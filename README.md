# AvB

## 26 May, 2017

## By: Mark Fisher

## Description
The other day, I was at the gym practicing Brazilian Jiu Jitsu, and I wanted to know whether a new move I was practicing was increasing my success rate or decreasing it, and the thought occurred to me that this sort of optimization occurs regularly in software development. A/B tests are a design staple for many large commerce sites, so why shouldn't that kind of fine-tuning be used in everyday life?

AvB is an Android mobile app that aims to

### Set Up



## User stories
- [x] As a user, I'd like to visit a page to see a list of all team or club members.
- [x] As a user, I'd like to click a team or club member's entry in the list to visit their profile page, which should include more details about them.
- [x] As a user, I'd like the option to visit an "About" page that explains what the club is, and what they do.
- [x] As a user, I'd like all data persisted in a database, so it's always there when I need it.
- [x] As a user, I'd like to filter the list of users by their particular role in the group, or some other information/category. (For instance, a club may have a treasurer, president, and/or secretary. A sports team may have a goalie, forward, or striker, a book club may have founders and attendees. You're also welcome to filter by something other than role, if it's more relevant to your project.)
- [x] As an administrator, I want to add new users to the club. (User authentication is not required).
- [x] As an administrator, I want to edit user profiles, in case I make a mistake, or need to update their details.
- [x] As an administrator, need the option to delete a user, in case they leave the club or team.

## Specs/
| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
|User can filter the list of users by their particular role in the group|User indicates filter by role|Filter removes entries that don't fit the criteria|
|User can filter the list of users by their sphere of influence|User indicates filter by role|Filter removes entries that don't fit the criteria|
|Administrator can add new users to the club. |Admin. indicates that they want to add a new user|New user is generated|
|Administrator can edit user profiles|Admin. indicates that they want to edit a user profile|Changes to user profile are persisted|
|Administrator can remove user profiles|Admin. indicates that they want to remove a user profile|Removal of user profiles are persisted|

### Future Functionality
- [x] Deploy your application. Include a link to the live site in your project's README.
- [ ] Improve form validation
- [ ]
- [ ] Add Admin login
- [ ] Add functionality to keep track of stats, if relevant. Is your roster for a sports club? Keep track of the individual players' scoring percentage. An extracurricular course or seminar? Keep track of attendance or grades. A hiking group? Keep track of the number of miles each person has hiked with the club, etc.
- [ ] Add a message board area, where club members may chat with each other.
- [ ] Include an area called "announcements", "events", "games", etc. where users can post what events, matches, or meetings the club has planned.
- [ ] Consider implementing AngularFire/Firebase user authentication (Note that this goes beyond the scope of our curriculum, and will require you to complete outside research. Begin with the AngularFire documentation on User Authentication.)
- [ ] Add timestamp to firebase entries

# License

The MIT License (MIT)

Copyright (c) 2017 Mark Fisher

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.


---

## Planning

- [x] Components are used to create display and behavior for templates.
- [x] A router is used to navigate between different pages, including at least one dynamic route.
- [x] A service shares data between multiple components.
- [x] Application can create, edit, and delete instances of a model, persisting all changes in Firebase.
- [x] Data is retrieved from Firebase and displayed in the application.
- [x] Models can be filtered using a custom pipe.
- [x] Project demonstrates understanding of week's concepts. If prompted, you are able to discuss your code with an instructor using correct terminology.
- [x] Project is in a polished, portfolio-quality state.
- [x] Required functionality was in place by the Friday deadline.

### Model(s)
  - [x] Model: member
      - [x] First name (string)
      - [x] Last name (string)
      - [x] Illuminati nickname (string)
      - [x] Sector of influence (string) (technology, politics, media, culture, commerce)
      - [x] Number of twitter followers (number)
      - [x] Net worth (number)
      - [x] Political impact quotient (number)
      - [x] Suspicion that you're a member of the Illuminati quotient (number)
      - [ ] Influence quotient (number) = twitter followers/twitter population + net worth/sum world wealth + political impact quotient - Suspicion that you're a member of the Illuminati quotient
      - [x] Role in the organization (string: Black King, Black Queen, White Queen, White King, General Member, Treasurer, Secretary/Archivist)

### Components

- [x] **about**: I want to log a newly-admitted animal by submitting a form with animal species, name, age, diet, zoo location, number of needed caretakers, sex, one like and one dislike.
- [x] **members-display**:As a user, I'd like to visit a page to see a list of all team or club members.
- [x] **member-details**: As a user, I'd like to click a team or club member's entry in the list to visit their profile page, which should include more details about them.
- [x] **add-member**: As an administrator, I want to add new users to the club. (User authentication is not required).
- [x] **edit-member**: As an administrator, I want to edit user profiles, in case I make a mistake, or need to update their details.


### Pipes

- [x] I want options to view all members
- [x] I want to filter by sector of influence
- [x] I want to filter by group role

### Forms
- [x] Add member
- [x] Edit member

---

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 1.0.0.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive/pipe/service/class/module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `-prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
Before running the tests make sure you are serving the app via `ng serve`.

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Prerequisites/Installation

You will need the following things properly installed on your computer.

* [Git](https://git-scm.com/)
* [Node.js](https://nodejs.org/) (with NPM)
* [Bower](https://bower.io/)
* [Angular](TODO)

## Dependencies

* Materialize (should be set up automatically upon build of this webapp, but if not, simply navigate to the project directory in terminal and type, `bower install Materialize  `)
* *Firebase (should be set up automatically upon build of this webapp, but if not, simply navigate to the project directory in terminal and type, `ember install emberfir`)*


## Setup

Download this project [here](https://github.com/Atticus29/illuminati/archive/master.zip)

Run `ng serve` for a dev server. Navigate to [http://localhost:4200/](http://localhost:4200/). The app will automatically reload if you change any of the source files.
