<?PHP
header('Access-Control-Allow-Origin: *');
?>
<div class="settings">
    <img class="settings-button" src="assets/img/icons/settings.png" alt="Paramètres du jeu">
</div>
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


<a href="https://drive.google.com/file/d/1aqBA0kr1Xg6W7WW-s8DAKWTH6e3zbWjJ/view?usp=sharing">
    <img class="help-button" src="assets/img/icons/question.png" alt="Règles du jeu">
</a>

<script type="module" src="assets/scripts/sockjs.min.js"></script>
<script type="module" src="assets/scripts/stomp.min.js">
  <script type="module" src="assets/scripts/stomp.min.js.map"></script>
</script>
<script type="module" src="assets/scripts/game.js"></script>
