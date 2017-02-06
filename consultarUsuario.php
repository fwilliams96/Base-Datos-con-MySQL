<?php include ('functions.php');

$cc=$_GET['cc'];

if ($resultset= getSQLResultSet("SELECT cc,nombre,apellido,telefono FROM `tablaprof` where cc='$cc'")) {

	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
		echo json_encode($row);
        }


}

?>

