<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 16:22
 */

namespace Bookshop;


class Util extends BaseObject
{
    public static function escape(string $string) {
        return nl2br(htmlentities($string));
    }

    public static function action(string $action, array $params = null) : string {

        $page = isset($_REQUEST[Controller::PAGE])
            ? $_REQUEST[Controller::PAGE] : $_SERVER['REQUEST_URI'];

        $res = 'index.php?' . Controller::ACTION . '=' . rawurlencode($action) . '&' .
            Controller::PAGE . '=' . rawurlencode($page);

        if (is_array($params)) {
            foreach($params as $name=>$value) {
                $res .= '&' . rawurlencode($name) . '=' . rawurlencode($value);
            }
        }

        return $res;

    }

    public static function redirect(String $page = null)
    {

        if ($page == null) {
            $page = isset($_REQUEST[Controller::PAGE]) ?
                $_REQUEST[Controller::PAGE] :
                $_SERVER['REQUEST_URI'];
        }

        header("Location: $page");
        exit();
    }

}