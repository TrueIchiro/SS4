<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 15:11
 */

declare(strict_types=1);

use Bookshop\SessionContext;

error_reporting(E_ALL);
ini_set('display_errors', 'On');

spl_autoload_register(function($class) {
   $filename = __DIR__ . '/../lib/' . $class . '.php';
   if (file_exists($filename)) {
       include($filename);
   }
});

SessionContext::create();

$class = 'mock';
require_once (__DIR__ . '/../lib/Data/DataManager_' . $class . '.php');