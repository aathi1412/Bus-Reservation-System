@echo off

if exist out rmdir /s /q out
mkdir out

javac -cp "lib/*" -d out ^
src\com\bus\model\*.java ^
src\com\bus\util\*.java ^
src\com\bus\dao\*.java ^
src\com\bus\service\*.java ^
src\com\bus\main\*.java

if %errorlevel% neq 0 (
    echo Compilation Failed!
    exit /b
)

java -cp "out;lib/*" com.bus.main.Main