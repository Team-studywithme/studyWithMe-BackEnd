#!/bin/bash

cd ..

chmod +x ./gradlew

./gradlew build -x test

cd build/libs

nohup java -jar `Dspring.kakao.id=${SECRET}` studywithme-0.0.1-SNAPSHOT.jar &
