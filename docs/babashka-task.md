# Babashka Task

In the context of a task runner like Babashka, a "task" refers to a discrete unit of work that can be executed independently or as part of a sequence of its kind. Tasks in Babashka are typically defined in a way that they can be easily scripted, automated, and reused, making them an integral part of build automation, development workflows, and system maintenance.

## Key Characteristics of a Task in Babashka:

1. **Scriptable**: Tasks are usually written in Clojure, leveraging Babashka's scripting capabilities to create concise and efficient scripts that run quickly.
   
2. **Independent**: Each task is designed to perform a specific function or operation. Examples include running tests, building projects, cleaning directories, or deploying applications.

3. **Composable**: Tasks can be composed together to form more complex workflows. This allows for chaining tasks, where the output of one task might be used as the input for another.

4. **Reproducible**: Tasks should produce consistent results when run multiple times under the same conditions, making them reliable components of automated processes.

5. **Automatable**: Tasks are intended to be run without manual intervention, enabling automated build and deployment processes.

6. **Parameterized**: Tasks can accept arguments or parameters, allowing them to be customized for different scenarios or environments.

## Example of a Task in Babashka

Here is a task definition Babashka map:

```
{
word-count {:doc "List of durations in seconds"
            :task (shell "./csv.clj cache/num-words.csv")}

directory {:doc "Number of words in directory"
            :task (shell "./word-count.sh book")}

help {:doc "Help message"
            :task (shell "./metronome.clj help")
            :override-builtin true}
}
```

## Running Tasks

To run a task, you use Babashka's task runner command. For example, to clean the build directory, you would run:

```sh
bb help
```

To build the project, you would run:

```sh
bb directory
```

And to run the tests, you would run:

```sh
bb word-count
```

### Summary

A task in Babashka is a scriptable, independent, composable, reproducible, automatable, and parameterized unit of work. It allows developers to define and automate various parts of their development and deployment workflows efficiently using Babashka's capabilities.