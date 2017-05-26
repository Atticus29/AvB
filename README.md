# AvB

## 26 May, 2017

## By: Mark Fisher

## Description
The other day, I was at the gym practicing Brazilian Jiu Jitsu, and I wanted to know whether a new move I had learned was increasing my success rate or decreasing it, and the thought occurred to me that this sort of optimization occurs regularly in software development. A/B tests are a design staple for many large e-commerce sites, so why shouldn't that kind of fine-tuning be used in everyday life?

AvB is an Android mobile app that aims to bring A/B testing optimization to daily life. Potential use cases are myriad: will my golf score improve if I change my swing by adding this flourish? Should I ignore the haters and bowl grandma-style with two hands between my legs? If I add this card to my Magic deck, will it increase my win rate? If I make students sign the honor code before taking the exam, will the rate of cheating decrease?, Does treating my honeybees for _Varroa_ mites really make a difference?, etc., etc.

AvB leverages (Bayesian?) statistical model x to perform significance tests and statistical model y to perform power tests (for an approximation of how many trials are necessary to detect statistical significance for a given effect size)...

## User stories
- [ ] As a user, I'd like to be able to create a new experiment.
- [ ] As a user, I'd like to be able to add two different treatment categories to the experiment.
- [ ] As a user, I'd like to be able to add a successful trial to a treatment.
- [ ] As a user, I'd like to be able to add a failed trial to a treatment.
- [ ] As a user, I'd like to be able to set an effect size difference/sensitivity level I'd like to notice between my treatments (i.e., do I care if there's statistical significance but treatment 2 is only 0.01% better than treatment 1?).
- [ ] As a user, I'd like to be able to see all of my created experiments.
- [ ] As a user, I'd like to be able to see how many total trials I have run for each treatment.
- [ ] As a user, I'd like to be able to see how many trials remain for treatment 1 and, separately, for treatment 2, before statistically-significant differences (at effect size x) can be detected.
- [ ] As a user, I'd like to tweet results of my experiments (maybe with #AvB hashtag?).

## Planning

### Master Checklist
- [ ] Create an app with at least, bare minimum, 3 activities that the user can navigate to. Don't neglect about, bio, contact etc. activities.
- [ ] Build these activities out with designs, UI, and functionality as much as possible.
- [ ] Validate form inputs. Use toasts and hide/show to communicate statuses to your user, as well as DialogFragments.
- [ ] Display a list of information. Build this out as much as possible.
- [ ] Implement custom adapters (both Array and from scratch)
- [ ] Use custom typefaces to enhance your designs.
- [ ] Gather user input and pass it to another activity.
- [ ] Use ButterKnife to bind all views.
- [ ] Implement the View.OnClickListener interface to set click listeners to view elements.
- [ ] Manually test your app thoroughly before submitting. A reproducible runtime crash is not acceptable. If you cannot fix the error, submit and reference the error in your readme.
- [ ] Continue using good coding practices, especially indenting properly, commenting complex code segments, making logically separate Git commits, including a detailed README, and removing commented-out code and logging statements before committing.

### Models
- [ ] Experiment
  - [x] Name (String)
  - [ ] Date created (whatever the date object is in Java)
  - [x] Treatment1 (String)
    - [x] Successes (int)
    - [x] Failures (int)
  - [x] Treatment 2 (String)
    - [x] Successes (int)
    - [x] Failures (int)
  - [x] Effect size (double)
  - [ ] Minimum required trials per treatment for statistical significance to be reached (rounded ceiling int, get calculated in constructor rather than user input)

### Forms
- [ ] Create experiment (DialogFragment?); toasts for invalid forms

### Activities
- [ ] MainActivity (landing page)
- [ ] AboutActivity (how the app works)
- [ ] ExperimentsActivity (ListView of experiment names/timestamps?)
- [ ] ExperimentActivity (view of a single experiment with its two treatments, stats, and buttons to add success and failure trials to treatments)

### Adapters
- [ ] ExperimentAdapter (for listing experiment object names/timestamps)

### API
- [ ] Twitter (to Tweet results of completed experiments)

## Specs/
| Behavior                   | Input Example     | Output Example    |
| -------------------------- | -----------------:| -----------------:|
|User can enter experiment details|User enters experiment name, treatment 1 name, treatment 2 name, effect size|experiment details recorded|
|User can enter successful trial of a particular treatment of a particular experiment|User reports successful trial of treatment2|successful trial of treatment2 recorded|
|User can enter failed trial of a particular treatment of a particular experiment|User reports failed trial of treatment2|failed trial of treatment2 recorded|


### Set Up

* Clone repository from GitHub: Navigate to your computer's terminal and type, `git clone https://github.com/Atticus29/AvB.git`
* Open Android Studio (if not already installed, [begin install process here](https://developer.android.com/studio/index.html))
*

### Future Functionality
- [ ] Add twitter API
- [ ] Add user authentication
- [ ] Add DB persistence

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

## Prerequisites/Installation

You will need the following things properly installed on your computer.

* [Git](https://git-scm.com/)
* [Android Studio](https://developer.android.com/studio/index.html)

## Dependencies
* None yet
