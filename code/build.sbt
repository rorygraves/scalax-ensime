// mkdir -p src/{main,test}/{fortran,c,java,scala}/

organization := "com.github.fommil"
name := "scalax"
version := "1.0-SNAPSHOT"
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.spire-math" %% "spire"     % "0.8.2",
  "org.scalatest"  %% "scalatest" % "2.2.2" % "test"
)

lionRuns := 0
lionAllocRuns := 1
lionAllocRate := 1
