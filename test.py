f = open ("pso-packages.txt")

packages = []
for line in f.readlines():
    value, weight = line.split(",")
    package = (float(value),float(weight))
    value_to_weight = float(value)/float(weight);
    packages.append((value_to_weight,package))

packages.sort()
packages.reverse()

selection = []
weight = 0
value = 0
c = 0
while weight < 1000:
    selection.append(packages[c])
    c+= 1
    vtw, p = packages[c]
    v, w = p
    weight += w
    value += v
for package in selection:
    print package

print value, weight, len(selection)
