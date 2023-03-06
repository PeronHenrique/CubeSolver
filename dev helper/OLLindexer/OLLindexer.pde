
int[] cube = new int[25];

void setup() {
  size(500, 500);
}


void draw() {
  for (int x= 0; x < 5; x++)
    for (int y = 0; y < 5; y++) {
      fill(255 - cube[y * 5 + x]);
      rect(x * 100, y * 100, 100, 100);
    }
}

void mousePressed() {
  int index = mouseX/100 + mouseY/100 * 5;

  if (cube[index] == 0)
    cube[index] = 255;
  else
    cube[index] = 0;
}

int index = 16;

void keyPressed() {
  int codex = 0;
  if (cube[1] == 0) codex |= (2 << 0);
  else if (cube[5] == 0) codex |= (1 << 0);

  if (cube[2] == 0) codex |= (1 << 2);

  if (cube[9] == 0) codex |= (2 << 4);
  else if (cube[3] == 0) codex |= (1 << 4);

  if (cube[14] == 0) codex |= (1 << 6);

  if (cube[23] == 0) codex |= (2 << 8);
  else if (cube[19] == 0) codex |= (1 << 8);

  if (cube[22] == 0) codex |= (1 << 10);

  if (cube[15] == 0) codex |= (2 << 12);
  else if (cube[21] == 0) codex |= (1 << 12);

  if (cube[10] == 0) codex |= (1 << 14);


  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((index - 1) * 4) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((index - 1) * 4 + 1) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((index - 1) * 4 + 2) + ";");
  println("break;");

  codex = ((codex << 4) & 0xFFFF) | (codex >> 12);
  println("case 0x" + hex(codex, 4) + ": ");
  println("index = " + ((index - 1) * 4 + 3) + ";");
  println("break;");
  index++;
  println("");
}
