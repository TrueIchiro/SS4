<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 15:32
 */

namespace Bookshop;

interface IData {
    public function getId() : int;
}

class Entity extends BaseObject implements IData
{
    private $id;

    public function __construct(int $id)
    {
        $this->id = $id;
    }

    public function getId(): int
    {
        return $this->id;
    }
}