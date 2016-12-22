<?php
include 'readline.php3';
include 'TimeLooper.php3';
include 'newfilefound.php3';
include 'response.php3';
include 'socket.php3';
$socket = new Socket();
$socket->Startserver();
/*
$readline = new ReadLine();
$read_line = 0;
$time = new Timer();
$time -> loop($readline,$read_line);
//*/



