# Jarvis User Guide

## Features 

### Add task: ```todo / deadline / event```
Jarvis currently support three types of tasks: 
1. ToDo is a task without time constraints.

   ***Format:***
    ```todo {task description}```
2. Deadline is a task with a due time.

   ***Format:***
   ```deadline {task description} /by {dd/MM/yyyy HH:mm}```
3. Event is a task with a start time and an end time.

   ***Format:***
   ```event {task description} /from {dd/MM/yyyy HH:mm} /to {dd/MM/yyyy HH:mm}```

### Delete task: ```delete```
Delete a specific task by index.
    
***Format:***
    ```delete {index}```

### Mark / Unmark task: ```mark / unmark```
Mark a task as done or unmark a task as not done yet by indicating index.

(Mark) ***Format:***
    ```mark {index}```

(Unmark) ***Format:***
    ```unmark {index}```

### List all tasks: ```list```
Display all tasks currently in the task list.

***Format:***
    ```list```

### Find tasks with keyword: ```find```
Find all tasks currently in the task list, 
whose description contains the given keyword.

***Format:***
    ```find {keyword}```

### Check tasks on a given day: ```check```
Check if there is any task on the given day.

***Format:***
    ```check {dd/MM/yyyy}```

### Exiting the program: ```bye```
Exits the program.

***Format:***
    ```bye```

## FAQ
***Q:*** Can I use keyword in with letters in different cases?

***A:*** Yes. Jarvis is capable of recognizing keywords 
even if letters are in a different case.

***Q:*** Can I use date and time formats other than the format specified above?

***A:*** No, Jarvis currently supports tasks with dates and times in the ```dd/MM/yyyy HH:mm``` format when adding task,
and ```dd/MM/yy``` format when checking tasks. Please ensure your input follows this format when dealing with tasks.


