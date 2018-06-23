package SnakeGame;

import java.util.*;

public class Snake {
    private LinkedList<node> data = new LinkedList<>();
    private LinkedList<node> food = new LinkedList<>();
    private static final int cnt = 50;
    private volatile static Snake snake;
    public static int direction = 1;
    private Random random = new Random();
    public boolean isrunning = true;
    Integer [][] Grid = new Integer[cnt][cnt];

    public synchronized Integer[][] getGrid() {
        for(int i=0;i<Grid.length;i++){
            Arrays.fill(Grid[i],0);
        }
        for (node t : data) {
            Grid[t.y][t.x] = 2;
        }
        for(int i=0;i<food.size();i++){
            node t = food.get(i);
            if(Grid[t.y][t.x]==2){
                eatfood();
                food.remove(i);
                if(food.size()<2){
                    addfood();
                }
            }else{
                Grid[t.y][t.x] = 3;
            }

        }
        return Grid;
    }

    private class node{
        int x,y;
        node(int a,int b){
            x = a;
            y = b;
        }
    }

    private Snake(){
        for(int i=3;i<6;i++){
            data.add(new node(i,4));
        }
        food.add(new node(5,5));
        food.add(new node(10,15));
        new Thread(new Game()).start();
    }

    public static Snake getInstance() {
        if(snake==null){
            synchronized (Snake.class){
                if(snake==null) {
                    snake = new Snake();
                    System.out.println("test");
                }
            }
        }
        return snake;
    }

    public synchronized void update(){
        node head = data.getFirst();
        node newhead = new node(head.x,head.y);
        switch (direction){
            case 1: {
                newhead.x = head.x + 1;
                break;
            }
            case 2:{
                newhead.x = head.x - 1;
                break;
            }
            case 3: {
                newhead.y = head.y + 1;
                break;
            }
            case 4: {
                newhead.y = head.y - 1;
                break;
            }
            default:
                break;
        }
        newhead.x %= cnt;
        newhead.y %= cnt;
        if(newhead.x<0){
            newhead.x += cnt;
        }
        if(newhead.y < 0){
            newhead.y += cnt;
        }
        data.addFirst(newhead);
        data.removeLast();
    }

    private void addfood(){
        int x = random.nextInt(cnt);
        int y = random.nextInt(cnt);
        while(Grid[y][x]==2){
            x = random.nextInt(cnt);
            y = random.nextInt(cnt);
        }
        food.add(new node(x,y));
    }

    private void eatfood(){
        node tail = data.getLast();
        node secondTail = data.get(data.size()-2);
        if(tail.x==secondTail.x){
            if(tail.y==secondTail.y+1){
                data.add(new node(tail.x,tail.y+1));
            }else{
                data.add(new node(tail.x,tail.y-1));
            }
        }else{
            if(tail.x==secondTail.x+1){
                data.add(new node(tail.x+1,tail.y));
            }else{
                data.add(new node(tail.x-1,tail.y));
            }
        }
        addfood();
    }

}
