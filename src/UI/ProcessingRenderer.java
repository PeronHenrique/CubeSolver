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
import peasy.*;

public class ProcessingRenderer extends PApplet {

	private class MoveList {
		public ArrayList<Move> moves = new ArrayList<>();
		public int maxSteps = 75;

		public MoveList(ArrayList<Move> moves, int maxSteps) {
			this.maxSteps = maxSteps;
			this.moves = moves;
		}

		public Move getNext() {
			if (moves.size() == 0)
				return Move.NONE;

			Move move = moves.get(0);
			moves.remove(0);
			return move;
		}

		public boolean isEmpty() {
			return moves.size() == 0;
		}
	}

	private final COLOR cubeColors[] = {
			COLOR.YELLOW,
			COLOR.BLUE,
			COLOR.RED,
			COLOR.GREEN,
			COLOR.ORANGE,
			COLOR.WHITE,
	};

	private final int HALF_CUBIE_SIZE = 100;
	private final int CUBIE_SIZE = 200;

	private Cube cube;
	private PShape cubeShape;

	private ArrayList<MoveList> moveLists;

	private Move move;
	private int step;

	private int MAX_STEPS = 75;

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
		moveLists = new ArrayList<>();
		move = Move.NONE;
		step = 1;
		cube = Cube.Solved();
		drawCube(cube);
	}

	@Override
	public void draw() {
		rotateX(-.5f);
		rotateY(-.5f);
		scale(0.85f);

		background(120);

		makeMove();
		drawCube(cube);
		step = (step + 1) % MAX_STEPS;
		shape(cubeShape);
	}

	private void makeMove() {
		if (step == 0) {
			cube.move(move);

			if (moveLists.size() == 0) {
				move = Move.NONE;
				return;
			}

			if (moveLists.get(0).isEmpty()) {
				moveLists.remove(0);

				if (moveLists.size() == 0) {
					move = Move.NONE;
					MAX_STEPS = 75;
					return;
				}

				MAX_STEPS = moveLists.get(0).maxSteps;
			}

			move = moveLists.get(0).getNext();
		}

	}

	public void addMoves(ArrayList<Move> moves, int maxSteps) {
		printMoves(moves);
		if (maxSteps <= 0)
			maxSteps = 75;
		if (moveLists.size() == 0)
			MAX_STEPS = maxSteps;

		moveLists.add(new MoveList(moves, maxSteps));
	}

	private void printMoves(ArrayList<Move> moves) {
		String s = "";
		for (Move move : moves)
			s += move.name() + " ";

		s = s.replace("_", "'");
		s = s.replace("X", "x");
		s = s.replace("Y", "y");
		s = s.replace("Z", "z");
		s = s.replace("RW", "r");
		s = s.replace("LW", "l");
		s = s.replace("UW", "u");
		s = s.replace("DW", "d");
		s = s.replace("BW", "b");
		s = s.replace("FW", "f");
		System.out.println(s);
	}

	private void drawCube(Cube cube) {
		cubeShape = createShape(GROUP);

		if (cube == null)
			return;

		drawCenter(cube.getCenterColors(Face.U), Face.U);
		drawCenter(cube.getCenterColors(Face.B), Face.B);
		drawCenter(cube.getCenterColors(Face.R), Face.R);
		drawCenter(cube.getCenterColors(Face.F), Face.F);
		drawCenter(cube.getCenterColors(Face.L), Face.L);
		drawCenter(cube.getCenterColors(Face.D), Face.D);

		drawEdge(cube.getEdgeColors(Edge.UB), Edge.UB);
		drawEdge(cube.getEdgeColors(Edge.UR), Edge.UR);
		drawEdge(cube.getEdgeColors(Edge.UF), Edge.UF);
		drawEdge(cube.getEdgeColors(Edge.UL), Edge.UL);
		drawEdge(cube.getEdgeColors(Edge.FR), Edge.FR);
		drawEdge(cube.getEdgeColors(Edge.FL), Edge.FL);
		drawEdge(cube.getEdgeColors(Edge.BL), Edge.BL);
		drawEdge(cube.getEdgeColors(Edge.BR), Edge.BR);
		drawEdge(cube.getEdgeColors(Edge.DB), Edge.DB);
		drawEdge(cube.getEdgeColors(Edge.DR), Edge.DR);
		drawEdge(cube.getEdgeColors(Edge.DF), Edge.DF);
		drawEdge(cube.getEdgeColors(Edge.DL), Edge.DL);

		drawCorner(cube.getCornerColors(Corner.ULB), Corner.ULB);
		drawCorner(cube.getCornerColors(Corner.URB), Corner.URB);
		drawCorner(cube.getCornerColors(Corner.URF), Corner.URF);
		drawCorner(cube.getCornerColors(Corner.ULF), Corner.ULF);
		drawCorner(cube.getCornerColors(Corner.DLB), Corner.DLB);
		drawCorner(cube.getCornerColors(Corner.DRB), Corner.DRB);
		drawCorner(cube.getCornerColors(Corner.DRF), Corner.DRF);
		drawCorner(cube.getCornerColors(Corner.DLF), Corner.DLF);
	}

	private void drawCenter(byte[] colorIndexes, Face position) {
		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation);
		getYRotation(rotation);
		getZRotation(rotation);

		switch (position) {
			case U:
				colors[0] = cubeColors[colorIndexes[0]];
				offset.add(0, -CUBIE_SIZE, 0);

				getURotation(rotation);

				getMRotation(rotation);
				getSRotation(rotation);

				getUWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case L:
				colors[1] = cubeColors[colorIndexes[0]];
				offset.add(-CUBIE_SIZE, 0, 0);

				getLRotation(rotation);

				getSRotation(rotation);
				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getLWRotation(rotation);
				break;

			case F:
				colors[2] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, CUBIE_SIZE);

				getFRotation(rotation);

				getMRotation(rotation);
				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getFWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case R:
				colors[3] = cubeColors[colorIndexes[0]];
				offset.add(CUBIE_SIZE, 0, 0);

				getRRotation(rotation);

				getSRotation(rotation);
				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				break;

			case B:
				colors[4] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, -CUBIE_SIZE);

				getBRotation(rotation);

				getMRotation(rotation);
				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case D:
				colors[5] = cubeColors[colorIndexes[0]];
				offset.add(0, CUBIE_SIZE, 0);

				getDRotation(rotation);

				getMRotation(rotation);
				getSRotation(rotation);

				getDWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;
		}

		drawCubie(colors, offset, rotation);
	}

	private void drawEdge(byte[] colorIndexes, Edge position) {

		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation);
		getYRotation(rotation);
		getZRotation(rotation);

		switch (position) {
			case UB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[4] = cubeColors[colorIndexes[1]];

				offset.add(0, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation);
				getBRotation(rotation);

				getMRotation(rotation);

				getUWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case UR:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, 0);

				getURotation(rotation);
				getRRotation(rotation);

				getSRotation(rotation);

				getUWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				break;

			case UF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation);
				getFRotation(rotation);

				getMRotation(rotation);

				getUWRotation(rotation);
				getFWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case UL:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, 0);

				getURotation(rotation);
				getLRotation(rotation);

				getSRotation(rotation);

				getUWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getLWRotation(rotation);
				break;

			case BL:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, -CUBIE_SIZE);

				getLRotation(rotation);
				getBRotation(rotation);

				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getBWRotation(rotation);
				getLWRotation(rotation);
				break;

			case BR:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, -CUBIE_SIZE);

				getRRotation(rotation);
				getBRotation(rotation);

				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				break;

			case FL:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, CUBIE_SIZE);

				getLRotation(rotation);
				getFRotation(rotation);

				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getFWRotation(rotation);
				getLWRotation(rotation);
				break;

			case FR:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, CUBIE_SIZE);

				getRRotation(rotation);
				getFRotation(rotation);

				getERotation(rotation);

				getUWRotation(rotation);
				getDWRotation(rotation);
				getFWRotation(rotation);
				getRWRotation(rotation);
				break;

			case DB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[4] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation);
				getBRotation(rotation);

				getMRotation(rotation);

				getDWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case DR:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, 0);

				getDRotation(rotation);
				getRRotation(rotation);

				getSRotation(rotation);

				getDWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getRWRotation(rotation);
				break;

			case DF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation);
				getFRotation(rotation);

				getMRotation(rotation);

				getDWRotation(rotation);
				getFWRotation(rotation);
				getRWRotation(rotation);
				getLWRotation(rotation);
				break;

			case DL:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, 0);

				getDRotation(rotation);
				getLRotation(rotation);

				getSRotation(rotation);

				getDWRotation(rotation);
				getFWRotation(rotation);
				getBWRotation(rotation);
				getLWRotation(rotation);
				break;

		}

		drawCubie(colors, offset, rotation);
	}

	private void drawCorner(byte[] colorIndexes, Corner position) {

		COLOR[] colors = new COLOR[] {
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK,
				COLOR.BLACK, COLOR.BLACK, COLOR.BLACK };

		PVector offset = new PVector();
		PVector rotation = new PVector();

		getXRotation(rotation);
		getYRotation(rotation);
		getZRotation(rotation);

		switch (position) {
			case ULB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation);
				getLRotation(rotation);
				getBRotation(rotation);

				getUWRotation(rotation);
				getBWRotation(rotation);
				getLWRotation(rotation);
				break;

			case URB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, -CUBIE_SIZE);

				getURotation(rotation);
				getRRotation(rotation);
				getBRotation(rotation);

				getUWRotation(rotation);
				getRWRotation(rotation);
				getBWRotation(rotation);
				break;

			case ULF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation);
				getLRotation(rotation);
				getFRotation(rotation);

				getUWRotation(rotation);
				getLWRotation(rotation);
				getFWRotation(rotation);
				break;

			case URF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getURotation(rotation);
				getRRotation(rotation);
				getFRotation(rotation);

				getUWRotation(rotation);
				getRWRotation(rotation);
				getFWRotation(rotation);
				break;

			case DLB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation);
				getLRotation(rotation);
				getBRotation(rotation);

				getDWRotation(rotation);
				getLWRotation(rotation);
				getBWRotation(rotation);
				break;

			case DRB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDRotation(rotation);
				getRRotation(rotation);
				getBRotation(rotation);

				getDWRotation(rotation);
				getRWRotation(rotation);
				getBWRotation(rotation);
				break;

			case DLF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation);
				getLRotation(rotation);
				getFRotation(rotation);

				getDWRotation(rotation);
				getLWRotation(rotation);
				getFWRotation(rotation);
				break;

			case DRF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDRotation(rotation);
				getRRotation(rotation);
				getFRotation(rotation);

				getDWRotation(rotation);
				getRWRotation(rotation);
				getFWRotation(rotation);
				break;
			default:
				break;

		}

		drawCubie(colors, offset, rotation);
	}

	private void drawCubie(COLOR[] colors, PVector offset, PVector rotation) {
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

	private void getURotation(PVector rotation) {
		float angle = 0;
		if (move == Move.U)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.U_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.U2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.D)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.D_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.D2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.L)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.L_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.L2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.R)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.R_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.R2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.B)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.B_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.B2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.F)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.F_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.F2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getXRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.X)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.X_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.X2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getYRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.Y)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.Y_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.Y2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getZRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.Z)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.Z_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.Z2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getMRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.M)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.M_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.M2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getERotation(PVector rotation) {
		float angle = 0;
		if (move == Move.E)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.E_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.E2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getSRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.S)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.S_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.S2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getUWRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.UW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.UW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.UW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDWRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.DW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.DW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.DW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLWRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.LW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.LW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.LW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRWRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.RW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.RW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.RW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBWRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.BW)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.BW_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.BW2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFWRotation(PVector rotation) {
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
