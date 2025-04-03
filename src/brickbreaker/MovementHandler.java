package brickbreaker;

public class MovementHandler {
    private final int panelWidth;
    private final int panelHeight;

    public MovementHandler(int panelWidth, int panelHeight) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
    }

    public void moveBall(Ball ball) {
        if (ball.getX() < 0 || (ball.getX() + ball.getBALL_WIDTH()) > panelWidth - 10) {
            ball.setXVelocity(-ball.getXVelocity());
        }
        if (ball.getY() < 0) {
            ball.setYVelocity(-ball.getYVelocity());
        }
        ball.setX(ball.getX() + ball.getXVelocity());
        ball.setY(ball.getY() + ball.getYVelocity());
    }

    public void moveBase(Base base, boolean isLeftKeyPressed, boolean isRightKeyPressed) {
        if (isRightKeyPressed && base.getX() + base.getBASE_WIDTH() < panelWidth - 20) {
            base.setX(base.getX() + 5);
        } else if (isLeftKeyPressed && base.getX() >= 5) {
            base.setX(base.getX() - 5);
        }
    }

    public void moveGameObjects(Base base, Ball ball) {
        moveBase(base, base.isLeftKeyPressed(), base.isRightKeyPressed());
        moveBall(ball);
    }
}
// New class to handle movement logic