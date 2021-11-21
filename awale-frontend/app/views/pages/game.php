<?PHP
header('Access-Control-Allow-Origin: *');
?>

<a href="./">
    <img class="home-button-top" src="assets/img/icons/maison-web.png" alt="home">
</a>

<button class="settings" id="btnPopup">
    <img class="settings-button" src="assets/img/icons/settings.png" alt="Paramètres du jeu">
</button>
<div id="overlay" class="overlay">
    <div id="popup" class="popup">
        <h2><span id="btnClose" class="btnClose">&times;</span> Paramètres <div class="spacer"> </div></h2>
        <div>
            <p class="settings-title">Réglages du son :</p>
            <div class="input-settings">
                <input type="range" id="volume" min="0" max="11">
            </div>
            <p class="settings-title">Choix thème sonore</p>
            <div class="input-settings">
                <select id="sound">
                    <option value="Caraïbes" selected>Caraibes</option>
                    <option value="Mali">Mali</option>
                    <option value="Rwanda">Rwanda</option>
                </select>
            </div>
        </div>
        <input id="btnSubmit" type="submit" value="Valider">
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
<a href="?action=result">
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
