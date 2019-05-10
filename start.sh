#!/bin/sh
##java env
export JAVA_HOME=/jdk1.8.0_211
export JRE_HOME=$JAVA_HOME/jre

API_NAME=./SimpleDubbo-rpc/target/
JAR_NAME=$API_NAME\*.jar
#PID 
PID=$API_NAME\.pid


#提示用户输入

usage() {
   echo "Usage: sh start.sh [start|stop|restart|status]"
   exit 1
}

#检查程序是否在运行
is_exist() {
  pid=`ps ef | grep $JAR_NAME | grep -v grep | awk '{print $2}'`
  #如果不催在返回1，存在反回0
  if [ -z "${pid}" ]; then
    return 1
  else 
    return 0
  fi
}

#启动程序
start() {
  echo $JAR_NAME
 is_exist
 if [ $? -eq "0" ]; then
   echo ">>> ${JAVA_HOME} is already running PID=${pid} <<<"
 else
   #nohup $JRE_HOME/bin/java -Xms256m -Xmx51m -jar $JAR_NAME >/dev/null 2>&1 & 
   nohup $JRE_HOME/bin/java -Xms256m -Xmx510m -jar $JAR_NAME
   echo &! > $PID
   echo ">> start $JAR_NAME successful PID=$! <<"
  fi
}

#挺直方法
stop() {
  #判断有没有这个服务
  pidf=$(cat $PID)
  #echo "$pidf"
  echo ">> api PID = $pidf begin kill $pidf <<"
  kill $pidf
  rm -rf $PID
  sleep 2
  #判断
  is_exist
  if [ $? -eq "0" ]; then
     echo ">> api 2 PID = $pid begin kill -9 $pid <<"
     kill -9 $pid
     sleep 2
     echo ">> $JAR_NAME process stopped <<"
  else
     echo ">> ${JAR_NAME} is not running <<"
  fi
}

#输出运行状态
status(){
  is_exist
  if [ $? -eq "0" ]; then
    echo ">>> ${JAR_NAME} is running PID is ${pid} <<<"
  else
    echo ">>> ${JAR_NAME} is not running <<<"
  fi
}

#重启
restart(){
  stop
  start
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "status")
    status
    ;;
  "restart")
    restart
    ;;
  *)
    usage
    ;;
esac
exit 0


