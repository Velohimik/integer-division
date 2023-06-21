#! /bin/bash
echo Building a jar...
./mvnw clean package >nul 2>nul
echo ...successfull √
echo Copying jar and div scripts to /dist folder...
if [ ! -d dist/ ]
then
	mkdir dist/
fi
cp ./target/integer-division-0.1-SNAPSHOT-jar-with-dependencies.jar ./div.sh ./div.bat ./dist/
echo ...successfull √
