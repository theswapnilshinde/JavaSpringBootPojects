@echo off


if not exist ".\src\main\resources\application.properties" (
    @echo Creating file ".\src\main\resources\application.properties" ...
    copy ".\src\main\resources\application.example.properties" ".\src\main\resources\application.properties"
) else (
    @echo File ".\src\main\resources\application.properties" already exist.
)


if not exist ".\.idea\workspace.xml" (
    @echo Creating file ".\.idea\workspace.xml" ...
    copy ".\.idea\workspace.example.xml" ".\.idea\workspace.xml"
) else (
    @echo File ".\.idea\workspace.xml" already exist.
)
