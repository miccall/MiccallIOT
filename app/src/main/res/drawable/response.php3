<?php 
class Response{
	private static  $result_str ;
	private static  $result;
	/*
	* 按json 返回数据 
	* @param integer $code 状态码
	*/
    public static function json($code,$message="",$date = array())
	{
		if(!is_numeric($code)){
			return "";
		}
		self::$result = array(
			'code'=> $code ,
            'message'=>$message,
            'date' => $date 
			);
		
		echo json_encode(self::$result);
		//exit;
	}
	public static function setstr($str){
		self::$result_str = $str;
		self::resjson($str);
	}
	public static function resjson($str){
		$a = explode('##',$str);
		$arr = array(
		  	 'id' => $a[1], 
		  	 'wendu' => $a[2],
		  	 'shidu' => $a[3],
		  	 'yanwu' => $a[4],
		  	 'time' => $a[5]
		    );
		self::json(200,'success',$arr);
	}
	public static function get(){
		return json_encode(self::$result);
	} 
}


