package com.andry.consolemaze.controller;

import com.andry.consolemaze.Direction;
import com.andry.consolemaze.model.Model;
import com.andry.consolemaze.view.LanternaView;
import com.andry.consolemaze.view.MazeView;
import com.andry.consolemaze.view.SwingView;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class MazeController
{
    private Terminal terminal;
    private MazeView mazeView;

    private Model model;

    private boolean isGameOver;

    //AsciiPanel input
    class MazeListener implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e)
        {

        }

        @Override
        public void keyPressed(KeyEvent e)
        {
            if (isGameOver)
            {
                quit();
            }
            Direction playerDirection;
            switch (e.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    playerDirection = new Direction(Direction.Type.NORTH);
                    break;
                case KeyEvent.VK_DOWN:
                    playerDirection = new Direction(Direction.Type.SOUTH);
                    break;
                case KeyEvent.VK_RIGHT:
                    playerDirection = new Direction(Direction.Type.EAST);
                    break;
                case KeyEvent.VK_LEFT:
                    playerDirection = new Direction(Direction.Type.WEST);
                    break;
                default:
                    playerDirection = new Direction(Direction.Type.NONE);
                    break;

            }
            model.step(playerDirection);
            mazeView.draw();
            isGameOver = model.isGameOver();
        }

        @Override
        public void keyReleased(KeyEvent e)
        {

        }
    }

    public MazeController()
    {
        model = new Model();
        //Uncomment for Lanterna
        // * terminal = new DefaultTerminalFactory().createTerminal();
        // * mazeView = new LanternaView(new TerminalScreen(terminal), model);
        mazeView = new SwingView(model, new MazeListener());
        isGameOver = false;

        mazeView.draw();
        //beginPolling();


    }

    //Lanterna input
    private void beginPolling()
    {
        while (!isGameOver)
        {
            try
            {
                KeyStroke pressedKey = terminal.readInput();
                Direction playerDirection;
                switch (pressedKey.getKeyType())
                {
                    case ArrowUp:
                        playerDirection = new Direction(Direction.Type.NORTH);
                        break;
                    case ArrowDown:
                        playerDirection = new Direction(Direction.Type.SOUTH);
                        break;
                    case ArrowRight:
                        playerDirection = new Direction(Direction.Type.EAST);
                        break;
                    case ArrowLeft:
                        playerDirection = new Direction(Direction.Type.WEST);
                        break;
                    default:
                        playerDirection = new Direction(Direction.Type.NONE);
                        break;

                }
                model.step(playerDirection);
                mazeView.draw();
                isGameOver = model.isGameOver();

            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        if (isGameOver)
        {
            quit();
        }
    }

    private void quit()
    {
        mazeView.quit();
    }

}
