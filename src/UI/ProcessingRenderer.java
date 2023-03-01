package UI;

import processing.core.PApplet;
import processing.core.PShape;
import processing.core.PVector;

import java.util.ArrayList;

import Model.Cube;
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

	private final int MAX_STEPS = 75;
	private final int HALF_CUBIE_SIZE = 100;
	private final int CUBIE_SIZE = 200;

	private Cube cube;
	private PShape cubeShape;

	private ArrayList<Model.Move> moveList;

	private Model.Move move;
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
		move = Model.Move.NONE;
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
				move = Model.Move.NONE;
				drawCube(cube);
				return;
			}

			move = moveList.get(0);
			moveList.remove(0);
		}

		drawCube(cube);
		step = (step + 1) % MAX_STEPS;
	}

	public void addMoves(ArrayList<Model.Move> moves) {
		moveList.addAll(moves);
	}

	private void drawCube(Cube cube) {
		cubeShape = createShape(GROUP);

		if (cube == null)
			return;

		drawCenter(cube.getCenterColors(Model.Face.U), Model.Face.U);
		drawCenter(cube.getCenterColors(Model.Face.B), Model.Face.B);
		drawCenter(cube.getCenterColors(Model.Face.R), Model.Face.R);
		drawCenter(cube.getCenterColors(Model.Face.F), Model.Face.F);
		drawCenter(cube.getCenterColors(Model.Face.L), Model.Face.L);
		drawCenter(cube.getCenterColors(Model.Face.D), Model.Face.D);

		drawEdge(cube.getEdgeColors(Model.Edge.UB), Model.Edge.UB);
		drawEdge(cube.getEdgeColors(Model.Edge.UR), Model.Edge.UR);
		drawEdge(cube.getEdgeColors(Model.Edge.UF), Model.Edge.UF);
		drawEdge(cube.getEdgeColors(Model.Edge.UL), Model.Edge.UL);
		drawEdge(cube.getEdgeColors(Model.Edge.FR), Model.Edge.FR);
		drawEdge(cube.getEdgeColors(Model.Edge.FL), Model.Edge.FL);
		drawEdge(cube.getEdgeColors(Model.Edge.BL), Model.Edge.BL);
		drawEdge(cube.getEdgeColors(Model.Edge.BR), Model.Edge.BR);
		drawEdge(cube.getEdgeColors(Model.Edge.DB), Model.Edge.DB);
		drawEdge(cube.getEdgeColors(Model.Edge.DR), Model.Edge.DR);
		drawEdge(cube.getEdgeColors(Model.Edge.DF), Model.Edge.DF);
		drawEdge(cube.getEdgeColors(Model.Edge.DL), Model.Edge.DL);

		drawCorner(cube.getCornerColors(Model.Corner.ULB),
				Model.Corner.ULB);
		drawCorner(cube.getCornerColors(Model.Corner.URB),
				Model.Corner.URB);
		drawCorner(cube.getCornerColors(Model.Corner.URF),
				Model.Corner.URF);
		drawCorner(cube.getCornerColors(Model.Corner.ULF),
				Model.Corner.ULF);
		drawCorner(cube.getCornerColors(Model.Corner.DLB),
				Model.Corner.DLB);
		drawCorner(cube.getCornerColors(Model.Corner.DRB),
				Model.Corner.DRB);
		drawCorner(cube.getCornerColors(Model.Corner.DRF),
				Model.Corner.DRF);
		drawCorner(cube.getCornerColors(Model.Corner.DLF),
				Model.Corner.DLF);
	}

	private void drawCenter(byte[] colorIndexes, Model.Face position) {
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

	private void drawEdge(byte[] colorIndexes, Model.Edge position) {

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

	private void drawCorner(byte[] colorIndexes, Model.Corner position) {

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
		if (move == Model.Move.U)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.U_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.U2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}

	private void getDownRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.D)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.D_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.D2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}

	private void getLeftRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.L)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.L_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.L2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getRightRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.R)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.R_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.R2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getBackRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.B)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.B_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.B2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, -angle);
	}

	private void getFrontRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.F)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.F_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.F2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getXRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.X)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.X_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.X2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(angle, 0, 0);
	}

	private void getYRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.Y)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.Y_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.Y2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, -angle, 0);
	}
	
	private void getZRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.Z)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.Z_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.Z2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, 0, angle);
	}

	private void getMRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.M)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.M_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.M2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(-angle, 0, 0);
	}

	private void getERotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.E)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.E_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.E2)
			angle = radians((180 * step) / (float) (MAX_STEPS));
		rotation.add(0, angle, 0);
	}
	
	private void getSRotation(PVector rotation) {
		float angle = 0;
		if (move == Model.Move.S)
			angle = radians((90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.S_)
			angle = radians((-90 * step) / (float) (MAX_STEPS));
		if (move == Model.Move.S2)
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
