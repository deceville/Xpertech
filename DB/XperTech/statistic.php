 <?php  
 require "init.php";  
 $type = $_POST["type"];  
 $value = $_POST["value"]
 $id = $_POST["id"]
 $sql_query = "insert into stat_info values('$type','$value','$id');";   
 $result = mysqli_query($con,$sql_query);  
 ?> 