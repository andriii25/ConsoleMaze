package com.andry.consolemaze;

public abstract class Tile extends MazeEntity
{


    public Tile(int row, int column)
    {
        super(row, column);
    }

    public Tile(Position position)
    {
        super(position);
    }

    public abstract Direction onContact(Tile[][] maze, Direction characterDirection);

    public boolean canStep()
    {
        return false;
    }

    public boolean isGameOver()
    {
        return false;
    }


}
