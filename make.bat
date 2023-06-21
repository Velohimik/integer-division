@ echo off
chcp 65001 > NUL
echo Building a jar...
call .\mvnw.cmd clean package > NUL 2>NUL
echo ...successfull √
echo Copy jar and div scripts to \dist folder...
if NOT EXIST ".\dist\" mkdir .\dist\
copy .\target\integer-division-0.1-SNAPSHOT-jar-with-dependencies.jar .\dist\ > NUL
copy .\div.bat .\dist\ > NUL
copy .\div.sh .\dist\ > NUL
echo ...successfull √