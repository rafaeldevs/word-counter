# Babashka Tasks Quickstart Guide

This guide provides an overview of the Babashka tasks available for this word counting tool and document statistical analysis using Babashka. Each task has a specific function and can be executed via the command line.

> [*Babashka Task*](docs/babashka-task.md) a discrete unit of work that can be executed independently or as part of a sequence of its kind.

> *Babashka* is a native Clojure interpreter for scripting with fast startup.

> Clojure is a concise, powerful, and performant general-purpose  programming language that runs on the Java Virtual Machine, Common Language Runtime, JavaScript runtimes (aka Node.js or modern mobile/desktop web browsers), and Babashka.

## Requirements

After installing Babashka, Python, and granting the correct permissions to the `word-count.sh` and `metronome.clj` files you should be able to issue commands successfully.

- Babashka (v1.3.18 used in development/testing)
- Python (v3.10.12 used in development/testing)

## How to Use

1. **Install Babashka**: Make sure you have Babashka installed on your system. You can download it from [Babashka GitHub](https://github.com/babashka/babashka).

2. **Grant executable file permissions to files**: `word-count.sh` and `metronome.clj`
   - `chmod +x word-count.sh`
   - `chmod +x metronome.clj`

3. **Execute Tasks**: All tasks work and the project is configured for development. 

> If you want to get the word count of a directory use `word-count.sh <dir>` or modify the `directory` Babashka task found in `bb.edn` with your directory.

4. **Customize**: In development

By following this guide, you can efficiently utilize the Babashka tasks to manage and analyze word counts and reading times in your directory.

## Tasks

The following task can be executed on the command line

1. **Help**
   - **Description:** Display the available Babashka tasks with documentation
   - **Command:** `bb tasks`

   ![](screenshots/bb-tasks.png)

2. **Word Count**
   - **Description:** Sums the num_words column in num-words.csv
   - **Command:** `bb word-count`

   ![](screenshots/bb-word-count.png)

3. **Word Total**
   - **Description:** Calculates the total number of words (recursive)
   - **Command:** `bb word-total`
   
   ![](screenshots/bb-word-total.png)

4. **Reading Speed**
   - **Description:** Displays set reading speed. To configure see `reading_speed` variable found in `calc-reading-time.py`
   - **Command:** `bb reading-speed`

   ![](screenshots/bb-reading-speed.png)

5. **Reading Time (Minute)**
   - **Description:** Calculate reading time in minutes found in the target directory
   - **Command:** `bb reading-time-minute`

   ![](screenshots/bb-reading-time-minute.png)

6. **Reading Time (Hour)**
   - **Description:** Calculate reading time in hours found in the target directory
   - **Command:** `bb reading-time-hour`

   ![](screenshots/bb-reading-time-hour.png)

7. **Reading Time (Verses)**
   - **Description:** Calculate five-minute reading intervals found in the target directory
   - **Command:** `bb reading-time-verses`

   ![](screenshots/bb-reading-time-verses.png)

8. **Directory**
   - **Description:** Analyzes target a directory (sums word count, calculates reading time, ) Exports JSON file and STDOUT
   - **Command:** `bb directory`

   ![](screenshots/bb-directory.png)

9. **File**
   - **Description:** Analyzes target a directory (sums word count, calculates reading time, ) Exports JSON file. Less console output
   - **Command:** `bb file`

   ![](screenshots/bb-file.png)
