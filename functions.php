<?php 
header( 'Content-Type: text/html;charset=utf-8' );


function ejecutarSQLCommand($Commando){

  $mysqli = new mysqli("localhost", "root", "", "dbprof");

/* check connection */
if ($mysqli->connect_errno) {
    printf("Connect failed: %sn", $mysqli->connect_error);
    echo "error";
    exit();
}
echo $Commando;
if ($mysqli->multi_query($Commando)) {
     echo "listo";
     if ($resultset = $mysqli->store_result()) {
	echo "entre";
        while ($row = $resultset->fetch_array(MYSQLI_BOTH)) {

        }
        $resultset->free();
     }


}


$mysqli->close();
}

function getSQLResultSet($Commando){


  $mysqli = new mysqli("localhost", "root", "", "dbprof");

/* check connection */
if ($mysqli->connect_errno) {
    printf("Connect failed: %sn", $mysqli->connect_error);
    exit();
}

if ( $mysqli->multi_query($Commando)) {
    return $mysqli->store_result();




}



$mysqli->close();
}


?>
