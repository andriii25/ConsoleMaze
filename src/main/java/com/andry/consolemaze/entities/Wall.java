package com.andry.consolemaze.entities;

import com.andry.consolemaze.Direction;
import com.andry.consolemaze.Position;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;

public class Wall extends Tile
{
    private static final TextCharacter drawCharacter = new TextCharacter('#', TextColor.Factory.fromString("#A9A9A9"), TextColor.Factory.fromString("#696969"));

    public Wall(Position position)
    {
        super(position);
    }

    public Wall(int row, int column)
    {
        super(row, column);
    }

    public Direction onContact(Tile[][] maze, Direction characterDirection)
    {
        Position behindWall = new Position(getPosition(), characterDirection);
        if (!(behindWall.getRow() < 0 || behindWall.getRow() >= maze.length
                || behindWall.getColumn() < 0  || behindWall.getColumn() >= maze[0].length))
        {
            if (maze[behindWall.getRow()][behindWall.getColumn()].canStep())
            {
                Tile wall = maze[getPosition().getRow()][getPosition().getColumn()];
                Tile floor = maze[behindWall.getRow()][behindWall.getColumn()];
                Position temp = new Position(floor.getPosition());
                maze[wall.getPosition().getRow()][wall.getPosition().getColumn()] = floor;
                maze[wall.getPosition().getRow()][wall.getPosition().getColumn()].setPosition(wall.getPosition());

                maze[temp.getRow()][temp.getColumn()] = wall;
                maze[temp.getRow()][temp.getColumn()].setPosition(temp);
            }
        }

        return new Direction(Direction.Type.NONE);
    }

    @Override
    public DrawType getDrawType()
    {
        return DrawType.WALL;
    }
}
