#she-bang
import matplotlib.pyplot as pl

filename = "sample.csv"
loa_datapoints = []

x_id = 2
y_id = 3

with open(filename, 'r') as infile:
    for line in infile.readlines():
        loa_datapoints.append(line.split(","))

x_datapoints = [float(p[x_id]) for p in loa_datapoints]
y_datapoints = [float(p[y_id]) for p in loa_datapoints]

fig, ax = pl.subplots()
ax.set_aspect("equal")
ax.set_xlabel("distance [AU]")
ax.set_ylabel("distance [AU]")
ax.plot(x_datapoints, y_datapoints, "r--", label=filename)
ax.plot(0,0, "y*", label="SOL")
fig.legend()
fig.savefig("sample.png")