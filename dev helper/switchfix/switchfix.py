path = "dev helper/switchfix/"

with open(f"{path}switchOLL.txt", "r") as file:
    with open(f"{path}new.txt", "w") as correct:
        
        correct.write(" public static final HashMap<Integer, Integer> ollMap = new HashMap<>() {\n  {\n")

        for line in file:
            if line.find("case") != -1:
                idx = line.find("0x")
                caseindex = line[idx:-2]
                nextLine = file.readline()
                idx = nextLine.find(" = ")
                numberindex = int(nextLine[idx+3:-2])

                correct.write(f"        put({caseindex}, {(numberindex+4)});\n")


        correct.write("    }\n  };\n")

