<?php
/*
echo 'hello world!';

$str = 'string';

var_dump($str);

$num = "20";
$strnum = "$num test";
echo $strnum;
//echo $num + $strnum;

echo var_dump($num === $strnum) . '<br>';
echo var_dump($num == $strnum) . '<br>';

$arr = array("key" => "value", "test", 10 => 20, "test2");

var_dump($arr);

$arr = [
  "key" => "value",
  "test",
  10 => 20,
  "test2"
];

echo $arr["key"] . '<br>';

foreach ($arr as $item) {
    var_dump($item);
}

foreach ($arr as $key => $value) {
    echo $key . ' - ' . $value . '<br>';
}

function test(string $str) {
    var_dump($str);
}

test("das ist eine coole Applikation!");

//var_dump($_SERVER);
//var_dump($_REQUEST);
var_dump($_GET);
var_dump($_POST);
var_dump($_SESSION);
*/

require_once ('inc/bootstrap.php');

$default_view = 'welcome';
$view = $default_view;

//ini_set('session.use_cookies', 0);
//ini_set('session.use_only_cookies', 0);
//ini_set('session.use_trans_sid', 1);

//session_id($_GET['PHPSESSID']);
//session_start();

/*
if (!$_SESSION) {
    $_SESSION['test'] = 0;
} else {
    $_SESSION['test'] += 1;

    echo $_SESSION['test'];
}

*/

$postAction = isset($_REQUEST[\Bookshop\Controller::ACTION]) ?
    $_REQUEST[\Bookshop\Controller::ACTION] : null;

if ($postAction != null) {
    \Bookshop\Controller::getInstance()->invokePostAction();
}

if (isset($_REQUEST['view']) && file_exists(__DIR__ .
    '/views/' . $_REQUEST['view'] . '.php')) {
    $view = $_REQUEST['view'];
}

require_once ('views/' . $view . '.php');




?>

