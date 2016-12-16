### Task

Write code that will cover the functionality explained below and provide us with the source, instructions to build and run the appliocation  as well as a sample output of an execution:

+ Connect to the [Twitter Streaming API](https://dev.twitter.com/streaming/overview)
+ Retrieve the incoming messages for 30 seconds or up to 100 messages, whichever comes first
+ Your application should return the messages grouped by user (users sorted chronologically, ascending)
+ The messages per user should also be sorted chronologically, ascending
+ For each message, we will need the following:
    * The message ID
    * The creation date of the message as epoch value
    * The text of the message
    * The author of the message
+ For each author, we will need the following:
    * The user ID
    * The creation date of the user as epoch value
    * The name of the user
    * The screen name of the user
+ All the above infomation is provided in either SDTOUT or a log file
+ You are free to choose the output format, provided that it makes it easy to parse and process by a machine
+ Keep track of messages per second statistics across multiple runs of the application
