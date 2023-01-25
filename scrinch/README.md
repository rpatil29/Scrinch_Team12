## Dr. Gary Notes
Scrinch is a pretty full-featured Java Swing application that you can get off SourceForge
https://sourceforge.net/projects/scrinch/

The Maven build does not work completely for me, I had to comment out trying to build the
nbmodule and the webstart. But application builds and you can run the project from the
command-line doing this from directory application/target/dist after a "mvn clean install"

java -Xmx128m -jar scrinch-1.2-RACHMANINOV.jar
