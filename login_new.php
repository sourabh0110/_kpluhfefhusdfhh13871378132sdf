<?php
 
if($_SERVER['REQUEST_METHOD']=='POST'){
$username = $_POST['adm_username'];
$password = $_POST['adm_password'];
require_once('init.php');
$sql = "SELECT * FROM admin_users WHERE adm_username = '$username' AND adm_password='$password'";
//$sql = "SELECT * FROM user_info WHERE username = '$username' AND password='$password'";
$result = mysqli_query($con,$sql);
$check = mysqli_fetch_array($result);

if(isset($check)){
echo 'success';
}else{
echo 'failure';
}
}



?>