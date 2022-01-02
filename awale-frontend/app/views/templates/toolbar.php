<aside id="toolbar">
  <div class="button-group">
    <a href="?action=home">
      <img class="home-button-top" src="assets/img/icons/maison-web.png" alt="home">
    </a>
    <?php if ($view == 'home') { ?>
      <form action="?action=upd_username&controller=user" method="post">
        <input type="text" name="name" maxlength="10" placeholder="Votre pseudo" style="width: 120px" required/>
        <input type="submit" value="Enregistrer"/>
      </form>
    <?php } ?>
  </div>


  <div class="button-group">
    <a href="https://drive.google.com/file/d/1aqBA0kr1Xg6W7WW-s8DAKWTH6e3zbWjJ/view?usp=sharing"
       onclick="window.open(this.href); return false;"><img class="help-button" src="assets/img/icons/question.png"
                                                            alt="Règles du jeu"></a>
    <button class="settings" id="btnPopup">
      <img class="settings-button" src="assets/img/icons/settings.png" alt="Paramètres du jeu"/>
    </button>
    <div class="center modal">
      <div id="settings-modal">
        <h2>Paramètres&nbsp;<span id="btnClose">&times;</span></h2>
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
          <input id="soundSubmit" type="submit" value="Enregistrer">
        </div>
      </div>
    </div>
  </div>
  <script src="assets/scripts/settings.js" async></script>
</aside>