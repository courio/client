import sbtcrossproject.CrossPlugin.autoImport.{crossProject, CrossType}

name := "courio-client"
organization in ThisBuild := "io.cour"
version in ThisBuild := "1.0.1"
scalaVersion in ThisBuild := "2.13.1"

resolvers in ThisBuild ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)
scalacOptions in ThisBuild ++= Seq("-unchecked", "-deprecation", "-feature")

publishTo in ThisBuild := sonatypePublishToBundle.value
sonatypeProfileName in ThisBuild := "io.cour"
publishMavenStyle in ThisBuild := true
licenses in ThisBuild := Seq("MIT" -> url("https://github.com/courio/client/blob/master/LICENSE"))
sonatypeProjectHosting in ThisBuild := Some(xerial.sbt.Sonatype.GitHubHosting("courio", "client", "contact@courio.com"))
homepage in ThisBuild := Some(url("https://courio.com"))
scmInfo in ThisBuild := Some(
  ScmInfo(
    url("https://github.com/courio/client"),
    "scm:git@github.com:courio/client.git"
  )
)
developers in ThisBuild := List(
  Developer(id="darkfrog", name="Matt Hicks", email="matt@matthicks.com", url=url("http://matthicks.com"))
)

val courioCoreVersion = "1.0.1"
val youiVersion = "0.11.32"

val scalatestVersion = "3.1.0-SNAP13"

lazy val root = project.in(file("."))
  .aggregate(clientJS, clientJVM)
  .settings(
    name := "courio-client",
    publish := {},
    publishLocal := {}
  )

lazy val client = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("client"))
  .settings(
    name := "courio-client",
    libraryDependencies ++= Seq(
      "io.cour" %%% "courio-core" % courioCoreVersion,
      "io.youi" %%% "youi-core" % youiVersion,
      "org.scalatest" %%% "scalatest" % scalatestVersion % "test"
    )
  )
  .jvmSettings(
    libraryDependencies ++= Seq(
      "io.youi" %% "youi-server-undertow" % youiVersion
    )
  )

lazy val clientJS = client.js
lazy val clientJVM = client.jvm
