<?php
/**
 * Created by PhpStorm.
 * User: Ichir
 * Date: 23.04.2019
 * Time: 15:08
 */

namespace Bookshop;


class SessionContext extends BaseObject
{

    private static $exists = false;

    public static function create() : bool {
        if (!self::$exists) {
            self::$exists = session_start();
        }

        return self::$exists;
    }

}