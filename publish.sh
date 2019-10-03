#!/usr/bin/env bash

sbt +clean +test +clientJS/publishSigned +clientJVM/publishSigned
sbt sonatypeBundleRelease