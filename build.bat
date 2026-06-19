@echo off

if exist out rmdir /s /q out
mkdir out

javac -cp "lib/*" -d out ^
src\com\bus\model\*.java ^
src\com\bus\util\*.java ^
src\com\bus\dao\*.java ^
src\com\bus\service\*.java ^
src\com\bus\exception\*.java ^
src\com\bus\main\*.java

if %errorlevel% neq 0 (
    echo Compilation Failed!
    exit /b
)

copy src\com\bus\util\db.properties out\com\bus\util\db.properties > nul

java -cp "out;lib/*" com.bus.main.Main