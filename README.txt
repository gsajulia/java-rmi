-> Abrir o terminal que vai rodar o servidor (precisa ser antes do cliente)

javac *.java

export CLASSPATH=$CLASSPATH:/Users/julia/Desktop/teste/trabalho-rmi

rmiregistry &

java -cp . BandsServer &


-> Abrir o terminal que vai rodar o cliente

java BandsClient localhost