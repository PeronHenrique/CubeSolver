package Solver;

import Model.Cube;
import UI.ProcessingRenderer;

public abstract class Solver implements Runnable {

    protected Cube cube;
    protected ProcessingRenderer renderer;
    protected String solution = "";
    protected String scramble = "";

    private boolean terminalFree = true;

    public Solver(ProcessingRenderer renderer, String scramble) {
        this.renderer = renderer;
        this.cube = Cube.Scramble(scramble);
        this.scramble = scramble;

        new Thread(this).start();
    }

    public abstract void run();

    protected void setSolution(String solution) {
        // black magic to remove multiple repeated caracteres:
        // https://stackoverflow.com/questions/43605292/replacing-consecutive-repeated-characters-in-java
        solution = solution.replaceAll("(?s)(.)\\1+", "$1"); // any charsSystem.out.println(t);
        solution = solution.strip();
        renderer.setSolution(this, solution);

        printSolution(solution);
    }

    private void printSolution(String solution) {
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
