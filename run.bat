rem @echo off
java -jar lib\jflex-1.6.1.jar src\edu\uap\tripla.flex
java -jar  lib\java-cup-11a.jar src\edu\uap\tripla.cup

copy parser.java src\edu\uap
copy sym.java src\edu\uap
