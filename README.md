# word-counter

Counts the number of words given a specified directory

## Quickstart

[](QUICKSTART.md)

## Introduction

I recall a conference where Robert C. Martin (aka Uncle Bob) talked about clean code best practices. I want to showcase one version of the code housekeeping process. The functions contained in this software project as a whole are split amung three different languages: BASH (Bourne Again Shell), Python, and Clojure (a hosted language). When development kicked off BASH was the langauge used to break ground. As Microsoft evangelist ... whose name escapes me but if only I could find ta certain Powershell tutorial... where he said BASH (he said Powershell but same thing considering the big picture) is the equivalent to having a virtual bazzoka. Do you have an business automation problem? BASH it a way. I'm paraphrasing a lot here. Moving on BASH is a tool that can be use to retrive/transform/load dat using it's pipeing feature which can take output form one command and can be passed on to another command. However because not all data is shaped the same. An example of different data shapes can be experienced when you sit down at a restaurant and the waitor hands you a document. You know that it will contain delicious assortments of appetizers, entrees, a la cart items, bevarages, and deserts. In contrast, when you are driving, green signs are informative-navigational, red light --> stop and green --> GO GO GO. 

## Objective

Refactor the entire project to run 100% on Clojure. Currently the whole app works with a combination of CLojure, Python, and BASH. BASH does the heavy lifting of counting the words in a file and calculated insights are generated with the Python and Clojure components. Additionally the BASH component walk the file system to include and count every file within a parrent directory and that of its children recursivly. What this means is if there is a file tree with many nested folders any file located anywhere in the tree will be word counted.

> What a mess. 

## Challenges

### Identify components and develop a strategy to refactor, build, buy, or import from open source libraries

Curretly the way words are counted by this program can be traced to the `count_words` BASH function located in the `word-count.sh` file.

[Concordance](https://github.com/defndaines/concordance/tree/master) is a 100% based Clojure solution and can help by picking up the task of counting words given a file. What it won't do for us is the file system tranversal, a topic I'll articulate in this documentation another time.

Concordance uses a single function to detect words per it's definition of what a words is:


> For the purposes of this library, a "word" is a sequence of letters, numbers, or an apostrophe. All punctuation and white space is ignored (except for the aforementioned apostrophe). The apostrophe is treated as part of a word to avoid "don't" being turned into the nonsensical "don" and "t".


This definition by nature presents a limit. But doesn't this limit give you the idea of why we are limited to counting just words and variations of words such as `do` and `don't`. One word with an apostraphe another without. What about `compound-words` that are teathered by the hyphen or underscore? What about words that are considered to be code? Or hyperlinks? Would we classify all those tokens as words? Of course not. A hyperlink is a hyperlink. An Acronym -> Acronym. #tag -> #tag. This is 2024. We have evolved beyond capturing ideas in words. Right? If an opnline new article contained hyperlinks to the New York Times, Medium, or US department of Commerce website would you consider that article superior to one with the same content that contains nil references? I vote for citations. It shows due diligence and they are comforting to see.

### Provide configuration options to the user

In cases such as

`(def input-map (json/parse-string (slurp "cache/shareable-file.json") true))` in the `metronome.clj` file I hard coded where the location and name of a data file which contains the output of other processes not contained in the Clojure component of the program. This is a big no no when delivering software. However there are cases where it can be perfectly fine. For now it can stay like this but its definitly something to keep in mind. What would make me satisfied is to be able to configure what file will be be consumed by the slurp function in a Clojure edn file (this is an idea). This way the app can cease operation if the file can not be found (at the moment it keeps going and presents the user with a lot of noise). It can be designed in such a way where the user gets back an error code with a message prompting them to double check that configuration item. 