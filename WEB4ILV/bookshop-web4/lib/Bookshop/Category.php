<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 15:36
 */

namespace Bookshop;

class Category extends Entity
{
    private $name;

    public function __construct(int $id, string $name)
    {
        parent::__construct($id);
        $this->name = $name;
    }

    public function getName() : string {
        return $this->name;
    }
}

?>