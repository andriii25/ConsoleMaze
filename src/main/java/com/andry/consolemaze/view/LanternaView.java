package com.andry.consolemaze.view;

import com.andry.consolemaze.Direction;
import com.andry.consolemaze.model.Model;
import com.andry.consolemaze.Position;
import com.andry.consolemaze.entities.Hero;
import com.andry.consolemaze.entities.MazeEntity;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class LanternaView extends MazeView
{
    private TerminalScreen screen;

    private static final TextColor BACKGROUND = TextColor.Factory.fromString("#202020");
    private static final TextColor WALL_COLOR = TextColor.Factory.fromString("#A0A0A0");
    private static final TextColor HERO_COLOR = TextColor.Factory.fromString("#FFFF00");

    public LanternaView(TerminalScreen terminalScreen, Model model)
    {
        super(model);
        screen = terminalScreen;
        try
        {
            screen.startScreen();
            //Why is this the way to hide the cursor???
            screen.setCursorPosition(null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void draw ()
    {
        if (getModel().isGameOver())
        {
            if (!getModel().getHero().isDead())
            {
                TextGraphics textGraphics = screen.newTextGraphics();
                textGraphics.fillRectangle(new TerminalPosition(5, 10), new TerminalSize(21, 5), new TextCharacter(' ', WALL_COLOR, TextColor.ANSI.CYAN));
                textGraphics.putString(12, 12, "YOU WON!");
            }


        }
        for (int i = 0; i < getModel().getRows(); i++)
        {
            for (int j = 0; j < getModel().getColumns(); j++)
            {
                TextCharacter character = toTextCharacter(getModel().getMaze()[i][j]);
                screen.setCharacter(j, i, character);
            }
        }

        Hero hero = getModel().getHero();
        Position heroPosition = hero.getPosition();
        TextCharacter heroCharacter = toTextCharacter(hero);
        screen.setCharacter(heroPosition.getColumn(), heroPosition.getRow(), heroCharacter);
        try
        {
            screen.refresh(Screen.RefreshType.AUTOMATIC);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private TextCharacter toTextCharacter(MazeEntity entity)
    {
        switch (entity.getDrawType())
        {
            case FLOOR:
                return new TextCharacter(' ', TextColor.ANSI.DEFAULT, BACKGROUND);
            case GOAL:
                return new TextCharacter('X', TextColor.Factory.fromString("#00FF00"), BACKGROUND);
            case WALL:
                char adjacentWalls = countAdjacentWalls(entity);
                switch (adjacentWalls)
                {
                    case 0b0000:
                        return new TextCharacter((char)0x256C, WALL_COLOR, BACKGROUND);
                    case 0b0001:
                        return new TextCharacter((char)0x2550, WALL_COLOR, BACKGROUND);
                    case 0b0010:
                        return new TextCharacter((char)0x2550, WALL_COLOR, BACKGROUND);
                    case 0b0011:
                        return new TextCharacter((char)0x2550, WALL_COLOR, BACKGROUND);
                    case 0b0100:
                        return new TextCharacter((char)0x2551, WALL_COLOR, BACKGROUND);
                    case 0b0101:
                        return new TextCharacter((char)0x2557, WALL_COLOR, BACKGROUND);
                    case 0b0110:
                        return new TextCharacter((char)0x2554, WALL_COLOR, BACKGROUND);
                    case 0b0111:
                        return new TextCharacter((char)0x2566, WALL_COLOR, BACKGROUND);
                    case 0b1000:
                        return new TextCharacter((char)0x2551, WALL_COLOR, BACKGROUND);
                    case 0b1001:
                        return new TextCharacter((char)0x255D, WALL_COLOR, BACKGROUND);
                    case 0b1010:
                        return new TextCharacter((char)0x255A, WALL_COLOR, BACKGROUND);
                    case 0b1011:
                        return new TextCharacter((char)0x2569, WALL_COLOR, BACKGROUND);
                    case 0b1100:
                        return new TextCharacter((char)0x2551, WALL_COLOR, BACKGROUND);
                    case 0b1101:
                        return new TextCharacter((char)0x2563, WALL_COLOR, BACKGROUND);
                    case 0b1110:
                        return new TextCharacter((char)0x2560, WALL_COLOR, BACKGROUND);
                    case 0b1111:
                        return new TextCharacter((char)0x256C, WALL_COLOR, BACKGROUND);
                    default:
                        return null;
                }
            case HERO:
                Hero hero = getModel().getHero();
                Direction heroDirection = hero.getDirection();
                if (heroDirection.equals(new Direction(Direction.Type.NORTH)))
                {
                    return new TextCharacter((char)0x02C4, HERO_COLOR, BACKGROUND);
                }
                if (heroDirection.equals(new Direction(Direction.Type.SOUTH)))
                {
                    return new TextCharacter((char)0x02C5, HERO_COLOR, BACKGROUND);
                }
                if (heroDirection.equals(new Direction(Direction.Type.EAST)))
                {
                    return new TextCharacter((char)0x02C3, HERO_COLOR, BACKGROUND);
                }
                if (heroDirection.equals(new Direction(Direction.Type.WEST)))
                {
                    return new TextCharacter((char)0x02C2, HERO_COLOR, BACKGROUND);
                }
            default:
                return null;
        }
    }

    @Override
    public void quit()
    {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.putString(0, 23, "Press any key to exit...");
        try
        {
            screen.refresh();
            screen.readInput();
            screen.stopScreen();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
