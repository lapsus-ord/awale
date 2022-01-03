<div class="title">
	<img src="assets/img/icons/user-circle-white.svg" alt="Image de profil" width="64" style="fill: #fff;"/>
  <h2>Votre pseudo : <?= htmlspecialchars(Session::getUsername()) ?></h2>
</div>
<div class="main">
  <div class="title" style="display: flex; align-items: center; gap: 100px">
    <h2>Vos parties :</h2>
  </div>
  <div class="separation-line"></div>
  <div id="list-of-games"></div>
</div>
<script type="module" src="assets/scripts/profile.js"></script>
