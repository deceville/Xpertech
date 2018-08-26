<?php  
 require "init.php";  
 $accountNumber = $_POST["accountNumber"];
 $sql_query = "select ownership_id, box_number from ownership where ownership_id = '$accountNumber';";  
 $result = mysqli_query($con,$sql_query);  
 if(mysqli_num_rows($result) >0 )  
 {  
 $row = mysqli_fetch_assoc($result);  
 $ownership_id =$row["ownership_id"];  
 $box_number =$row["box_number"];  
 echo $box_number.",".$ownership_id;  
 }  
 else  
 {   
 echo "Invalid QR code. Please try again.";  
 }  
 ?>  