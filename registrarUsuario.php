<?php include ('functions.php');

$cc=$_GET['cc'];

$nombre=$_GET['nombre'];

$apellido=$_GET['apellido'];

$telefono=$_GET['telefono'];

ejecutarSQLCommand("INSERT INTO `tablaprof`(`cc`, `nombre`, `apellido`, `telefono`) VALUES ('$cc','$nombre','$apellido','$telefono')
ON DUPLICATE KEY UPDATE `nombre`='$nombre',
`apellido`='$apellido',
`telefono`='$telefono'
;");

?>


