<?php
class Found_File{
      Var $fp;
      function found()
      {
      	
      	
      	$files = glob('D:/test/*.txt');
		print_r($files);
		$array_size =  count($files);
		$this->fp = $files[$array_size - 1];
		//iconv("UTF-8","gb2312",$this->fp); 
		//$fp 找到最新文件 。。。。 
		return $this->fp;
		
		/*if (file_exists($this->fp)) 
		{
		    echo file_get_contents($this->fp);
		}
		*/
      } 
};
$f = new Found_File();
$name = $f->found();
echo $name;
