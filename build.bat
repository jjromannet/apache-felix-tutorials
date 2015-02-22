javac -cp %FELIX_HOME%\bin\felix.jar -d build src\tutorial\example1\Activator.java
jar cfm build\example1.jar src\tutorial\example1\manifest.mf -C build tutorial\example1\Activator.class
javac -cp %FELIX_HOME%\bin\felix.jar -d build src\tutorial\example2\Activator.java src\tutorial\example2\DictionaryImpl.java src\tutorial\example2\service\DictionaryService.java
jar cfm build\example2.jar src\tutorial\example2\manifest.mf -C build tutorial\example2\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example2b\Activator.java src\tutorial\example2b\DictionaryImpl.java
jar cfm build\example2b.jar src\tutorial\example2b\manifest.mf -C build tutorial\example2b\