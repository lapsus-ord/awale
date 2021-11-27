<?php
require_once File::getApp(array("controllers", "ControllerPage.php"));

$action = $_GET['action'] ?? "home";
$controller = $_GET['controller'] ?? "page";
$controller_class = "Controller" . ucfirst($controller);

if (class_exists($controller_class)) {
  if (method_exists($controller_class, $action))
    $controller_class::$action();
  else
    $controller_class::error();
}