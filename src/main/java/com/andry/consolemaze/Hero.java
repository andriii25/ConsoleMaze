package com.andry.consolemaze;


public class Hero extends MazeEntity
{
    private Direction direction;

    private boolean isDead;

    public Hero(Position playerPosition, Direction playerDirection)
    {
        super(playerPosition);
        direction = playerDirection;
        isDead = false;
    }

    public boolean turn(Direction newDirection)
    {
        if (newDirection.equals(direction))
        {
            return false;
        }
        direction = newDirection;
        return true;
    }

    public void step(Direction stepDirection)
    {
        getPosition().translate(stepDirection);
    }

    public Direction getDirection()
    {
        return direction;
    }

    public boolean isDead()
    {
        return isDead;
    }

    @Override
    public DrawType getDrawType()
    {
        return DrawType.HERO;
    }
}
