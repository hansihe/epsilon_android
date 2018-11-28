with open("epsilon/ion/src/shared/events.cpp", 'r') as f:

    while "char * s_nameForEvent[255]" not in f.readline():
        pass

    text = ""

    t = f.readline()
    while "};" not in t:
        t = t.strip()

        if not t.startswith("//"):
            text += t

        t = f.readline()

entries = text.split(",")

events = []

for entry in entries:
    entry = entry.strip()

    if len(entry) == 0:
        continue

    if entry == "nullptr":
        events.append(None)
    else:
        events.append(entry.replace("\"", ""))

out = ""

out += "package com.hansihe.epsilon_sim\n"
out += "\n"
out += "object EpsilonEvents {\n"
out += "\n"

for (idx, event) in enumerate(events):
    if event is None:
        continue
    out += "    const val " + event + ": Int = " + str(idx) + ";\n"

out += "\n"

out += "    val lowerCaseCharacters = arrayOf(\n"
for (idx, event) in enumerate(events):
    if event is None:
        continue
    if event.startswith("Lower") and len(event) == 6:
        if event[5] == "Z":
            out += "        " + str(idx) + " // " + event + "\n"
        else:
            out += "        " + str(idx) + ", // " + event + "\n"
out += "    )\n"

out += "    val upperCaseCharacters = arrayOf(\n"
for (idx, event) in enumerate(events):
    if event is None:
        continue
    if event.startswith("Upper") and len(event) == 6:
        if event[5] == "Z":
            out += "        " + str(idx) + " // " + event + "\n"
        else:
            out += "        " + str(idx) + ", // " + event + "\n"
out += "    )\n"

out += "\n"
out += "}\n"

with open("app/src/main/java/com/hansihe/epsilon_sim/EpsilonEvents.kt", 'w') as f:
    f.write(out)
