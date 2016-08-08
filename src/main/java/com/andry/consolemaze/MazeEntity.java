package com.andry.consolemaze;

public abstract class MazeEntity
{
    private Position position;
    public enum DrawType
    {
        WALL,
        FLOOR,
        GOAL,
        HERO
    }
    public MazeEntity(int row, int column)
    {
        this(new Position(row, column));
    }

    public MazeEntity(Position position)
    {
        this.position = position;
    }

    public MazeEntity(MazeEntity entity)
    {
        this(entity.getPosition());
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }

    public abstract DrawType getDrawType();
}

