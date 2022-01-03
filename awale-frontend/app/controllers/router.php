<?php
require_once File::getApp(array("controllers", "Controller.php"));
require_once File::getApp(array("controllers", "ControllerUser.php"));

$action = $_GET['action'] ?? 'home';
$controller = $_GET['controller'] ?? '';
$controller_class = 'Controller' . ucfirst($controller);

if (class_exists($controller_class)) {
  if (method_exists($controller_class, $action))
    $controller_class::$action();
  else
    $controller_class::error();
}