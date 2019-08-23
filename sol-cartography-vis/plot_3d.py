#she-bang
import matplotlib.pyplot as pl
from mpl_toolkits.mplot3d import Axes3D

filename = "sample.csv"
loa_datapoints = []

x_id = 2
y_id = 3
z_id = 4

with open(filename, 'r') as infile:
    for line in infile.readlines():
        loa_datapoints.append(line.split(","))

lin_movement = [i for i in range(len(loa_datapoints))]
x_datapoints = [float(p[x_id]) for p in loa_datapoints]
y_datapoints = [float(p[y_id]) for p in loa_datapoints]
#z_datapoints = [float(p[z_id]) for p in loa_datapoints]
z_datapoints = [float(p[z_id]) + L for p,L in zip(loa_datapoints, lin_movement)]

fig = pl.figure()
ax = fig.add_subplot(111, projection="3d")
ax.plot(x_datapoints,y_datapoints,z_datapoints)
ax.set_xlabel("distance [AU]")
ax.set_ylabel("distance [AU]")
ax.set_zlabel("distance [AU]")
fig.savefig("sample3d.png")

#fig, ax = pl.subplots()
#ax.set_aspect("equal")
#ax.plot(x_datapoints, y_datapoints, "r--", label=filename)
#ax.plot(0,0, "y*", label="SOL")
#fig.legend()
#fig.savefig("sample.png")