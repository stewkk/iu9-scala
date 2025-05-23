ThisBuild / scalaVersion := "2.13.12"
ThisBuild / organization := "com.bmstu-iu9"

lazy val lab2 = project
  .in(file("."))
  .settings(
    name := "Lab2",
    libraryDependencies += "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test,
    scalacOptions += "-deprecation"
  )
