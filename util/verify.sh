#!/bin/bash

cd vulnerable-code
.\mvnw verify -B
cd ..

# verify fixed code project
cd fixed-code
.\mvnw verify -B
cd ..