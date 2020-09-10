#! /bin/sh
runFile=hrdh-service.jar
logFile=hrdh-service.log

start(){
        exec java -jar $runFile >> /usr/local/java-project/hrdh/hrdh-service/logs/$logFile &
        sleep 5s
        tail -200 /usr/local/java-project/hrdh/hrdh-service/logs/$logFile
}

stop(){
        ps -ef|grep $runFile|grep -v "grep"|awk '{print $2}'|while read pid
        do
           kill $pid
           echo "killed "$pid
        done
        sleep 3s
        ps -ef|grep $runFile|grep -v "grep"|awk '{print $2}'|while read pid
        do
           kill -9 $pid
           echo "killed "$pid
        done
}

case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
restart)
  stop
  start
  ;;
*)
  printf 'Usage: %s {start|stop|restart}\n' "$prog"
  exit 1
  ;;
esac
