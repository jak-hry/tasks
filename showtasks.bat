call .\runcrud
if "%ERRORLEVEL%" == "0" goto rename
echo.
echo RUNCRUD has errors
goto fail

:rename
start chrome http://localhost:8080/crud/v1/tasks
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.