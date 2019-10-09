This is a sample application project highlighting the microservice architecture developed in scala using play. Asynchronous calls have been demonstrated using akka's actor system and by usage of futures.

Cassandra database has been used with respective connection details in order to make the rest services complete & meaningful. This can be changed according to one's db requirements.

Example REST endpoints have been created on simple resource "Employee" by GET, POST and DELETE for demonstration. Scalatestplus has been used to generate sample test cases for few scenarios. All dependencies are added and should be automatically downloaded/ resolved on importing the project.

The intention is to provide a small working project to begin with a microservice, without having to go through the process of setting up all the environment, struggling with version selection, looking up drivers or corresponding dependencies. It is also useful for learning the play-scala-akka working in non blocking way for beginners.
