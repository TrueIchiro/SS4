<?php
/**
 * Created by PhpStorm.
 * User: Ichir
 * Date: 23.04.2019
 * Time: 15:13
 */

namespace Bookshop;

class ShoppingCart extends BaseObject
{

    private static function getCart() : array {
        return isset($_SESSION['cart']) ? $_SESSION['cart'] : array();
    }

    private static function storeCart(array $cart) {
        $_SESSION['cart'] = $cart;
    }

    public static function add(int $bookId) {
        $cart = self::getCart();
        $cart[$bookId] = $bookId;
        self::storeCart($cart);
    }

    public static function remove (int $bookId) {
        $cart = self::getCart();

        if (self::contains($bookId)) {
            if (($key = array_search($bookId, $cart)) !== false) {
                unset($cart[$key]);
            }
        }

        self::storeCart($cart);
    }

    public static function size() : int {
        return count(self::getCart());
    }

    public static function clear() {
        self::getCart(array());
    }

    public static function contains(int $bookId) : bool {
        //array_key_exists($needle, $haystack)
        return array_key_exists($bookId, self::getCart());
    }

    public static function getAll() : array {
        return self::getCart();
    }

}