<?php

if (isset($_GET['action'])) {
  // On récupère l'action passée dans l'URL
  $action = $_GET['action'];

  if (file_exists(File::build_path(array("app", "views", "pages", $action . ".php")))) {
    $controller = "pages";
    $view = $action;
    require_once File::build_path(array("app", "views", "view.php"));
  } else {
    $controller = "";
    $view = "404";
    require_once File::build_path(array("app", "views", "view.php"));
  }
} else {
  $controller = "pages";
  $view = "home";
  require_once File::build_path(array("app", "views", "view.php"));
}