<?php
/**
 * Created by PhpStorm.
 * User: P26334
 * Date: 29.03.2019
 * Time: 15:36
 */

namespace Bookshop;


class Book extends Entity
{

    private $categoryId;
    private $title;
    private $author;
    private $price;

    /**
     * Book constructor.
     * @param $categoryId
     * @param $title
     * @param $author
     * @param $price
     */
    public function __construct(int $id, int $categoryId, string $title, string $author, float $price)
    {
        parent::__construct($id);
        $this->categoryId = $categoryId;
        $this->title = $title;
        $this->author = $author;
        $this->price = $price;
    }

    /**
     * @return int
     */
    public function getCategoryId(): int
    {
        return $this->categoryId;
    }

    /**
     * @return string
     */
    public function getTitle(): string
    {
        return $this->title;
    }

    /**
     * @return string
     */
    public function getAuthor(): string
    {
        return $this->author;
    }

    /**
     * @return float
     */
    public function getPrice(): float
    {
        return $this->price;
    }




}