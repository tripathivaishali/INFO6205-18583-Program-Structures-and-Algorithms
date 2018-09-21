/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;
    private int l = 1;
    private final Random random = new Random();
    private int m;
    
    
    private void move(int dx, int dy) {
        x=x+dx;y=y+dy;

    }

    /**
     * Perform a random walk of m steps
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        for (int i = 0; i < m; i++)
            randomMove();
    }

    private void randomMove() {
        if(random.nextBoolean()){
            move(l*(random.nextBoolean()?-1:1),0);
        }
        else{
            move(0,l*(random.nextBoolean()?-1:1));
        }

        
    }

    public double distance() {
        double d = Math.sqrt(Math.pow(0-x,2) + Math.pow(0-y,2));


        return d; 
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
       for (int i = 0; i < n; i++){
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
          }
        
                return totalDistance/n ;


    }

    public static void main(String[] args) {
//        args = new String[]{"2","30"};
        
        if (args.length==0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");
        
        int m = Integer.parseInt(args[0]);
        int n = 30;
        if (args.length > 1) n = Integer.parseInt(args[1]);
        double meanDistance = randomWalkMulti(m, n);
        
        System.out.println("Number of Steps: "+m + "\nMean Distance: " + meanDistance + "\nOver "+ n + " experiments");
    }

}
