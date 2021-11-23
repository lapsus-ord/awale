<?PHP
header('Access-Control-Allow-Origin: *');
?>

<a href="?action=home">
    <img class="home-button-top" src="assets/img/icons/maison-web.png" alt="home">
    <script>
        window.onbeforeunload = function confirmation(){
            return 'Voulez-vous bien quitter la partie ?';
        };
    </script>
</a>

<button class="settings" id="btnPopup">
    <img class="settings-button" src="assets/img/icons/settings.png" alt="Paramètres du jeu">
</button>
<div id="overlay" class="overlay">
    <div id="popup" class="popup">
        <h2><span id="btnClose" class="btnClose">&times;</span> Paramètres <div class="spacer"> </div></h2>
        <div class="border-settings">
            <p class="settings-title">Réglages du son :</p>
            <div class="input-settings">
                <input type="range" id="volume" min="0" max="10" value="0">
            </div>
            <p class="settings-title">Choix thème sonore</p>
            <div class="input-settings">
                <select id="music">
                    <option value="Rwanda" selected>Rwanda</option>
                    <option value="Mali">Mali</option>
                    <option value="Égypte">Egypte</option>
                </select>
            </div>
        </div>
        <audio style="display: none" src="assets/sounds/Rwanda.mp3" id="audio" loop="true">
            Votre navigateur ne supporte pas la balise audio.
        </audio>
        <div class="input-settings">
            <input type="submit" value="Enregistrer" id="submit">
        </div>
    </div>
</div>

<script src="assets/scripts/settings.js" async></script>

<h2>En attente d'un autre joueur… / En jeu</h2>
<br>
<div class="board">
  <div class="ennemy row">
    <div class="cell-0 a">4</div>
    <div class="cell-1">4</div>
    <div class="cell-2">4</div>
    <div class="cell-3">4</div>
    <div class="cell-4">4</div>
    <div class="cell-5">4</div>
  </div>
  <div class="user row">
    <div class="cell-0">4</div>
    <div class="cell-1">4</div>
    <div class="cell-2">4</div>
    <div class="cell-3">4</div>
    <div class="cell-4">4</div>
    <div class="cell-5">4</div>
  </div>
</div>
<br>
<a href="?action=result&winner=XXX">
    <input type="submit" value="Finir partie">
</a>


<a href="https://drive.google.com/file/d/1aqBA0kr1Xg6W7WW-s8DAKWTH6e3zbWjJ/view?usp=sharing">
    <img class="help-button" src="assets/img/icons/question.png" alt="Règles du jeu">
</a>

<script type="module" src="assets/scripts/sockjs.min.js"></script>
<script type="module" src="assets/scripts/stomp.min.js">
  <script type="module" src="assets/scripts/stomp.min.js.map"></script>
</script>
<script type="module" src="assets/scripts/game.js"></script>
