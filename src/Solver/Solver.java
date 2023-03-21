package Solver;

import Model.Cube;
import Model.Move;
import UI.Renderer;

public abstract class Solver implements Runnable {

    protected Cube cube;
    public Cube getCube() {
        return cube;
    }

    protected Renderer renderer;
    protected String solution = "";
    public String getSolution() {
        return solution;
    }

    protected String scramble = "";
    public String getScramble() {
        return scramble;
    }

    private boolean terminalFree = true;

    public Solver(Renderer renderer, String scramble) {
        this.renderer = renderer;
        this.cube = Cube.Scramble(scramble);
        this.scramble = scramble;

        new Thread(this).start();
    }

    public abstract void run();

    protected void setSolution(String solution) {
        Move.simplifyString(solution);
        if(renderer != null)
            renderer.setSolution(this, solution);
    }

    public void printSolution(String solution) {
        while (!terminalFree) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        terminalFree = false;
        System.out.println("");
        System.out.println("*********************");
        System.out.println("Scramble: ");
        System.out.println(scramble);
        System.out.println("");
        System.out.println("Solution " + solution.split(" ").length
                + " moves from " + this.getClass().getName() + ": ");
        System.out.println(solution);
        System.out.println("");
        System.out.println("Verification: ");
        System.out.println(scramble + " " + solution);
        System.out.println("*********************");
        System.out.println("");
        terminalFree = true;
    }
}
