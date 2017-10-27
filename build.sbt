name := "AsynchronousDemo"

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0" % "test",
  "com.datastax.cassandra" % "cassandra-driver-core" % "3.2.0"
)


    