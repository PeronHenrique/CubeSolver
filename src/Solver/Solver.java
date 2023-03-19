package Solver;

import Model.Cube;
import UI.ProcessingRenderer;

public abstract class Solver implements Runnable {

    protected Cube cube;
    protected ProcessingRenderer renderer;
    protected String solution = "";
    protected String scramble = "";

    public Solver(ProcessingRenderer renderer, String scramble) {
        this.renderer = renderer;
        this.cube = Cube.Scramble(scramble);
        this.scramble = scramble;
    }

    public abstract void run();

    protected void setSolution(){
		System.out.println("Scramble: " + scramble);
		System.out.println("Solution from " + this.getClass().getName() + ": " + solution);
        renderer.setSolution(this, solution);
    }
}
