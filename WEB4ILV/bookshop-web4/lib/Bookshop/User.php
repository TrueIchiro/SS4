<?php
/**
 * Created by PhpStorm.
 * User: Ichir
 * Date: 23.04.2019
 * Time: 16:56
 */

namespace Bookshop;


class User extends Entity
{

    private $userName;
    private $passwordHash;

    public function __construct(int $id, string $userName, string $passwordHash)
    {
        parent::__construct($id);
        $this->userName = $userName;
        $this->passwordHash = $passwordHash;
    }

    /**
     * @return string
     */
    public function getUserName(): string
    {
        return $this->userName;
    }

    /**
     * @return string
     */
    public function getPasswordHash(): string
    {
        return $this->passwordHash;
    }

}