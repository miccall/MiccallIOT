<?php
//include 'readline.php3';

class Timer{
	Var $lasttime ; 
	
    function __construct()
	{
		$lasttime = 0;
	}
	function loop($readline,$read_line_loop){
		$lasttime = 0;
		while(true){
			$time = time();
		    $currenttime = date("s",$time);
		    $currenttime = intval($currenttime);
		    //echo $currenttime."\n";
			if(($currenttime - $lasttime)>=1||($currenttime - $lasttime)==-59)
			{
				
				echo "current: ".$currenttime."\n";
				$read_line_loop = $readline->read($read_line_loop);
    			//echo "read_line_loop : ".$read_line_loop."\n";
    			//$read_line_loop++;
				//echo "last   : ".$lasttime."\n";*/
				$lasttime = $currenttime;
			}
		}
	}
};

/*
$readline = new ReadLine();
$read_line = 0;
$time = new Timer();
$time -> loop($readline,$read_line);
//*/






