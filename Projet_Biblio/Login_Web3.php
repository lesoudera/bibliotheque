<?php
try
{
	$bdd = new PDO('mysql:host=localhost;dbname=bibliothèque;charset=utf8', 'root', '', array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
}
catch (Exception $e)
{
        die('Erreur : ' . $e->getMessage());
}

$reponse = $bdd->query('SELECT IdUtilisateur_Utilisateur, Mot_De_Passe_Utilisateur FROM utilisateur WHERE IdUtilisateur_Utilisateur=2');

while ($donnees = $reponse->fetch())
{
   /* echo $donnees['IdUtilisateur_Utilisateur']; ?> <br/>
    <?php */ echo $donnees['Mot_De_Passe_Utilisateur'];

/*?>
    <p>
    <strong>Jeu</strong> : <?php echo $donnees['nom']; ?><br />
    Le possesseur de ce jeu est : <?php echo $donnees['possesseur']; ?>, et il le vend à <?php echo $donnees['prix']; ?> euros !<br />
    Ce jeu fonctionne sur <?php echo $donnees['console']; ?> et on peut y jouer à <?php echo $donnees['nbre_joueurs_max']; ?> au maximum<br />
    <?php echo $donnees['possesseur']; ?> a laissé ces commentaires sur <?php echo $donnees['nom']; ?> : <em><?php echo $donnees['commentaires']; ?></em>
   </p>
<?php */
}

$reponse->closeCursor(); // Termine le traitement de la requête

?>






