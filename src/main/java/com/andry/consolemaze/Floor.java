package com.andry.consolemaze;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;


public class Floor extends Tile
{
    private static final TextCharacter drawCharacter = new TextCharacter(' ', TextColor.ANSI.DEFAULT, TextColor.Factory.fromString("#696969"));

    public Floor(Position position)
    {
        super(position);
    }

    public Floor(int row, int column)
    {
        super(row, column);
    }

    public Direction onContact(Tile[][] maze, Direction characterDirection)
    {
        return characterDirection;
    }

    @Override
    public boolean canStep()
    {
        return true;
    }

    @Override
    public DrawType getDrawType()
    {
        return DrawType.FLOOR;
    }
}
