@echo off
echo 1. Сборка проекта
call mvn clean install

echo 2. Определение путей необходимых каталогов и файлов
SET FRONTEND_PATH=%CD%\frontend
SET BACKEND_PATH=%CD%\backend

echo 3. Очистка директории wabapp томката
RMDIR %CATALINA_HOME%\webapps\ROOT
RMDIR %CATALINA_HOME%\webapps\api
DEL %CATALINA_HOME%\ROOT.war
DEL %CATALINA_HOME%\api.war

echo 4. Копирвание war-ников в директорию томката
MOVE %FRONTEND_PATH%\target\*.war %CATALINA_HOME%\webapps\ROOT.war
MOVE %BACKEND_PATH%\rest\target\*.war %CATALINA_HOME%\webapps\api.war

echo 5. Запуск сервера
call %CATALINA_HOME%\bin\startup