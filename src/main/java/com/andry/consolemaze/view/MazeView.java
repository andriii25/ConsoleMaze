package com.andry.consolemaze.view;

import com.andry.consolemaze.model.Model;
import com.andry.consolemaze.Position;
import com.andry.consolemaze.entities.MazeEntity;

import java.awt.*;

import static com.andry.consolemaze.entities.MazeEntity.DrawType.WALL;

public abstract class MazeView
{
    private Model model;

    public static final Color WALL_COLOR = new Color(0xA0A0A0);
    public static final Color BACKGROUND = new Color(0x202020);
    public static final Color HERO_COLOR = new Color(0xFFFF00);
    public static final Color GOAL_COLOR = new Color(0x00FF00);

    public MazeView(Model model)
    {
        this.model = model;
    }

    public abstract void draw();
    public abstract void quit();

    protected Model getModel()
    {
        return model;
    }

    protected char countAdjacentWalls(MazeEntity entity)
    {
        char adjacentWalls = 0;
        Position position = entity.getPosition();
        if (position.getRow() > 0 && getModel().getMaze()[position.getRow() - 1][position.getColumn()].getDrawType() == WALL)
        {
            adjacentWalls |= 0b1000;
        }
        if (position.getRow() < (getModel().getRows() - 1) && getModel().getMaze()[position.getRow() + 1][position.getColumn()].getDrawType() == WALL)
        {
            adjacentWalls |= 0b0100;
        }
        if (position.getColumn() < (getModel().getColumns() - 1) && getModel().getMaze()[position.getRow()][position.getColumn() + 1].getDrawType() == WALL)
        {
            adjacentWalls |= 0b0010;
        }
        if (position.getColumn() > 0 && getModel().getMaze()[position.getRow()][position.getColumn()- 1].getDrawType() == WALL)
        {
            adjacentWalls |= 0b0001;
        }
        return adjacentWalls;
    }

}
