
scalaVersion := "2.12.4"

scalacOptions ++= Seq(
  "-Ypartial-unification"
)


libraryDependencies += "org.typelevel" %% "cats-core" % "1.1.0"
libraryDependencies += "org.typelevel" %% "cats-macros" % "1.1.0"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"
