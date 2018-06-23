package SnakeGame;

public class Game implements Runnable {
    private long loopstarttime,loopendtime,deltatime;

    @Override
    public void run() {
        while(true){
            loopstarttime = System.currentTimeMillis();
            triggerrun();
            _wait();

        }
    }

    private void _wait() {
        loopendtime = System.currentTimeMillis();
        deltatime = 100 - (loopendtime - loopstarttime);
        if(deltatime>0){
            try {
                Thread.sleep(deltatime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void triggerrun() {
        Snake snake = Snake.getInstance();
        if(snake.isrunning)
            snake.update();
    }
}
