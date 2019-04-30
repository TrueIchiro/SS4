<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 15:25
 */

namespace Bookshop;


class BaseObject
{

    public function __get($name)
    {
        throw new \Exception('Attribute ' . $name .
            ' is not declared');
    }

    public function __set($name, $value)
    {
        throw new \Exception('Attribute ' . $name .
            ' is not declared');
    }

    public function __call($name, $arguments)
    {
        throw new \Exception('Method ' . $name .
            ' is not declared');
    }

    public static function __callStatic($name, $arguments)
    {
        throw new \Exception('Static method ' . $name .
            ' is not declared');
    }
}