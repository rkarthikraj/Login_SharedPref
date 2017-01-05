<?php
if($_GET['type']=="signup")
{
$id=$_GET['id'];
$name=$_GET['name'];
$uname=$_GET['uname'];
$pwd=$_GET['pwd'];
$email=$_GET['email'];

$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("LOGIN_DB",$con) or die("database error");
$query=mysql_query("insert into USER_CREDENTIALS values('','$name','$uname','$pwd','$email')");
$result1=@mysql_query("select * from USER_CREDENTIALS");
echo "<table border='2'>";
while($row=mysql_fetch_array($result1))
{
echo "<tr>";
echo "<td>",$row['ID'],"</td><td>",$row['NAME'],"</td><td>",$row['UNAME'],"</td><td>",$row['PWD'],"</td><td>",$row['EMAIL'],"</td>";
echo "</tr>";
}
echo "</table>";
}
else if($_GET['type']=="signin")
{
$uname=$_GET['uname'];
$pwd=$_GET['pwd'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("LOGIN_DB",$con) or die("database error");
$result=mysql_query("SELECT * FROM USER_CREDENTIALS WHERE UNAME='$uname' and PWD='$pwd'");
// Mysql_num_row is counting table row
$count=mysql_num_rows($result);
// If result matched $username and $password, table row must be 1 row
if ($count==1) 
	{
	$j=json_encode(array('output' => 'true'));
	echo $j;
	} 
else 
	{
    $j=json_encode(array('output' => 'false'));
	echo $j;
	}
}
else if($_GET['type']=="place")
{
$name=$_GET['name'];
$con=@mysql_connect("localhost","root","") or die("server error");
$db=mysql_select_db("LOGIN_DB",$con) or die("database error");
$query=mysql_query("insert into PLACE values('','$name')");
$result1=@mysql_query("select * from PLACE");
echo "<table border='2'>";
while($row=mysql_fetch_array($result1))
{
echo "<tr>";
echo "<td>",$row['ID'],"</td><td>",$row['NAME'],"</td>";
echo "</tr>";
}
echo "</table>";
}
?>