package com.andry.consolemaze.view;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import com.andry.consolemaze.Direction;
import com.andry.consolemaze.entities.Hero;
import com.andry.consolemaze.model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

public class SwingView extends MazeView
{
    private AsciiPanel asciiPanel;
    private JFrame mazeFrame;

    public SwingView(Model model, KeyListener listener)
    {
        super(model);
        mazeFrame = new JFrame();
        asciiPanel = new AsciiPanel(getModel().getColumns(), getModel().getRows(), AsciiFont.QBICFEET_10x10);
        mazeFrame.add(asciiPanel);
        mazeFrame.addKeyListener(listener);
        mazeFrame.pack();
        mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mazeFrame.setVisible(true);
    }



    @Override
    public void draw()
    {

        for (int i = 0; i < getModel().getRows(); i++)
        {
            for (int j = 0; j < getModel().getColumns(); j++)
            {
                char displayCharacter;
                Color foreground;
                Color background = BACKGROUND;
                switch (getModel().getMaze()[i][j].getDrawType())
                {
                    case FLOOR:
                        displayCharacter = ' ';
                        foreground = WALL_COLOR;
                        break;
                    case GOAL:
                        displayCharacter = 'X';
                        foreground = GOAL_COLOR;
                        break;
                    case WALL:
                        foreground = WALL_COLOR;
                        char adjacentWalls = countAdjacentWalls(getModel().getMaze()[i][j]);
                        switch (adjacentWalls)
                        {
                            case 0b0000:
                                displayCharacter = 206;//(char) 0x256C;
                                break;
                            case 0b0001:
                                displayCharacter = 205;//(char) 0x2550;
                                break;
                            case 0b0010:
                                displayCharacter = 205;//(char) 0x2550;
                                break;
                            case 0b0011:
                                displayCharacter = 205;//(char) 0x2550;
                                break;
                            case 0b0100:
                                displayCharacter = 186;//(char) 0x2551;
                                break;
                            case 0b0101:
                                displayCharacter = 187;//(char) 0x2557;
                                break;
                            case 0b0110:
                                displayCharacter = 201;//(char) 0x2554;
                                break;
                            case 0b0111:
                                displayCharacter = 203;//(char) 0x2566;
                                break;
                            case 0b1000:
                                displayCharacter = 186;//(char) 0x2551;
                                break;
                            case 0b1001:
                                displayCharacter = 188;//(char) 0x255D;
                                break;
                            case 0b1010:
                                displayCharacter = 200;//(char) 0x255A;
                                break;
                            case 0b1011:
                                displayCharacter = 202;//(char) 0x2569;
                                break;
                            case 0b1100:
                                displayCharacter = 186;//(char) 0x2551;
                                break;
                            case 0b1101:
                                displayCharacter = 185;//(char) 0x2563;
                                break;
                            case 0b1110:
                                displayCharacter = 204;//(char) 0x2560;
                                break;
                            case 0b1111:
                                displayCharacter = 206;//(char) 0x256C;
                                break;
                            default:
                                displayCharacter = '?';
                        }
                        break;
                    default:
                        foreground = null;
                        displayCharacter = '?';

                }
                asciiPanel.write(displayCharacter, j, i, foreground, background);
            }
        }

        Hero hero = getModel().getHero();
        Direction heroDirection = hero.getDirection();
        char displayCharacter;
        Color foreground = HERO_COLOR;
        Color background = BACKGROUND;
        if (heroDirection.equals(new Direction(Direction.Type.NORTH)))
        {
            displayCharacter = '^';
        } else if (heroDirection.equals(new Direction(Direction.Type.SOUTH)))
        {
            displayCharacter = 'v';
        } else if (heroDirection.equals(new Direction(Direction.Type.EAST)))
        {
            displayCharacter = '>';
        } else if (heroDirection.equals(new Direction(Direction.Type.WEST)))
        {
            displayCharacter = '<';
        }
        else
        {
            displayCharacter = '?';
        }
        asciiPanel.write(displayCharacter, hero.getPosition().getColumn(), hero.getPosition().getRow(), foreground, background);
        if (getModel().isGameOver())
        {
            if (!getModel().getHero().isDead())
            {
                asciiPanel.writeCenter("You won!", getModel().getRows() / 2, Color.red, BACKGROUND);
            }
        }
        mazeFrame.repaint();
    }

    @Override
    public void quit()
    {
        mazeFrame.dispatchEvent(new WindowEvent(mazeFrame, WindowEvent.WINDOW_CLOSING));
    }
}