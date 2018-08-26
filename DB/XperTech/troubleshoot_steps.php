<?php  
 require "init.php";  
 $troubleshoot_id = $_POST["troubleshoot_id"];
 $sql_query = "select trbl_steps_desc from troubleshoot_steps where troubleshoot_id = '$troubleshoot_id';";  
 $result = mysqli_query($con,$sql_query); 
 $array = array();
 $index = 0;
 if(mysqli_num_rows($result) > 0 ) {  
	 while ($row = mysqli_fetch_assoc($result)) {
	 	$array[$index] = $row['trbl_steps_desc'];
	 	$index++;
	 }
	 $trbl_steps_desc = implode("$", $array);
	 echo $trbl_steps_desc;
 }  
 else  
 {   
 echo "No cable box found.";  
 }  
 ?>  