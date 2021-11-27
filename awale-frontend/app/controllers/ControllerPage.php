<?php

class ControllerPage {

  public static function home(): void {
    $page_title = "Accueil";
    $view = "home";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function play(): void {
    $page_title = "🆚 Joueur";
    $view = "game";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function result(): void {
    $page_title = "Résultats";
    $view = "result";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function difficulty(): void {
    $page_title = "Résultats";
    $view = "difficulty";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function error(): void {
    $page_title = "Erreur";
    $view = "404";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function hasAnUserId() {
    $_COOKIE["user_id"] ?? setcookie("user_id", uniqid("p_"), time() * 100, "/");
  }

}

ControllerPage::hasAnUserId();