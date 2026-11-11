#!/usr/bin/env bash
cd "$(dirname "$0")"
mkdir -p build
javac -d build -cp "lib/jlayer-1.0.1.jar" $(find src -name "*.java")