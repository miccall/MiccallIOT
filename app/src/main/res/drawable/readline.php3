<?php //header('charset: utf-8;');
//格式化字符串
//include 'newfilefound.php3';
Class ReadLine{
	Var $num;
	Var $wendu;
	Var $shidu;
	Var $time;
	Var $msg_sock;
	function __construct($msgsock)
	{
		$this->msg_sock=$msgsock;
	}
	function read($line){
		$f = new Found_File();
        $name = $f->found();
	    $fp = 'D:\test\20161030101436.txt' ;
	    $fp = $name;
		$myfile = fopen($fp, "r") or die("Unable to open file!");
		$mywritefile = fopen("testfile.txt", "w");
		$count = 0;
		$str_num = 0;
		while(!feof($myfile))
		{   
		  $str= fgets($myfile);
		  $str = mb_convert_encoding($str, 'utf-8','gbk');
		  //echo $str;
			if($count>0 && $count==$str_num+1)
			{
				//echo $str;
				//echo "count: ".$count." ";
				$a = explode(')',$str);
				if($a[0]=="") break;
				$str_num = trim($a[0]);
				$str = $a[1] ;
				$a = explode('℃',$str);
				$str_wendu = trim($a[0]);
				$str = $a[1];
				$a = explode('%',$str);
				$str_shidu = trim($a[0]);
				$str = trim($a[1]);
				$str_yanwu = substr($str,0,5);
		        $str_yanwu = trim($str_yanwu);
		        $a = explode('日',$str);
		        $str_time = $a[1];
		        //echo "line :".$line."\n";
		        if($line==$count){
		        	$mmmm = "##".$str_num."##".$str_wendu."##".$str_shidu."##".$str_yanwu."##".$str_time."";
		        	Response::setstr($mmmm);
		        	$mmmm = Response::get()."\n";
		        	echo "mmmmm : ".$mmmm."\n";
		        	//$this->msg ="success !  Welcome To Server!\n";
		            socket_write($this->msg_sock, $mmmm, strlen($mmmm));
		            echo "to connect clint...   success !\n";
		            $buf = socket_read($this->msg_sock,8192);
		            //接受客户端的消息 ： 
		            $talkback = "message is  :$buf\n";
		            echo $talkback;
		        	//echo "read : ".$mmmm."\n"; 
		        	//$this->t_socket->setmseeage($mmmm);
		        	
		        	//$socket->Startserver();
					/*
					echo $str_num;
					echo "\n";
					echo $str_wendu;
					echo "\n";
					echo $str_shidu;
					echo "\n";
					echo $str_yanwu;
					echo "\n";
					echo $str_time;
					echo "\n";
					*/
					echo "\n";
		    }
			fwrite($mywritefile, $str);
		  }
		  $count++;
		}
		//echo $count;
		fclose($mywritefile);
		fclose($myfile);
		return $count;
	}
}
/*
$r = new ReadLine();
$read_line = 15;
//while(1){
//	usleep(5000);
    $read_line = $r->read($read_line);
    //echo $read_line;
//}*/
