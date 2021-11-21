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

<h1>Félicitations au Joueur X</h1>

<script src="assets/scripts/fireworks.js" async></script>

<a href="./">
    <img class="home-button" src="assets/img/icons/maison-web.png" alt="home">
</a>
