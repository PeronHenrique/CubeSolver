
COLOR[] cube = new COLOR[25];
int index = 0;

void setup() {
  size(500, 500);

  for (int x= 0; x < 5; x++)
    for (int y = 0; y < 5; y++) {
      if (x == 0 || x == 4 || y == 0 || y == 4)
        cube[y * 5 + x] = COLOR.BLACK;
      else
        cube[y * 5 + x] = COLOR.YELLOW;
    }
}


void draw() {
  for (int x= 0; x < 5; x++)
    for (int y = 0; y < 5; y++) {
      fill(cube[y * 5 + x].getColor());
      rect(x * 100, y * 100, 100, 100);
    }
}


void mousePressed() {
  index = mouseX/100 + mouseY/100 * 5;
}

int switchIndex = 0;

void keyPressed() {

  if (key == '1') {
    cube[index] = COLOR.ORANGE;
    return;
  }

  if (key == '2') {
    cube[index] = COLOR.GREEN;
    return;
  }

  if (key == '3') {
    cube[index] = COLOR.RED;
    return;
  }

  if (key == '4') {
    cube[index] = COLOR.BLUE;
    return;
  }

  int codex = 0;
  if (cube[1] == COLOR.GREEN && cube[5] == COLOR.ORANGE) codex |= (0 << 0); //<>//
  if (cube[1] == COLOR.RED && cube[5] == COLOR.GREEN) codex |= (1 << 0);
  if (cube[1] == COLOR.BLUE && cube[5] == COLOR.RED) codex |= (2 << 0);
  if (cube[1] == COLOR.ORANGE && cube[5] == COLOR.BLUE) codex |= (3 << 0);

  if (cube[2] == COLOR.GREEN ) codex |= (0 << 2);
  if (cube[2] == COLOR.RED ) codex |= (1 << 2);
  if (cube[2] == COLOR.BLUE ) codex |= (2 << 2);
  if (cube[2] == COLOR.ORANGE ) codex |= (3 << 2);

  if (cube[9] == COLOR.GREEN && cube[3] == COLOR.ORANGE) codex |= (0 << 4);
  if (cube[9] == COLOR.RED && cube[3] == COLOR.GREEN) codex |= (1 << 4);
  if (cube[9] == COLOR.BLUE && cube[3] == COLOR.RED) codex |= (2 << 4);
  if (cube[9] == COLOR.ORANGE && cube[3] == COLOR.BLUE) codex |= (3 << 4);

  if (cube[14] == COLOR.GREEN ) codex |= (0 << 6);
  if (cube[14] == COLOR.RED ) codex |= (1 << 6);
  if (cube[14] == COLOR.BLUE ) codex |= (2 << 6);
  if (cube[14] == COLOR.ORANGE ) codex |= (3 << 6);

  if (cube[23] == COLOR.GREEN && cube[19] == COLOR.ORANGE) codex |= (0 << 8);
  if (cube[23] == COLOR.RED && cube[19] == COLOR.GREEN) codex |= (1 <<8);
  if (cube[23] == COLOR.BLUE && cube[19] == COLOR.RED) codex |= (2 << 8);
  if (cube[23] == COLOR.ORANGE && cube[19] == COLOR.BLUE) codex |= (3 << 8);
  
  if (cube[22] == COLOR.GREEN ) codex |= (0 << 10);
  if (cube[22] == COLOR.RED ) codex |= (1 << 10);
  if (cube[22] == COLOR.BLUE ) codex |= (2 << 10);
  if (cube[22] == COLOR.ORANGE ) codex |= (3 << 10);

  if (cube[15] == COLOR.GREEN && cube[21] == COLOR.ORANGE) codex |= (0 << 12);
  if (cube[15] == COLOR.RED && cube[21] == COLOR.GREEN) codex |= (1 << 12);
  if (cube[15] == COLOR.BLUE && cube[21] == COLOR.RED) codex |= (2 << 12);
  if (cube[15] == COLOR.ORANGE && cube[21] == COLOR.BLUE) codex |= (3 << 12);

  if (cube[10] == COLOR.GREEN ) codex |= (0 << 14);
  if (cube[10] == COLOR.RED ) codex |= (1 << 14);
  if (cube[10] == COLOR.BLUE ) codex |= (2 << 14);
  if (cube[10] == COLOR.ORANGE ) codex |= (3 << 14);


  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((switchIndex) * 4) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((switchIndex) * 4 + 1) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((switchIndex) * 4 + 2) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((switchIndex) * 4 + 3) + ";");
  println("break;");
  switchIndex++;
  println("");
}
