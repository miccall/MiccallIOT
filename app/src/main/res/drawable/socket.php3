<?php
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
 
class Socket{
	Var $sock;
	Var $ret ;
	Var $ip;
	Var $port;
	Var $msgsock;
	Var $msg;

	function __construct(){
		set_time_limit( 0 ); 
		 //设置IP和端口号
		$this->ip = '192.168.1.143' ;
		$this->port = 3030 ;
		echo "server has been open...";
		echo "\n";
		self::Initserver();
	}
    function Initserver()
	{

		if(($this->sock = socket_create(AF_INET,SOCK_STREAM,SOL_TCP)) < 0) {
		     echo "socket_create() 失败的原因是:".socket_strerror($this->sock)."\n";
		 }
		 echo "create"."\n";
		 //允许使用本地地址
		 //socket_set_option($this->socket, SOL_SOCKET, SO_REUSEADDR, TRUE);
		//绑定到socket端口 
		 if(($this->ret = socket_bind($this->sock,$this->ip,$this->port)) < 0) {
		     echo "socket_bind() 失败的原因是:".socket_strerror($this->ret)."\n";
		 }
		 echo "bind"."\n";
		 //开始监听   最大客户端是 4 超过的客户端连接会返回WSAECONNREFUSED错误 
		 if(($this->ret = socket_listen($this->sock,4)) < 0) {
		     echo "socket_listen() 失败的原因是:".socket_strerror($this->ret)."\n";
		 }
		 echo "listen"."\n";
		 $count = 0;
	}
	 function Startserver(){
	 		echo " listening..."."\n";
	 		// 另一个Socket来处理通信 
            if (($this->msgsock = socket_accept($this->sock)) < 0) {
         	    echo "socket_accept() failed: reason: " . socket_strerror($this->msgsock) . "\n";
         	    break;
     	    }else{
     	    	$readline = new ReadLine($this->msgsock);
				$read_line = 0;
				$time = new Timer();
				$time -> loop($readline,$read_line);
     	    	
     	    	 //发到客户端
		         $this->msg ="success !  Welcome To Server!\n";
		         socket_write($this->msgsock, $this->msg, strlen($this->msg));
		         
		         echo "to connect clint...   success !\n";
		         $buf = socket_read($this->msgsock,8192);
		         //接受客户端的消息 ： 
		         $talkback = "message is  :$buf\n";
		         echo $talkback;
		     }
		     //echo $buf;
		     socket_close($this->msgsock);
	 }
	 function setmseeage($txt_msg){
	 	$this->msg =  $txt_msg ; 
	 	echo "socket : ".$this->msg."\n";
	 }

	function closesever(){
	    	socket_close($this->sock);
	}

 };