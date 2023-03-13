# WORDS - get statistics of a text file
This is a Java application that displays some statistics related to one file (number of lines, words, etc).

## Requirements
- Java 8

## Provided statistics
- Number of words
- Number of characters
- Number of syllables
- Number of sentences
- Number of lines
- Number of paragraphs
- The most commonly used word
- Most common word count
- The reading level of text

## Usage
Open the jar file on MacOS -> drag a text file onto the application -> it displays all statistics -> you can close the app from the X aka exit button.


## Project structure
This project was designed using Gradle with JDK 8+.
- The application root package is **com.imaeots.wordcounter**
- The logic for displaying an application Frame and reading data from the file sysitem is available in the **com.imaeots.wordcounter.entities.shell.DragAndDropFileFrame** class.
- The logic for calculating the statistics is available in the **com.imaeots.wordcounter.entities.shell.Statistics** class.

## Credits and license
This is for use-only! Modifications are allowed only locally. If you wish to develop it further and publish it then contact as it is not allowed without doing so.
# This was created by IMaeots