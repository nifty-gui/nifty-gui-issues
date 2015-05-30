# nifty-gui-issues
add your SSCCE here

The existing issues are relatively old and are only here as an example. They use JOGL as the renderer but that doesn't
mean that everything in here has to use that renderer. Feel free to add whatever renderer you need to reproduce an issue.

However, when your problem is not renderer specific you can simply use the JOGLNiftyRunner to get up and running in
no time. But you could bring your own initialization code as well.

You can add resource files if necessary. However keep in mind to only add what really is essentially to reproduce some
issues. Please take the time to reduce your code to the minimum code required to illustrate a problem.

The existing issue examples, especially the issue 132 code is actually too much but was meant as some form of example/tutorial
how to do certain things as well, so it's a bit longer than what we'd want to see.

Issue examples in here can be run by the command line by simply executing the following commands. It would be nice if
anything you add in here could be run in the same way.

Examples:

``
# build
mvn clean package

# run the issue 132 example
mvn exec:java -Dexec.mainClass="de.lessvoid.issues.issue132.Issue132Main"

# run the issue 136 example
mvn exec:java -Dexec.mainClass="de.lessvoid.issues.issue136.Issue136Main"
``