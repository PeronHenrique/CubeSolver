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


	private final COLOR cubeColors[] = {
			COLOR.YELLOW,
			COLOR.BLUE,
			COLOR.RED,
			COLOR.GREEN,
			COLOR.ORANGE,
			COLOR.WHITE,
	};

	private final int MAX_STEPS = 2;
	private final int HALF_CUBIE_SIZE = 100;
	private final int CUBIE_SIZE = 200;

	private Cube cube;
	private PShape cubeShape;

	private ArrayList<Move> moveList;

	private Move move;
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
		moveList = new ArrayList<>();
		move = Move.NONE;
		step = 0;
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
		shape(cubeShape);
	}

	private void makeMove() {
		if (step == 0) {
			cube.move(move);

 			if (moveList.size() == 0) {
				move = Move.NONE;
				drawCube(cube);
				return;
			}

			move = moveList.get(0);
			moveList.remove(0);
		}

		drawCube(cube);
		step = (step + 1) % MAX_STEPS;
	}

	public void addMoves(ArrayList<Move> moves) {
		printMoves(moves);
		moveList.addAll(moves);
	}

	private void printMoves(ArrayList<Move> moves) {
        String s = "";
        for (Move move : moves) 
            s += move.name() + " ";
        
        s = s.replace("_", "'");
        s = s.replace("X", "x");
        s = s.replace("Y", "y");
        s = s.replace("Z", "z");
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

		drawCorner(cube.getCornerColors(Corner.ULB),
				Corner.ULB);
		drawCorner(cube.getCornerColors(Corner.URB),
				Corner.URB);
		drawCorner(cube.getCornerColors(Corner.URF),
				Corner.URF);
		drawCorner(cube.getCornerColors(Corner.ULF),
				Corner.ULF);
		drawCorner(cube.getCornerColors(Corner.DLB),
				Corner.DLB);
		drawCorner(cube.getCornerColors(Corner.DRB),
				Corner.DRB);
		drawCorner(cube.getCornerColors(Corner.DRF),
				Corner.DRF);
		drawCorner(cube.getCornerColors(Corner.DLF),
				Corner.DLF);
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

				getUpRotation(rotation);

				getMRotation(rotation);
				getSRotation(rotation);
				break;

			case L:
				colors[1] = cubeColors[colorIndexes[0]];
				offset.add(-CUBIE_SIZE, 0, 0);

				getLeftRotation(rotation);
				
				getSRotation(rotation);
				getERotation(rotation);
				break;

			case F:
				colors[2] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, CUBIE_SIZE);

				getFrontRotation(rotation);
				
				getMRotation(rotation);
				getERotation(rotation);
				break;

			case R:
				colors[3] = cubeColors[colorIndexes[0]];
				offset.add(CUBIE_SIZE, 0, 0);

				getRightRotation(rotation);
				
				getSRotation(rotation);
				getERotation(rotation);
				break;

			case B:
				colors[4] = cubeColors[colorIndexes[0]];
				offset.add(0, 0, -CUBIE_SIZE);

				getBackRotation(rotation);
				
				getMRotation(rotation);
				getERotation(rotation);
				break;

			case D:
				colors[5] = cubeColors[colorIndexes[0]];
				offset.add(0, CUBIE_SIZE, 0);

				getDownRotation(rotation);
				
				getMRotation(rotation);
				getSRotation(rotation);
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

				getUpRotation(rotation);
				getBackRotation(rotation);

				getMRotation(rotation);
				break;

			case UR:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, 0);

				getUpRotation(rotation);
				getRightRotation(rotation);
				
				getSRotation(rotation);
				break;

			case UF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, -CUBIE_SIZE, CUBIE_SIZE);

				getUpRotation(rotation);
				getFrontRotation(rotation);

				getMRotation(rotation);
				break;

			case UL:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, 0);

				getUpRotation(rotation);
				getLeftRotation(rotation);
				
				getSRotation(rotation);
				break;

			case BL:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, -CUBIE_SIZE);

				getLeftRotation(rotation);
				getBackRotation(rotation);
				
				getERotation(rotation);
				break;

			case BR:
				colors[4] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, -CUBIE_SIZE);

				getRightRotation(rotation);
				getBackRotation(rotation);
				
				getERotation(rotation);
				break;

			case FL:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, 0, CUBIE_SIZE);

				getLeftRotation(rotation);
				getFrontRotation(rotation);
				
				getERotation(rotation);
				break;

			case FR:
				colors[2] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, 0, CUBIE_SIZE);

				getRightRotation(rotation);
				getFrontRotation(rotation);
				
				getERotation(rotation);
				break;

			case DB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[4] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, -CUBIE_SIZE);

				getDownRotation(rotation);
				getBackRotation(rotation);
				
				getMRotation(rotation);
				break;

			case DR:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, 0);

				getDownRotation(rotation);
				getRightRotation(rotation);
				
				getSRotation(rotation);
				break;

			case DF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[2] = cubeColors[colorIndexes[1]];

				offset.add(0, CUBIE_SIZE, CUBIE_SIZE);

				getDownRotation(rotation);
				getFrontRotation(rotation);
				
				getMRotation(rotation);
				break;

			case DL:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, 0);

				getDownRotation(rotation);
				getLeftRotation(rotation);
				
				getSRotation(rotation);
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

				getUpRotation(rotation);
				getLeftRotation(rotation);
				getBackRotation(rotation);
				break;

			case URB:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, -CUBIE_SIZE);

				getUpRotation(rotation);
				getRightRotation(rotation);
				getBackRotation(rotation);
				break;

			case ULF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getUpRotation(rotation);
				getLeftRotation(rotation);
				getFrontRotation(rotation);
				break;

			case URF:
				colors[0] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, -CUBIE_SIZE, CUBIE_SIZE);

				getUpRotation(rotation);
				getRightRotation(rotation);
				getFrontRotation(rotation);
				break;

			case DLB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDownRotation(rotation);
				getLeftRotation(rotation);
				getBackRotation(rotation);
				break;

			case DRB:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[4] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, -CUBIE_SIZE);

				getDownRotation(rotation);
				getRightRotation(rotation);
				getBackRotation(rotation);
				break;

			case DLF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[1] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(-CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDownRotation(rotation);
				getLeftRotation(rotation);
				getFrontRotation(rotation);
				break;

			case DRF:
				colors[5] = cubeColors[colorIndexes[0]];
				colors[3] = cubeColors[colorIndexes[1]];
				colors[2] = cubeColors[colorIndexes[2]];

				offset.add(CUBIE_SIZE, CUBIE_SIZE, CUBIE_SIZE);

				getDownRotation(rotation);
				getRightRotation(rotation);
				getFrontRotation(rotation);
				break;
			default:
				break;

		}

		drawCubie(colors, offset, rotation);
	}

	private void getUpRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.U)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.U_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.U2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDownRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.D)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.D_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.D2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLeftRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.L)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.L_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.L2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRightRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.R)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.R_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.R2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBackRotation(PVector rotation) {
		float angle = 0;
		if (move == Move.B)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Move.B_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Move.B2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFrontRotation(PVector rotation) {
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

}
