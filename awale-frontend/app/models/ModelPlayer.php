<?php

class ModelPlayer {
  private String $user_id;
  private String $username;

  public function __construct() {
    $this->user_id = uniqid("p_");
    $this->username = "Anonyme";
  }

  /**
   * @param String $username
   */
  public function setUsername(string $username): void {
    $this->username = $username;
  }

}