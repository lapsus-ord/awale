<?php

class File {
  public static function build_path($path_array) {
    // $ROOT_FOLDER (sans slash à la fin) vaut
    // "/home/ann2/votre_login/public_html/TD5" à l'IUT
    $ROOT_FOLDER = __DIR__ . DIRECTORY_SEPARATOR . '..' . DIRECTORY_SEPARATOR . '..';
    return $ROOT_FOLDER . '/' . join('/', $path_array);
  }
}