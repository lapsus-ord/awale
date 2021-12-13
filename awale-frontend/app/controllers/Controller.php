<?php

class Controller {

  public static function home(): void {
    $page_title = "Accueil";
    $view = "home";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function play(): void {
    if (!isset($_GET['gameId']))
      header('Location: ?action=play&gameId=' . uniqid());
    $page_title = "🆚 Joueur";
    $view = "game";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function difficulty(): void {
    $page_title = "Résultats";
    $view = "difficulty";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function list(): void {
    $page_title = "Liste des parties";
    $view = "listOfGames";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function result(): void {
    $page_title = "Résultats";
    $view = "result";
    require_once File::getApp(array("views", "view.php"));
  }

  public static function error(): void {
    $page_title = "Erreur";
    $view = "404";
    require_once File::getApp(array("views", "view.php"));
  }

}