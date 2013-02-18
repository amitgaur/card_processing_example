Choice of language

1. Java

I am most well versed in programming in Java and hence it was the language of choice for me. 

2.Maven 

Dependency management/packaging works well

Requirements for runinng the solution 

1. Requirement : Java 1.6 and Maven 2 or Maven 3 installed on the running machine 
2. MVN_HOME and JAVA_HOME variables set 
3. Run mvn verify in the directory to validate Maven setup


Running the solution 

1. To run in the command line option mode run the following command on the prompt in the base directory
eg .

mvn exec:java -Dexec.mainClass="com.amitgaur.braintree.application.ApplicationMain" 

a) Input the line items and the stdin input can be terminate with a "Done" or an empty line


To run in file parsing option mode, run the following command on the prompt

mvn exec:java -Dexec.mainClass="com.amitgaur.braintree.application.ApplicationMain" -Dexec.args="-filepath " 

The must be the fully qualified path name of the file on disk

eg. mvn package exec:java -Dexec.mainClass="com.amitgaur.braintree.application.ApplicationMain" -Dexec.args="-filepath card_data.txt"

Logging : The app creates a log file in log/application.log that contains errors/status information on processing.



Shortcomings 

1. I could have written more tests, test coverage is not ideal 

2. I would have liked to use a dependency injection framework to bootstrap the application 

3. Scalability : All items are read into memory : if the file is really large, the app can run out of out of memory errors

