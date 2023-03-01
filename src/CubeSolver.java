import Solver.Solver;
import UI.ProcessingRenderer;

public class CubeSolver {

    public static void main(String[] args) throws Exception {
        ProcessingRenderer renderer = new ProcessingRenderer();
        Solver solver = new Solver(renderer);
        Thread.sleep(1000);
        new Thread(solver).run();
    }
}
