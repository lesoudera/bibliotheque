<html>
<head>
<title>menu</title>
</head>
<body bgcolor="#ffffff">
<h2>St� Lafleur</h2>
<p /><a href="logo.htm" target="page">Accueil</a>
<hr />
<u><b>Nos produits</b></u>
<?php
    
	$user="root";
	$pass="";
	try {
        $bdd = new PDO('mysql:host=localhost;dbname=bibliothèque;charset=utf8', 'root', '', array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
    foreach($bdd->query('select * from categorie') as $row) {
        echo '<p /><a href="listePdt.php?categ='.$row["cat_code"].'" target="page">'.$row["cat_libelle"]."</a>";
    }
    $dbh = null;
} catch (PDOException $e) {
    print "Erreur !: " . $e->getMessage() . "<br/>";
    die();
}

?>
<form action="panier.php" target="menu" method="get">
<input type="submit" name="action" value="Vider le panier" />
</form>

<form action="commande.php" target="page" method="get">
<p><input type="submit" value="Commander" />
</form>
<p /><a href="mailto:commercial@lafleur.com" target="page">Nous contacter</a>
</body>
</html>
