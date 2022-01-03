<div class="main">
  <?php if ($_POST['result'] != 'draw') { ?>
    <h2>Félicitations <?= htmlspecialchars($_POST['result']) ?> ! 🎉</h2>
  <?php } else { ?>
    <h2>Bravo vous êtes ex aequo ! 👑</h2>
  <?php } ?>
  <div class="separation-line"></div>
  <h2><?= htmlspecialchars($_POST['player1']) ?></h2>
  <h2><?= htmlspecialchars($_POST['player2']) ?></h2>
</div>

<script src="assets/scripts/confettis.js"></script>
<script>
  const start = () => {
    setTimeout(function () {
      confetti.start()
    }, 1000);
  };
  const stop = () => {
    setTimeout(function () {
      confetti.stop()
    }, 8000);
  };
  start();
  stop();
</script>
