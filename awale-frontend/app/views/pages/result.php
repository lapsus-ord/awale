<h1>Félicitations au Joueur <?= $_GET['winner']; ?></h1>

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
