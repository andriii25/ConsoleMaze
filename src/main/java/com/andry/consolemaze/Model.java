package com.andry.consolemaze;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;


public class Model
{
    private Tile[][] maze;
    private Hero hero;
    private boolean isGameOver;

    public Model()
    {
        readMaze("/maze.txt");
        placeHero();
        isGameOver = false;
    }

    private void placeHero()
    {
        Position playerPosition = new Position(-1, -1);
        Random random = new Random();
        do
        {
            playerPosition.setRow(random.nextInt(maze.length));
            playerPosition.setColumn(random.nextInt(maze[0].length));
        } while (!maze[playerPosition.getRow()][playerPosition.getColumn()].canStep());
        //hero = new Hero(playerPosition, new Direction(Direction.Type.NORTH));
        hero = new Hero(new Position(1, 3), new Direction(Direction.Type.EAST));
    }


    private void readMaze(String mazeName)
    {
        File mazeFile = new File(getClass().getResource(mazeName).getFile());
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(mazeFile));
            ArrayList<Tile[]> tilesList = new ArrayList<>();
            String nextLine;
            int lineCounter = 0;
            while ((nextLine = reader.readLine()) != null)
            {
                Tile[] nextLineTiles = new Tile[nextLine.length()];
                for (int i = 0; i < nextLine.length(); i++)
                {
                    switch (nextLine.charAt(i))
                    {
                        case '#':
                            nextLineTiles[i] = new Wall(lineCounter, i);
                            break;
                        case ' ':
                            nextLineTiles[i] = new Floor(lineCounter, i);
                            break;
                        case 'X':
                            nextLineTiles[i] = new Goal(lineCounter, i);
                            break;
                    }
                }
                lineCounter++;
                tilesList.add(nextLineTiles);

            }
            maze = new Tile[tilesList.size()][];
            maze = tilesList.toArray(maze);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void step(Direction playerDirection)
    {
        if (!hero.turn(playerDirection))
        {
            Position nextPosition = new Position(hero.getPosition(), playerDirection);
            Tile toStepOn = maze[nextPosition.getRow()][nextPosition.getColumn()];
            Direction actualDirection = toStepOn.onContact(maze, playerDirection);
            hero.step(actualDirection);
            isGameOver = toStepOn.isGameOver();
        }

    }

    public boolean isGameOver()
    {
        return isGameOver;
    }

    public Tile[][] getMaze()
    {
        return maze;
    }

    public Hero getHero()
    {
        return hero;
    }

    public int getRows()
    {
        return maze.length;
    }

    public int getColumns()
    {
        return maze[0].length;
    }
}
