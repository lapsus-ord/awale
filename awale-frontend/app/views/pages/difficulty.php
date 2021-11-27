<button class="settings" id="btnPopup">
  <img class="settings-button" src="assets/img/icons/settings.png" alt="Paramètres du jeu">
</button>
<div id="overlay" class="overlay">
  <div id="popup" class="popup">
    <h2><span id="btnClose" class="btnClose">&times;</span> Paramètres
      <div class="spacer"></div>
    </h2>
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

<div class="title">
  <img class="logo" src="assets/img/logo.png" alt="Logo du site Awale"/>
  <h1>AWALE</h1>
</div>
<div class="main">
  <h2>Niveau du BOT &nbsp;:</h2>
  <div class="separation-line"></div>
  <div class="buttons-box">
    <a style="background-color: green" href="?action=play&level=0" class="default-button bot-button">Facile</a>
    <a style="background-color: blue" href="?action=play&level=1" class="default-button bot-button">Moyen</a>
  </div>
  <div class="buttons-box">
    <a style="background-color: orange" href="?action=play&level=2" class="default-button bot-button">Fort</a>
    <a style="background-color: red" href="?action=play&level=3" class="default-button bot-button">Impossible</a>
  </div>
</div>

<a href="?action=home">
  <img class="home-button" src="assets/img/icons/maison-web.png" alt="home">
</a>
