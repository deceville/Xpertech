<?php  
 require "init.php";  
 $manual_id = $_POST["manual_id"];
 $sql_query = "select manual_steps_desc from troubleshoot_steps where manual_id = '$manual_id';";  
 $result = mysqli_query($con,$sql_query); 
 $array = array();
 $index = 0;
 if(mysqli_num_rows($result) > 0 ) {  
	 while ($row = mysqli_fetch_assoc($result)) {
	 	$array[$index] = $row['manual_steps_desc'];
	 	$index++;
	 }
	 $manual_steps_desc = implode("$", $array);
	 echo $manual_steps_desc;
 }  
 else  
 {   
 echo "No cable box found.";  
 }  
 ?>  