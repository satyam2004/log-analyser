# Log Analyser

A simple utility to read, parse and flag the evens from a log file.

## To run the app

1. Clone the git repo

```shell
git clone https://github.com/satyam.anand/log-analyser.git
```

2. Open the project in an IDE of your choice.
3. Run the main application - `com.github.satyam.anand.loganalyser.LogAnalyser`.
    1. Create/Update the Run Configuration for the class to provide the program argument.<br />
       ![Edit Run Config window on Intellij](https://github.com/satyam.anand/log-analyser/blob/master/assets/run-config-loganalyser.png)

       <br />
       A sample log file is available in `resources/samples/logfile.txt`
    2. You can generate a sample log file using the
       utility `com.github.satyam.anand.loganalyser.utilities.LogFileGenerator`. You can specify the number of events to
       be generated for the sample file.
    

## Summary of task
Our custom-build server logs different events to a file named logfile.txt. Every event has **2** entries in
the file - one entry when the event was started and another when the event was finished. The entries
in the file have no specific order (a finish event could occur before a start event for a given id).

Every line in the file is a JSON object containing the following event data:

* **id** - the unique event identifier
* **state** - whether the event was started or finished (can have values `STARTED` or `FINISHED`)
* **timestamp** - the timestamp of the event in milliseconds

Application Server logs also have the following additional attributes:
* **type** - type of log
* **host** - hostname

Example contents of logfile.txt:
```json
{"id":"scsmbstgra", "state":"STARTED", "type":"APPLICATI ON_LOG", "host":"12345",
"timestamp":1491377495212}
{"id":"scsmbstgrb", "state":"STARTED", "timestamp":1491377495213}
{"id":"scsmbstgrc", "state":"FINISHED", "timestamp":1491377495218}
{"id":"scsmbstgra", "state":"FINISHED", "type":"APPLICATION_LOG", "host":"12345",
"timestamp":1491377495217}
{"id":"scsmbstgrc", "state":"STARTED", "timestamp":1491377495210}
{"id":"scsmbstgrb", "state":"FINISHED", "timestamp":1491377495216}
...
```
In the example above, the event `scsmbstgrb` duration is `1491377495216 - 1491377495213 = 3ms`.
The longest event is `scsmbstgrc` (`1491377495218 - 1491377495210 = 8ms`).

**The program should:**
* Take the path to logfile.txt as an input argument
* Parse the contents of logfile.txt
* Flag any long events that take longer than 4ms
* Write the found event details to file-based HSQLDB (http://hsqldb.org/) in the working folder
  * The application should create a new table if necessary and store the following values:
    * Event id
    * Event duration
    * Type and Host if applicable
    * Alert (true if the event took longer than 4ms, otherwise false)