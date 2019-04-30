<?php
/**
 * Created by PhpStorm.
 * User: Ichir
 * Date: 23.04.2019
 * Time: 16:59
 */

namespace Bookshop;


use Data\DataManager;

class AuthenticationManager extends BaseObject
{

    public static function authenticate(string $username, string $password) : bool {
        $user = DataManager::getUserByUserName($username);

        if ($user != null &&
            $user->getPasswordHash() == hash('sha1', "$username|$password")) {
            $_SESSION['user'] = $user->getId();
            return true;
        }

        self::signOut();
        return false;
    }

    public static function signOut()
    {

        unset($_SESSION['user']);

    }

    public static function isAuthenticated() : bool {
        return isset($_SESSION['user']);
    }

    public static function getAuthenticatedUser() {
        return self::isAuthenticated()
            ? DataManager::getUserById($_SESSION['user'])
            : null;
    }

}