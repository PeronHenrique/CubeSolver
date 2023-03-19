package UI;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.util.ArrayList;

import Model.Corner;
import Model.Cube;
import Model.Edge;
import Model.Face;
import Model.Move;
import Solver.CFOPSolver;
import Solver.Solver;
import peasy.*;

public class ProcessingRenderer extends PApplet {

	private final COLOR cubeColors[] = {
			COLOR.YELLOW,
			COLOR.BLUE,
			COLOR.RED,
			COLOR.GREEN,
			COLOR.ORANGE,
			COLOR.WHITE,
	};

	private final int HALF_CUBIE_SIZE = 50;
	private final int CUBIE_SIZE = 100;
	private final int CUBE_SPACING = 600;

	private final int MAX_STEPS = 30;

	private ArrayList<SolverContainer> solvers;

	private final String testScramble = "";

	private int step;

	public ProcessingRenderer() {
		PApplet.runSketch(new String[] { "Cube Solver" }, this);
	}

	@Override
	public void settings() {
		size(600, 600, P3D);
	}

	@Override
	public void setup() {
		new PeasyCam(this, 1000);
		step = 1;

		solvers = new ArrayList<>();

		for (int i = 0; i < 9; i++) {
			String scramble;
			if ("".equals(testScramble))
				scramble = Move.getRandomMoves(20);
			else
				scramble = testScramble;


			CFOPSolver cfop = new CFOPSolver(this, scramble);
			solvers.add(new SolverContainer(cfop, scramble));
		}

		drawCubes();
	}

	public void setSolution(Solver solver, String solution) {
		for (SolverContainer sc : solvers) {
			if (sc.getSolver() == solver)
				sc.setSolution(solution);
		}
	}

	@Override
	public void draw() {
		translate(-720, -720, -1000);
		// rotateX(-.4f);
		// rotateY(-.4f);

		background(120);

		drawCubes();
		step = (step + 1) % MAX_STEPS;
	}

	private void drawCubes() {

		int index = 0;

		for (SolverContainer sc : solvers) {
			pushMatrix();
			translate((index % 3) * CUBE_SPACING, (index / 3) * CUBE_SPACING);

			if (step == 0) {
				sc.getCube().move(sc.getMove());
				sc.getNextMove();
			}

			shape(drawCube(sc.getCube(), sc.getMove()));
			index++;

			popMatrix();
		}

	}

	private PShape drawCube(Cube cube, Move move) {
		PShape cubeShape = createShape(GROUP);

		if (cube == null)
			return cubeShape;

		drawCenter(cube.getCenterColors(Face.U), Face.U, cubeShape, move);
		drawCenter(cube.getCenterColors(Face.B), Face.B, cubeShape, move);
		drawCenter(cube.getCenterColors(Face.R), Face.R, cubeShape, move);
		drawCenter(cube.getCenterColors(Face.F), Face.F, cubeShape, move);
		drawCenter(cube.getCenterColors(Face.L), Face.L, cubeShape, move);
		drawCenter(cube.getCenterColors(Face.D), Face.D, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.UB), Edge.UB, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.UR), Edge.UR, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.UF), Edge.UF, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.UL), Edge.UL, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.FR), Edge.FR, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.FL), Edge.FL, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.BL), Edge.BL, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.BR), Edge.BR, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.DB), Edge.DB, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.DR), Edge.DR, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.DF), Edge.DF, cubeShape, move);
		drawEdge(cube.getEdgeColors(Edge.DL), Edge.DL, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.ULB), Corner.ULB, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.URB), Corner.URB, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.URF), Corner.URF, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.ULF), Corner.ULF, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.DLB), Corner.DLB, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.DRB), Corner.DRB, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.DRF), Corner.DRF, cubeShape, move);
		drawCorner(cube.getCornerColors(Corner.DLF), Corner.DLF, cubeShape, move);

		return cubeShape;
	}

	private void drawCenter(byte[] colorIndexes, Face position, PShape cubeShape, Move move) {
		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation, move);
		getYRotation(rotation, move);
		getZRotation(rotation, move);

		switch (position) {
			case U:
				colors[0] = cubeColors[colorIndexes[0]];
				offset.add(0, -CUBIE_SIZE, 0);

				getURotation(rotation, move);

				getMRotation(rotation, move);
				getSRotation(rotation, move);

				getUWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case L:
				colors[1] = cubeColors[colorIndexes[0]];
				offset.add(-CUBIE_SIZE, 0, 0);

				getLRotation(rotation, move);

				getSRotation(rotation, move);
				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case F:
				colors[2] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, CUBIE_SIZE);

				getFRotation(rotation, move);

				getMRotation(rotation, move);
				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case R:
				colors[3] = cubeColors[colorIndexes[0]];
				offset.add(CUBIE_SIZE, 0, 0);

				getRRotation(rotation, move);

				getSRotation(rotation, move);
				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				break;

			case B:
				colors[4] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, -CUBIE_SIZE);

				getBRotation(rotation, move);

				getMRotation(rotation, move);
				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case D:
				colors[5] = cubeColors[colorIndexes[0]];
				offset.add(0, CUBIE_SIZE, 0);

				getDRotation(rotation, move);

				getMRotation(rotation, move);
				getSRotation(rotation, move);

				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;
		}

		drawCubie(colors, offset, rotation, cubeShape);
	}

	private void drawEdge(byte[] colorIndexes, Edge position, PShape cubeShape, Move move) {

		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation, move);
		getYRotation(rotation, move);
		getZRotation(rotation, move);

		switch (position) {
			case UB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[4] = cubeColors[colorIndexes[1]];

				offset.add(0, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation, move);
				getBRotation(rotation, move);

				getMRotation(rotation, move);

				getUWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case UR:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, 0);

				getURotation(rotation, move);
				getRRotation(rotation, move);

				getSRotation(rotation, move);

				getUWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				break;

			case UF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation, move);
				getFRotation(rotation, move);

				getMRotation(rotation, move);

				getUWRotation(rotation, move);
				getFWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case UL:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, 0);

				getURotation(rotation, move);
				getLRotation(rotation, move);

				getSRotation(rotation, move);

				getUWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case BL:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, -CUBIE_SIZE);

				getLRotation(rotation, move);
				getBRotation(rotation, move);

				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getBWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case BR:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, -CUBIE_SIZE);

				getRRotation(rotation, move);
				getBRotation(rotation, move);

				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				break;

			case FL:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, CUBIE_SIZE);

				getLRotation(rotation, move);
				getFRotation(rotation, move);

				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case FR:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, CUBIE_SIZE);

				getRRotation(rotation, move);
				getFRotation(rotation, move);

				getERotation(rotation, move);

				getUWRotation(rotation, move);
				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getRWRotation(rotation, move);
				break;

			case DB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[4] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation, move);
				getBRotation(rotation, move);

				getMRotation(rotation, move);

				getDWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case DR:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, 0);

				getDRotation(rotation, move);
				getRRotation(rotation, move);

				getSRotation(rotation, move);

				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getRWRotation(rotation, move);
				break;

			case DF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation, move);
				getFRotation(rotation, move);

				getMRotation(rotation, move);

				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getRWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case DL:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, 0);

				getDRotation(rotation, move);
				getLRotation(rotation, move);

				getSRotation(rotation, move);

				getDWRotation(rotation, move);
				getFWRotation(rotation, move);
				getBWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

		}

		drawCubie(colors, offset, rotation, cubeShape);
	}

	private void drawCorner(byte[] colorIndexes, Corner position, PShape cubeShape, Move move) {

		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation, move);
		getYRotation(rotation, move);
		getZRotation(rotation, move);

		switch (position) {
			case ULB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation, move);
				getLRotation(rotation, move);
				getBRotation(rotation, move);

				getUWRotation(rotation, move);
				getBWRotation(rotation, move);
				getLWRotation(rotation, move);
				break;

			case URB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation, move);
				getRRotation(rotation, move);
				getBRotation(rotation, move);

				getUWRotation(rotation, move);
				getRWRotation(rotation, move);
				getBWRotation(rotation, move);
				break;

			case ULF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation, move);
				getLRotation(rotation, move);
				getFRotation(rotation, move);

				getUWRotation(rotation, move);
				getLWRotation(rotation, move);
				getFWRotation(rotation, move);
				break;

			case URF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation, move);
				getRRotation(rotation, move);
				getFRotation(rotation, move);

				getUWRotation(rotation, move);
				getRWRotation(rotation, move);
				getFWRotation(rotation, move);
				break;

			case DLB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation, move);
				getLRotation(rotation, move);
				getBRotation(rotation, move);

				getDWRotation(rotation, move);
				getLWRotation(rotation, move);
				getBWRotation(rotation, move);
				break;

			case DRB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation, move);
				getRRotation(rotation, move);
				getBRotation(rotation, move);

				getDWRotation(rotation, move);
				getRWRotation(rotation, move);
				getBWRotation(rotation, move);
				break;

			case DLF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation, move);
				getLRotation(rotation, move);
				getFRotation(rotation, move);

				getDWRotation(rotation, move);
				getLWRotation(rotation, move);
				getFWRotation(rotation, move);
				break;

			case DRF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation, move);
				getRRotation(rotation, move);
				getFRotation(rotation, move);

				getDWRotation(rotation, move);
				getRWRotation(rotation, move);
				getFWRotation(rotation, move);
				break;
			default:
				break;

		}

		drawCubie(colors, offset, rotation, cubeShape);
	}

	private void drawCubie(COLOR[] colors, PVector offset, PVector rotation, PShape cubeShape) {
		PShape cubie = createShape(GROUP);

		cubie.addChild(getCubieUpFace(colors[0].getColor()));
		cubie.addChild(getCubieLeftFace(colors[1].getColor()));
		cubie.addChild(getCubieFrontFace(colors[2].getColor()));
		cubie.addChild(getCubieRightFace(colors[3].getColor()));
		cubie.addChild(getCubieBackFace(colors[4].getColor()));
		cubie.addChild(getCubieDownFace(colors[5].getColor()));
		cubeShape.addChild(cubie);

		cubie.translate(offset.x, offset.y, offset.z);
		cubie.rotateX(rotation.x);
		cubie.rotateY(rotation.y);
		cubie.rotateZ(rotation.z);

	}

	private PShape getCubieUpFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private PShape getCubieDownFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private PShape getCubieLeftFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private PShape getCubieRightFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private PShape getCubieBackFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private PShape getCubieFrontFace(int color) {
		PShape temp = createShape();
		temp.beginShape();
		temp.vertex(-HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(-HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.vertex(HALF_CUBIE_SIZE, -HALF_CUBIE_SIZE, HALF_CUBIE_SIZE);
		temp.endShape(CLOSE);
		temp.setFill(color);
		return temp;
	}

	private void getURotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.U)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.U_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.U2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.D)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.D_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.D2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.L)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.L_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.L2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.R)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.R_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.R2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.B)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.B_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.B2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.F)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.F_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.F2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getXRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.X)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.X_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.X2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getYRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.Y)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.Y_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.Y2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getZRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.Z)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.Z_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.Z2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getMRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.M)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.M_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.M2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getERotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.E)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.E_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.E2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getSRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.S)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.S_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.S2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getUWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.UW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.UW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.UW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.DW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.DW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.DW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.LW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.LW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.LW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.RW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.RW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.RW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.BW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.BW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.BW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFWRotation(PVector rotation, Move move) {
		float angle = 0;
		if (move == Move.FW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.FW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.FW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

}
