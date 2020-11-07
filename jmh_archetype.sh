#!/bin/bash
 mvn archetype:generate \
          -DinteractiveMode=false \
          -DarchetypeGroupId=org.openjdk.jmh \
          -DarchetypeArtifactId=jmh-java-benchmark-archetype \
          -DgroupId=pl.setblack \
          -DartifactId=immu-benchmark \
          -Dversion=1.0
