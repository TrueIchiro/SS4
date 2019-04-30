<?php
/**
 * Created by PhpStorm.
 * User: Ichir
 * Date: 23.04.2019
 * Time: 15:39
 */

namespace Bookshop;

//Singleton pattern
use Data\DataManager;
use mysql_xdevapi\Exception;

class Controller extends BaseObject
{

    const ACTION = 'action';
    const PAGE = 'page';
    const ACTION_ADD = 'addToCart';
    const ACTION_REMOVE = 'removeFromCart';
    const ACTION_LOGIN = 'login';
    const ACTION_LOGOUT = 'logout';
    const USER_NAME = 'username';
    const USER_PASSWORD = 'password';
    const ACTION_ORDER = 'placeOrder';
    const CC_NAME = 'nameOnCard';
    const CC_NUMBER = 'cardNumber';

    // Hold the class instance.
    private static $instance = null;

    // The constructor is private
    // to prevent initiation with outer code.
    private function __construct()
    {
        // The expensive process (e.g.,db connection) goes here.
    }

    // The object is created from within the class itself
    // only if the class has no instance.
    public static function getInstance()
    {
        if (self::$instance == null)
        {
            self::$instance = new Controller();
        }

        return self::$instance;
    }

    public function invokePostAction() {

        if ($_SERVER['REQUEST_METHOD'] != 'POST') {
            throw new \Exception('Controller can only handle POST requests.');
        } else if (!isset($_REQUEST[self::ACTION])) {
            throw new \Exception(self::ACTION . 'not specified.');
        }

        $action = $_REQUEST[self::ACTION];

        switch ($action) {
            case self::ACTION_ADD:
                ShoppingCart::add((int) $_REQUEST['bookId']);
                Util::redirect();
                break;
            case self::ACTION_REMOVE:
                ShoppingCart::remove((int) $_REQUEST['bookId']);
                Util::redirect();
                break;
            case self::ACTION_LOGIN:
                if (!AuthenticationManager::authenticate(
                    $_REQUEST[self::USER_NAME], $_REQUEST[self::USER_PASSWORD]
                )) {
                    $this->forwardRequest(array('Invalid username or password.'));
                    break;
                }
                Util::redirect();
                break;
            case self::ACTION_LOGOUT:
                AuthenticationManager::signOut();
                Util::redirect();
                break;
            case self::ACTION_ORDER:
                $user = AuthenticationManager::getAuthenticatedUser();

                if ($user == null) {
                    $this->forwardRequest(array('Not logged in.'));
                    break;
                }

                if ($this->processCheckout($_REQUEST[self::CC_NAME], $_REQUEST[self::CC_NUMBER])) {
                    break;
                }

                break;
            default:
                throw new \Exception('Unknown Controller action ' . $action);
        }

    }

    private function forwardRequest(array $errors, string $target = null)
    {

        if ($target == null) {
            if (!isset($_REQUEST[self::PAGE])) {
                throw new \Exception('Missing target for forward.');
            }

            $target = $_REQUEST[self::PAGE];
        }

        if (count($errors) > 0) {
            $target .= '&errors=' . urlencode(serialize($errors));
        }

        header("Location: $target");

        exit();

    }

    private function processCheckout(string $nameOnCard, string $cardNumber) : bool {
        $errors = array();

        $nameOnCard = trim($nameOnCard);

        if ($nameOnCard == null || strlen($nameOnCard) == 0) {
            $errors[] = 'Invalid name on card.';
        }

        if ($cardNumber == null
            || strlen($cardNumber) != 16
            || !ctype_digit($cardNumber)) {
            $errors[] = 'Invalid card number. Card number has to be 16 digits long.';
        }

        if (count($errors) >= 0) {
            $this->forwardRequest($errors);
            return false;
        }

        if (ShoppingCart::size() == 0) {
            $this->forwardRequest(array('No items in cart.'));
            return false;
        }

        $user = AuthenticationManager::getAuthenticatedUser();
        $orderId = DataManager::createOrder($user->getId(),
            ShoppingCart::getAll(),
            $nameOnCard,
            $cardNumber);

        if (!$orderId) {
            $this->forwardRequest(array('Could not create order.'));
        }

        ShoppingCart::clear();

        Util::redirect('index.php?view=success&orderId=' . rawurlencode($orderId));

        return true;
    }

}