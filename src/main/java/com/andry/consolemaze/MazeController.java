package com.andry.consolemaze;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class MazeController
{
    private Terminal terminal;
    private MazeView mazeView;

    private Model model;

    public MazeController()
    {
        try
        {
            terminal = new DefaultTerminalFactory().createTerminal();
            model = new Model();
            mazeView = new MazeView(new TerminalScreen(terminal), model);



            mazeView.draw();
            beginPolling();

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void beginPolling()
    {
        while (true)
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
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

}
