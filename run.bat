@echo off
if not exist "Password List" md "Password List"
echo Starting Password Generator
echo Input to fields you know and leave others
java Main.java
pause