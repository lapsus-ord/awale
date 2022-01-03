<?php

require_once "../app/lib/File.php";
require_once File::getApp(array("models", "Session.php"));
Session::initSession();
require_once File::getApp(array("controllers", "router.php"));