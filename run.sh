#!/bin/bash
# Compila todos los .java de src y los guarda en bin
javac -d bin src/*.java

if [ $? -eq 0 ]; then
    echo "--- Ejecutando programa ---"
    java -cp bin Main
else
    echo "Error de compilación"
fi
