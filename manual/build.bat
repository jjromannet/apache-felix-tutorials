javac -cp %FELIX_HOME%\bin\felix.jar -d build src\tutorial\example1\Activator.java
jar cfm build\example1.jar src\tutorial\example1\manifest.mf -C build tutorial\example1\Activator.class
javac -cp %FELIX_HOME%\bin\felix.jar -d build src\tutorial\example2\Activator.java src\tutorial\example2\DictionaryImpl.java src\tutorial\example2\service\DictionaryService.java
jar cfm build\example2.jar src\tutorial\example2\manifest.mf -C build tutorial\example2\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example2b\Activator.java src\tutorial\example2b\DictionaryImpl.java
jar cfm build\example2b.jar src\tutorial\example2b\manifest.mf -C build tutorial\example2b\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example3\Activator.java
jar cfm build\example3.jar src\tutorial\example3\manifest.mf -C build tutorial\example3\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example4\Activator.java
jar cfm build\example4.jar src\tutorial\example4\manifest.mf -C build tutorial\example4\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example5\Activator.java
jar cfm build\example5.jar src\tutorial\example5\manifest.mf -C build tutorial\example5\

javac -cp %FELIX_HOME%\bin\felix.jar;build\example2.jar -d build src\tutorial\example6\Activator.java src\tutorial\example6\service\SpellChecker.java
jar cfm build\example6.jar src\tutorial\example6\manifest.mf -C build tutorial\example6\


