package com.andry.consolemaze;

public class Goal extends Floor
{
    public Goal(int row, int column)
    {
        super(row, column);
    }

    public Goal(Position position)
    {
        super (position);
    }

    @Override
    public boolean isGameOver()
    {
        return true;
    }

    @Override
    public DrawType getDrawType()
    {
        return DrawType.GOAL;
    }
}
