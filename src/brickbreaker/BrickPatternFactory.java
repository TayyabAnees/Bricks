package brickbreaker;

import java.util.ArrayList;

public class BrickPatternFactory {
    public ArrayList<Brick> createBrickPattern(int level) {
        switch (level) {
            case 1: return createLevelOnePattern();
            case 2: return createLevelTwoPattern();
            case 3: return createLevelThreePattern();
            case 4: return createLevelFourPattern();
            default: return createLevelFivePattern();
        }
    }

    private ArrayList<Brick> createLevelOnePattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        int xPos = 0;
        int yPos = 50;
        for (int i = 0; i < 28; i++) {
            Brick brick = new Brick(null);
            if (i % 7 == 0) {
                xPos = 0;
                yPos += brick.getBRICK_HEIGHT() + 20;
            }
            brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
            bricks.add(brick);
            xPos += brick.getBRICK_WIDTH() + 20;
        }
        return bricks;
    }

    private ArrayList<Brick> createLevelTwoPattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        int xPos = 70;
        int yPos = 50;
        for (int i = 1; i <= 7; i++) {
            for (int j = 1; j <= 7; j++) {
                Brick brick = new Brick(null);
                if (i % 2 == 0 && j % 2 == 0) {
                    brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                    bricks.add(brick);
                } else if (i % 2 != 0 && j % 2 != 0) {
                    brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                    bricks.add(brick);
                }
                xPos += brick.getBRICK_WIDTH();
            }
            xPos = 70;
            yPos += 30;
        }
        return bricks;
    }

    private ArrayList<Brick> createLevelThreePattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        createBox(bricks, 30, 20);
        createBox(bricks, 400, 20);
        return bricks;
    }

    private void createBox(ArrayList<Brick> bricks, int startX, int startY) {
        int xPos = startX;
        int yPos = startY;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                bricks.add(brick);
                xPos += brick.getBRICK_WIDTH();
            }
            xPos = startX;
            yPos += 30;
        }
    }

    private ArrayList<Brick> createLevelFourPattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        int xPos = 120;
        int yPos = 20;
        int nextXPos = 120;
        int bricksCount = 8;
        while (bricksCount > 0) {
            for (int i = 0; i < bricksCount; i++) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                bricks.add(brick);
                xPos += brick.getBRICK_WIDTH();
            }
            bricksCount--;
            nextXPos += 35;
            xPos = nextXPos;
            yPos += 30;
        }
        return bricks;
    }

    private ArrayList<Brick> createLevelFivePattern() {
        ArrayList<Brick> bricks = new ArrayList<>();
        createTriangle(bricks, 150, 50, true);
        createTriangle(bricks, 550, 50, true);
        return bricks;
    }

    private void createTriangle(ArrayList<Brick> bricks, int startX, int startY, boolean leftAligned) {
        int xPos = startX;
        int yPos = startY;
        int nextXPos = startX;
        int bricksCount = 1;
        while (bricksCount <= 5) {
            for (int i = 0; i < bricksCount; i++) {
                Brick brick = new Brick(null);
                brick.setBounds(xPos, yPos, brick.getBRICK_WIDTH(), brick.getBRICK_HEIGHT());
                bricks.add(brick);
                xPos += brick.getBRICK_WIDTH();
            }
            bricksCount++;
            nextXPos += leftAligned ? -35 : 35;
            xPos = nextXPos;
            yPos += 30;
        }
    }
}
// New class to handle brick pattern creation