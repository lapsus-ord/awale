<div class="pseudo">
    <input type="text" id="name" required size="10" placeholder="Votre pseudo">
    <input type="submit" value="Envoyer">
</div>

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
    </div>
</div>

<script src="assets/scripts/settings.js" async></script>

<div class="title">
  <img class="logo" src="assets/img/logo.png" alt="Logo du site Awale"/>
  <h1>AWALE</h1>
</div>
<div class="main">
  <h2>Modes de jeu&nbsp;:</h2>
  <div class="separation-line"></div>
  <div class="buttons-box">
    <a href="?action=difficulty" class="default-button">Bot</a>
    <a href="?action=game" class="default-button">Joueur</a>
  </div>
</div>

<a  href="https://drive.google.com/file/d/1aqBA0kr1Xg6W7WW-s8DAKWTH6e3zbWjJ/view?usp=sharing" onclick="window.open(this.href); return false;">
    <img class="help-button" src="assets/img/icons/question.png" alt="Règles du jeu">
</a>
