package com.andry.consolemaze;

public class Direction
{
    private int directionRow;
    private int directionColumn;

    public enum Type { NORTH, SOUTH, EAST, WEST, NONE}

    public Direction(Direction.Type type)
    {
        switch (type)
        {
            case NORTH:
                directionRow = -1;
                directionColumn = 0;
                break;
            case SOUTH:
                directionRow = 1;
                directionColumn = 0;
                break;
            case EAST:
                directionRow = 0;
                directionColumn = 1;
                break;
            case WEST:
                directionRow = 0;
                directionColumn = -1;
                break;
            case NONE:
                directionRow = 0;
                directionColumn = 0;
        }
    }

    public Direction(int deltaRow, int deltaColumn)
    {
        directionRow = deltaRow;
        directionColumn = deltaColumn;
    }

    public int getRowDirection()
    {
        return directionRow;
    }

    public int getColumnDirection()
    {
        return directionColumn;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Direction direction = (Direction) o;

        return directionRow == direction.directionRow && directionColumn == direction.directionColumn;

    }

    @Override
    public int hashCode()
    {
        int result = directionRow;
        result = 31 * result + directionColumn;
        return result;
    }
}
