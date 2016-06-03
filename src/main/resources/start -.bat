@echo off
echo ********************************
echo Running Super Simple Stocks Test
echo ********************************

@rem setting classpath
set classpath=stockservice-0.0.1-SNAPSHOT-jar-with-dependencies.jar

@rem executing application
java -jar stockservice-0.0.1-SNAPSHOT-jar-with-dependencies.jar

echo ********************************
echo Excecution done.
echo ********************************


pause