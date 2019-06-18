SET JAVA_HOME = "C:\Program Files\Java\jdk1.8.0_121\bin"
SET PATH =%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET JFLEX_HOME = C:\Users\p_ab1\Desktop\USAC\jflex-1.7.0\lib
cd C:\Users\p_ab1\Desktop\Compi 2\Inteprete\src\inteprete
java -jar C:\Users\p_ab1\Desktop\USAC\jflex-1.7.0\lib\jflex-full-1.7.0.jar lexico.jlex
java -jar C:\Users\p_ab1\Desktop\USAC\java-cup-11b.jar -parser Sintactico -symbols Simbolos sintactico.cup

