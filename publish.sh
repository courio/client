#!/usr/bin/env bash

set -e

sbt +clean +clientJVM/test +clientJS/publishSigned +clientJVM/publishSigned
sbt sonatypeBundleRelease