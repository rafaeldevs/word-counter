# Babashka Tasks Quickstart Guide

This guide provides an overview of the Babashka tasks available for this word counting tool and document statistical analysis using Babashka. Each task has a specific function and can be executed via the command line.

> *Babashka Task* a discrete unit of work that can be executed independently or as part of a sequence of its kind.

> *Babashka* is a native Clojure interpreter for scripting with fast startup.

> Clojure is a concise, powerful, and performant general-purpose  programming language that runs on the Java Virtual Machine, Common Language Runtime, JavaScript runtimes (aka Node.js or modern mobile/desktop web browsers), and Babashka.

See the [Babashka documentation](https://github.com/babashka/babashka#installation) for more information

## Requirements

This project ships with batteries included. In other words after installing Babashka and granting the correct permissions to the `word-count.sh` and `metronome.clj` files you should be able to issue commands successfully.

- babashka v1.3.18

## How to Use

1. **Install Babashka**: Make sure you have Babashka installed on your system. You can download it from [Babashka GitHub](https://github.com/babashka/babashka).

2. **Execute Tasks**: The first two tasks are ready, rest need QA

3. **Customize**: In development

By following this guide, you can efficiently utilize the Babashka tasks to manage and analyze word counts and reading times in your directory.

## Tasks

The following task can be executed on the command line

1. **Directory**
   - **Description:** Given a directory sums the number of words and more
   - **Command:** `bb directory`
   - **Task:** Executes the `./word-count.sh book` script.
   - **Documentation:** `Number of words in directory`
   ```bash
   bb directory
   ```

   ![](screenshots/bb-directory.png)

2. **Word Count**
   - **Description:** Renders the total Reading time in seconds for the directory
   - **Command:** `bb word-count`
   - **Task:** Executes the `./csv.clj cache/num-words.csv` script.
   - **Documentation:** `List of durations in seconds`
   ```bash
   bb word-count
   ```

   ![](screenshots/bb-word-count.png)

3. **Help**
   - **Description:** Display the help message.
   - **Command:** `bb help`
   - **Task:** Executes the `./metronome.clj help` script and overrides the builtin help.
   - **Documentation:** `Help message`
   ```bash
   bb help
   ```

4. **File**
   - **Description:** Produces word-count, reading-count, raw to .999-refined.
   - **Command:** `bb file`
   - **Task:** Executes the `./metronome.clj file` script.
   - **Documentation:** `Produces word-count, reading-count, raw to .999-refined`
   ```bash
   bb file
   ```

5. **Reading Speed**
   - **Description:** Calculate reading speed in words per minute (wpm).
   - **Command:** `bb reading-speed`
   - **Task:** Executes the `./metronome.clj reading-speed` script.
   - **Documentation:** `Reading speed (wpm)`
   ```bash
   bb reading-speed
   ```

6. **Word Total**
   - **Description:** Calculate the total number of words in the provided path.
   - **Command:** `bb word-total`
   - **Task:** Executes the `./metronome.clj number-of-words` script.
   - **Documentation:** `The Word Total of the path provided.`
   ```bash
   bb word-total
   ```

7. **Reading Time (Hour)**
   - **Description:** Calculate reading time in hours.
   - **Command:** `bb reading-time-hour`
   - **Task:** Executes the `./metronome.clj reading-time-hour` script.
   - **Documentation:** `Calculated reading time (hr)`
   ```bash
   bb reading-time-hour
   ```

8. **Reading Time (Minute)**
   - **Description:** Calculate reading time in minutes.
   - **Command:** `bb reading-time-minute`
   - **Task:** Executes the `./metronome.clj reading-time-minute` script.
   - **Documentation:** `Calculated reading time (min)`
   ```bash
   bb reading-time-minute
   ```

9. **Reading Time (Verses)**
   - **Description:** Calculate reading time in five-minute intervals.
   - **Command:** `bb reading-time-verses`
   - **Task:** Executes the `./metronome.clj five-minute-verses` script.
   - **Documentation:** `Calculated reading time (5 min)`
   ```bash
   bb reading-time-verses
   ```
