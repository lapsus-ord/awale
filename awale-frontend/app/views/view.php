<!DOCTYPE html>
<html lang="fr">
  <?php require_once File::build_path(array("app", "views", "templates", "header.php")); ?>

  <body>
    <div class="container">
      <?php
      $filepath = File::build_path(array("app", "views", $controller, "$view.php"));
      require_once $filepath;
      ?>
    </div>
    <?php require_once File::build_path(array("app", "views", "templates", "footer.php")); ?>
  </body>
</html>