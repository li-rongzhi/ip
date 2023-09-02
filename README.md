# Jarvis Chatbot
> "Jarvis, sometimes you gotta run before you can walk." - Tony Stark

### Description
The name **Jarvis** stands for [Just A Rather Very Intelligent System](https://en.wikipedia.org/wiki/J.A.R.V.I.S.). This pull request adds task management capabilities to the Jarvis chatbot, allowing users to
* Create tasks
* List tasks
* Mark(unmark) tasks as completed(uncompleted)
* Check tasks with given date
* Store taskList in disk 

**Jarvis** currently supports three kinds of tasks:
*1. ToDo: without time component*
*2. Deadline: with a due time*
*3. Event: consists of a start time(from) and an end time(to)*

The task management feature enhances Jarvis's utility by enabling users to keep track of their to-do list without leaving the chat interface.

### Features
- [x] Basic task management operations
- [x] Store taskList in disk
- [ ] A nice GUI(Coming soon)
- [ ] More exciting features...

### How to Test
1. Install the Jarvis chatbot on your preferred platform.
2. Run `Launcher` file to launch the chatbot 🥰

___
#### If you are a Java programmer, you can also use it to practice Java. Here's the `main` method in `Launcher`:
```java
public static void main(String[] args) {
    Application.launch(Main.class, args);
}
```
