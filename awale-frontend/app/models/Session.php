<?php

class Session {
  public static function initSession(): void {
    session_start();
    // Création du user_id
    if (!isset($_SESSION['user_id']))
      $_SESSION['user_id'] = uniqid("p_");

    // Création du tableau du panier
    if (!isset($_SESSION['username'])) {
      $_SESSION['username'] = 'anonymous';
    }
  }

  public static function resetSession(): void {
    session_destroy();
    self::initSession();
  }

  // --- Utilisateur ---
  public static function getUserId(): string {
    return $_SESSION['user_id'];
  }

  public static function getUsername(): string {
    return $_SESSION['username'];
  }

  public static function setUsername(string $name): void {
    $_SESSION['username'] = $name;
  }
}