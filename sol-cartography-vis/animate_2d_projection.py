#she-bang
import matplotlib.pyplot as pl
import matplotlib.animation as an
pl.style.use('seaborn-pastel')

x_id = 2
y_id = 3

def readCsvFile(filename):
    loa_datapoints = []
    with open(filename, 'r') as infile:
        for line in infile.readlines():
            loa_datapoints.append(line.split(","))

    x_datapoints = [float(p[x_id]) for p in loa_datapoints]
    y_datapoints = [float(p[y_id]) for p in loa_datapoints]
    return x_datapoints,y_datapoints

x_earth, y_earth = readCsvFile("data/earth_se.csv")
x_mars, y_mars = readCsvFile("data/mars_se.csv")
x_venus, y_venus = readCsvFile("data/venus_se.csv")

datapoint_interval = 1
frames = int(len(x_earth)/datapoint_interval)

print("read all the files")

fig = pl.figure()
ax = fig.add_axes([0.0,0.0,1.0,1.0])
ax.plot(0,0, "y*")
ax.set_ylim([-2.0,2.0])
ax.set_xlim([-2.0,2.0])

line_earth, = ax.plot([],[], "bo")
line_mars, = ax.plot([],[], "ro")
line_venus, = ax.plot([],[], "ko")

def init():
    print("matplotliblib.animation.init()")
    line_earth.set_data([],[])
    line_mars.set_data([],[])
    line_venus.set_data([],[])
    return line_earth, line_mars, line_venus

def animate(i):
    print("matplotlib.animation.animate(%d)"%i)
    line_earth.set_data(x_earth[i*datapoint_interval], y_earth[i*datapoint_interval])
    line_mars.set_data(x_mars[i*datapoint_interval],y_mars[i*datapoint_interval])
    line_venus.set_data(x_venus[i*datapoint_interval],y_venus[i*datapoint_interval])
    return line_earth, line_mars, line_venus

anim = an.FuncAnimation(fig, animate, init_func=init, frames=frames, blit=False)
anim.save("sample.mp4", fps=30, extra_args=['-vcodec','libx264'])
