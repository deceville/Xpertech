<?php  
 require "init.php";  
 $selfinstall_id = $_POST["selfinstall_id"];
 $box_id = $_POST["box_id"];
 $sql_query = "select title from selfinstall_steps_title where selfinstall_id = '$selfinstall_id' AND box_id = '$box_id';";
 $result = mysqli_query($con,$sql_query); 
 $array = array();
 $index = 0;
 if(mysqli_num_rows($result) > 0 ) {  
	 while ($row = mysqli_fetch_assoc($result)) {
	 	$array[$index] = $row['title'];
	 	$index++;
	 }
	 $install_steps_desc = implode("$", $array);
	 echo $install_steps_desc;
 }  
 else  
 {   
 echo "No cable box found.";  
 }  
 ?>  