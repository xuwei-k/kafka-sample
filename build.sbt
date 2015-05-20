scalaVersion := "2.11.6"

libraryDependencies += "org.apache.kafka" %% "kafka" % "0.8.2.1" exclude("junit", "junit")

scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)
