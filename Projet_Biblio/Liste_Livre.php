<html>
<head>
<title>Liste des produits</title>
</head>
<body bgcolor="#ffffff">
<table  width="95%" border="1" cellspacing="0" cellpadding="4">
<?php
    $user="root";
	$pass="";
	try {
        $bdd = new PDO('mysql:host=localhost;dbname=bibliothèque;charset=utf8', 'root', '', array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
	$requete="select * from livre";
    foreach($bdd->query($requete) as $row) {
         echo '<tr>';
            echo '<td>'.$row[""].'</td>';
            /*echo '<td>'.$row["pdt_ref"].'</td>';
            echo '<td>'.$row["pdt_designation"].'</td>';
            echo '<td align="right">'.$row["pdt_prix"].' �</td>';*/
            echo '</tr>';
    }
    //$dbh = null;
} catch (PDOException $e) {
    print "Erreur !: " . $e->getMessage() . "<br/>";
    die();
}

?>
</table>
            </body>
</html>
