<?php  
 require "init.php";  
 $box_number = $_POST["box_number"];
 $sql_query = "select package_number,package_name,package_numOfChannels from packages where box_number = '$box_number';";  
 $result = mysqli_query($con,$sql_query); 
 $array = array();
 $index = 0;
 if(mysqli_num_rows($result) > 0 ) {  
	 while ($row = mysqli_fetch_assoc($result)) {
	 	$array[$index] = $row['package_name'];
	 	$index++;
	 }
	 $package_title = implode("$", $array);
	 echo $package_title;
 }  
 else  
 {   
 echo "No cable box found.";  
 }  
 ?>  