<?php

class File {
    private const DS = DIRECTORY_SEPARATOR;
    private const APP_FOLDER = __DIR__ . self::DS . '..' . self::DS;
    private const ROOT_FOLDER = self::APP_FOLDER . '..' . self::DS;
    private const PUBLIC_FOLDER = self::ROOT_FOLDER . 'public' . self::DS;

    public static function getApp(array $path_array) {
        return self::APP_FOLDER . join(self::DS, $path_array);
    }

    public static function getRoot(array $path_array) {
        return self::ROOT_FOLDER . join(self::DS, $path_array);
    }

    public static function getPublic(array $path_array) {
        return self::PUBLIC_FOLDER . join(self::DS, $path_array);
    }
}