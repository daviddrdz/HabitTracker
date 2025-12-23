#!/bin/bash

if [ ! -d "bin" ]; then
    mkdir bin
fi

javac -d bin src/*.java

if [ $? -eq 0 ]; then
    clear
    java -cp bin Main
else
    echo "Error de compilación"
fi
