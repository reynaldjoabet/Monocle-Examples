// The simplest possible sbt build file is just one line:

name := "Monocle-Examples"

version := "1.0"

lazy val `monocle-scala2-examples` = (project in file("monocle-scala2-examples")).settings(
  scalaVersion := "2.13.12",
  libraryDependencies ++= Seq(
    "dev.optics" %% "monocle-core"  % "3.2.0",
    "dev.optics" %% "monocle-macro" % "3.2.0"
  )
)

lazy val `monocle-scala3-examples` = (project in file("monocle-scala3-examples")).settings(
  scalaVersion := "3.3.1",
  libraryDependencies ++= Seq(
    "dev.optics" %% "monocle-core"  % "3.2.0",
    "dev.optics" %% "monocle-macro" % "3.2.0"
  ),
  scalacOptions ++= Seq(
    "-no-indent"
  )
)
// by default sbt run runs the program in the same JVM as sbt
//in order to run the program in a different JVM, we add the following

//fork in run := true

ThisBuild/ usePipelining := true
