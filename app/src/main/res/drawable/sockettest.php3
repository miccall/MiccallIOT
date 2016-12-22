<?php
set_time_limit( 0 ); 
 //设置IP和端口号
$ip = '192.168.1.133' ;
$port = 3030 ;
echo "server has been open...";
echo "\n";
echo "listening......";
/*
   +-------------------------------
  *    @socket通信整个过程
  *+-------------------------------
  *    @socket_create
  *    @socket_bind
  *    @socket_listen
  *    @socket_accept
  *    @socket_read
  *    @socket_write
  *    @socket_close
  +--------------------------------
  */
/*----------------    以下操作都是手册上的    -------------------*/
//创建socket 
if(($sock = socket_create(AF_INET,SOCK_STREAM,SOL_TCP)) < 0) {
     echo "socket_create() 失败的原因是:".socket_strerror($sock)."\n";
 }
//绑定到socket端口 
 if(($ret = socket_bind($sock,$ip,$port)) < 0) {
     echo "socket_bind() 失败的原因是:".socket_strerror($ret)."\n";
 }
 //开始监听 
 if(($ret = socket_listen($sock,4)) < 0) {
     echo "socket_listen() 失败的原因是:".socket_strerror($ret)."\n";
 }
 
 $count = 0;
 
 do {
     if (($msgsock = socket_accept($sock)) < 0) {
         echo "socket_accept() failed: reason: " . socket_strerror($msgsock) . "\n";
         break;
     } else {
         
         //发到客户端
         $msg ="success !  Welcome To Server!\n";
         socket_write($msgsock, $msg, strlen($msg));
         
         echo "to connect clint...   success !\n";
         $buf = socket_read($msgsock,8192);
         
         //接受服务器的消息 ： 
         $talkback = "message is  :$buf\n";
         echo $talkback;
         
     }
     //echo $buf;
     socket_close($msgsock);
 
 } while (true);
 
 socket_close($sock);
 ?>