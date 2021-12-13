<?php

class ControllerUser {

  public static function userid() {
    echo Session::getUserId();
  }

  public static function username() {
    echo Session::getUsername();
  }

  public static function upd_username() {
    if (isset($_POST['name'])) {
      Session::setUsername($_POST['name']);
    }
    header('Location: ?');
  }

}