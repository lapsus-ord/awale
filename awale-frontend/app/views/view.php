<!DOCTYPE html>
<html lang="fr">
  <?php require_once File::getApp(array("views", "templates", "header.php")); ?>

  <body>
    <div class="container">
      <?php
      require_once File::getApp(array("views", "pages", "$view.php"));
      ?>
    </div>
    <?php require_once File::getApp(array("views", "templates", "toolbar.php")); ?>
    <?php require_once File::getApp(array("views", "templates", "footer.php")); ?>
  </body>
</html>